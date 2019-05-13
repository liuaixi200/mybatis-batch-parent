#如果你的项目是spring-boot开发的，按如下引用就可以了
```xml
       <dependency>
            <groupId>com.github.liuaixi200</groupId>
            <artifactId>mybatis-batch-starter</artifactId>
            <version>1.0.0</version>
        </dependency>
```
#支持的参数
到达多少数时自动提交。
mybatis.batch.batchCommit: 5000 

#代码中使用
``` java
public void test1(){
        MybatisBatchHelper.startBatch();
        for(int i = 0;i<5000;i++){
            OssParseLog log = new OssParseLog();
            log.setBatchId(i+"");
            log.setCrtTime(new Date());
            log.setName("aaaa");
            log.setHhmmss("112233");
            log.setType("test1");
            log.setApp("0001");
            baseManagr.insertSelective(log);
        }
    }
```