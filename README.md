#在spring-boot2中引入jar包
groupId:com.github.liuax  
artifactId:mybatis-batch-starter  
version:1.0.0

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