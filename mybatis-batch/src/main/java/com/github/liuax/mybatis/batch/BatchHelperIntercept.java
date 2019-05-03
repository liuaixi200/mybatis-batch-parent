package com.github.liuax.mybatis.batch;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.BatchExecutor;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author liuax01
 * @ref
 * @className BatchHelperIntercept
 * @description
 * @date 2019/4/29 8:41
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "commit", args = {boolean.class}),
        @Signature(type = Executor.class, method = "close", args = {boolean.class})
    }
)
public class BatchHelperIntercept implements Interceptor {

    private int batchCommit = 0;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Executor target = (Executor)invocation.getTarget();
        Method method = invocation.getMethod();
        if(StringUtils.equals(method.getName(),"update") && MybatisBatchHelper.needBatch()) {
            MappedStatement ms = (MappedStatement)invocation.getArgs()[0];
            //需要批量提交
            BatchExecutor batchExecutor = MybatisBatchHelper.getBatchExecutor();
            if (batchExecutor == null) {
                batchExecutor = new BatchExecutor(ms.getConfiguration(), target.getTransaction());
                MybatisBatchHelper.setBatchExecutor(batchExecutor);
            }
            Object resObject = method.invoke(batchExecutor, invocation.getArgs());
            MybatisBatchHelper.increment();
            if(this.batchCommit > 0 && MybatisBatchHelper.getModBatchCommit() == this.batchCommit){
                //执行executeBatch
                batchExecutor.flushStatements();
                MybatisBatchHelper.clearModBatchCommit();
            }
            return resObject;
        }
        BatchExecutor batchExecutor = MybatisBatchHelper.getBatchExecutor();
        boolean hasBatchExecutor = batchExecutor != null;
        if(StringUtils.equals(method.getName(),"commit") && hasBatchExecutor){
            return method.invoke(batchExecutor, invocation.getArgs());
        }
        if(StringUtils.equals(method.getName(),"close") && hasBatchExecutor){
            MybatisBatchHelper.clear();
            return method.invoke(batchExecutor, invocation.getArgs());
        }
        return method.invoke(target,invocation.getArgs());
    }

    @Override
    public Object plugin(Object target) {
        //包装插件
        return Plugin.wrap(target,this);
    }

    @Override
    public void setProperties(Properties properties) {
        this.batchCommit = Integer.parseInt(properties.getProperty("batchCommit","0"));
    }
}
