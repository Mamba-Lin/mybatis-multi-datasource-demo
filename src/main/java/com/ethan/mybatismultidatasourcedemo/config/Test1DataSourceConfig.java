package com.ethan.mybatismultidatasourcedemo.config;

import com.sun.xml.internal.bind.v2.util.DataSourceSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @ClassName Test1DataSourceConfig
 * @Description
 * 1.借助@ConfigurationProperties来获取数据源配置
 * 2.借助DataSourceProperties创建DataSource实例
 * 3.基于DataSource创建SqlSessionfactory，并指定mapper对应的xml文件路径
 * 4.创建new SqlSessionTemplate(SqlSessionFactory)
 * @Author Yutao.Lin
 * @Date 1/22/21 1:23 PM
 * @Version 1.0
 **/
//表示这个类为一个配置类
@Configuration
//配置mybatis接口类的位置
@MapperScan(basePackages = "com.ethan.mybatismultidatasourcedemo.test1.mapper", sqlSessionFactoryRef = "test1SqlSessionFactory")
public class Test1DataSourceConfig {


    //表示这个数据源是默认数据源，配置多数据源时必须要加，不然spring不知道哪个是默认配置数据源
    @Primary
    //将这个对象放入spring容器中
    @Bean(name = "test1DataSourceProperties")
    //读取application.yml中的配置参数映射成一个对象，prefix表示参数的前缀，也可以通过@value单独注入配置
    @ConfigurationProperties(prefix = "spring.datasource.multi-source-test1")
    public DataSourceProperties test1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "test1DataSource")
    //@Qualifier表示查找spring容器中名字为test1DataSourceProperties的对象
    public DataSource test1DataSource(@Qualifier("test1DataSourceProperties") DataSourceProperties test1DataSourceProperties) {
        return test1DataSourceProperties.initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name = "test1SqlSessionFactory")
    public SqlSessionFactory test1SqlSessionFactory(DataSource test1DataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(test1DataSource);
        //该属性必须设置，不然将报错
        bean.setMapperLocations(
                //设置mybatis的xml位置
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/test1/*.xml")
        );
        return bean.getObject();
    }

    @Primary
    @Bean(name = "test1SqlSessionTemplate")
    public SqlSessionTemplate test1SqlSessionTemplate(SqlSessionFactory test1SqlSessionFactory){
        return new SqlSessionTemplate(test1SqlSessionFactory);
    }


}