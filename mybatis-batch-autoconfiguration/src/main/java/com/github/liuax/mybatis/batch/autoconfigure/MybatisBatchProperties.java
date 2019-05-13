    
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
package com.github.liuax.mybatis.batch.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

/**
 * 功能描述
 *
 * @author liuax01
 * @date 2019/5/2 20:06
 */
@ConfigurationProperties(
        prefix = "mybatis.batch"
)
public class MybatisBatchProperties {

    private Properties properties = new Properties();

    public Properties getProperties() {
        return this.properties;
    }

    public void setBatchCommit(String batchCommit) {
        Integer.parseInt(batchCommit);
        this.properties.setProperty("batchCommit",batchCommit);
    }

    public String getBatchCommit(){
        return this.properties.getProperty("batchCommit");
    }
}
