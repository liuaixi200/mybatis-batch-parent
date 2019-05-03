    
/*
 * Copyright (C) 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.liuax.mybatis.batch;

import org.apache.ibatis.executor.BatchExecutor;

/**
 * 功能描述
 *
 * @author liuax01
 * @date 2019/5/2 20:10
 */
public class MybatisBatchHelper {

    private static ThreadLocal<MybatisBatchPo> mybatisBatchContext = ThreadLocal.withInitial(MybatisBatchPo::new);

    public static void startBatch(){
        mybatisBatchContext.get().setNeedBatch(true);
    }

    public static boolean needBatch(){

        return mybatisBatchContext.get().getNeedBatch();
    }

    static void increment(){
        int batchCommit = mybatisBatchContext.get().getBatchCommit();
        batchCommit++;
        int mod = mybatisBatchContext.get().getModBatchCommit();
        mod++;
        mybatisBatchContext.get().setModBatchCommit(mod);
        mybatisBatchContext.get().setBatchCommit(batchCommit);
    }

    static int getBatchCommit(){
        return mybatisBatchContext.get().getBatchCommit();
    }

    static void clearModBatchCommit(){
        mybatisBatchContext.get().setModBatchCommit(0);
    }

    static int getModBatchCommit(){
        return mybatisBatchContext.get().getModBatchCommit();
    }

    static void setBatchExecutor(BatchExecutor batchExecutor){
        mybatisBatchContext.get().setBatchExecutor(batchExecutor);
    }

    static BatchExecutor getBatchExecutor(){
        return mybatisBatchContext.get().getBatchExecutor();
    }

    static void clear(){
        mybatisBatchContext.remove();
    }
}
