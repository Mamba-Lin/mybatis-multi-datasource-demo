package com.ethan.mybatismultidatasourcedemo.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @ClassName Test2DataSourceConfig
 * @Description
 * 1.借助@ConfigurationProperties来获取数据源配置
 * 2.借助DataSourceProperties创建DataSource实例
 * 3.基于DataSource创建SqlSessionfactory，并指定mapper对应的xml文件路径
 * 4.创建new SqlSessionTemplate(SqlSessionFactory)
 * @Author Yutao.Lin
 * @Date 1/22/21 1:36 PM
 * @Version 1.0
 **/
//表示这个类为一个配置类
@Configuration
//配置mybatis接口类的位置
@MapperScan(basePackages = "com.ethan.mybatismultidatasourcedemo.test2.mapper", sqlSessionFactoryRef = "test2SqlSessionFactory")
public class Test2DataSourceConfig {
    @Bean(name = "test2DataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.multi-source-test2")
    public DataSourceProperties test2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "test2DataSource")
    public DataSource testDataSource(@Qualifier("test2DataSourceProperties") DataSourceProperties test2DataSourceProperties) {
        return test2DataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Bean("test2SqlSessionFactory")
    public SqlSessionFactory test2SqlSessionFactory(@Qualifier("test2DataSource") DataSource test2DataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(test2DataSource);
        bean.setMapperLocations(
                // 设置mybatis的xml所在位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/test2/*.xml"));
        return bean.getObject();
    }

    @Bean("test2SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("test2SqlSessionFactory") SqlSessionFactory test2SqlSessionFactory) {
        return new SqlSessionTemplate(test2SqlSessionFactory);
    }
}