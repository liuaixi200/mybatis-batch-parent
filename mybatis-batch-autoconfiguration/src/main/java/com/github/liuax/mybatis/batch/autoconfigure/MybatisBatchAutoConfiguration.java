    
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

import com.github.liuax.mybatis.batch.BatchHelperIntercept;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

/**
 * 功能描述
 *
 * @author liuax01
 * @date 2019/5/2 21:11
 */
@Configuration("mybatisBatchAutoConfiguration")
@ConditionalOnBean({SqlSessionFactory.class})
@EnableConfigurationProperties({MybatisBatchProperties.class})
@AutoConfigureAfter({MybatisAutoConfiguration.class})
public class MybatisBatchAutoConfiguration {

    @Autowired
    private List<SqlSessionFactory> sqlSessionFactoryList;

    @Autowired
    private MybatisBatchProperties mybatisBatchProperties;

    @PostConstruct
    public void addPageInterceptor() {
        BatchHelperIntercept interceptor = new BatchHelperIntercept();
        Properties properties = mybatisBatchProperties.getProperties();
        interceptor.setProperties(properties);
        Iterator<SqlSessionFactory> it = this.sqlSessionFactoryList.iterator();

        while(it.hasNext()) {
            SqlSessionFactory sqlSessionFactory = it.next();
            sqlSessionFactory.getConfiguration().addInterceptor(interceptor);
        }
    }
}
