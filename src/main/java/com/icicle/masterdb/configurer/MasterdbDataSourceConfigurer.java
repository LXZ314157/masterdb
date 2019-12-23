package com.icicle.masterdb.configurer;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 主数据数据库配置类
 *
 * @author liumingming
 * @version 2017-12-25 11:56.
 */
@Configuration
@MapperScan(basePackages = "com.icicle.masterdb.dao.masterdb", sqlSessionTemplateRef = "masterdbSqlSessionTemplate")
public class MasterdbDataSourceConfigurer {

    @Bean(name = "masterdbDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.masterdb")
    @Primary
    public DataSource masterdbDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "masterdbSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterdbSqlSessionFactory(@Qualifier("masterdbDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:/mapper/masterdb/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "masterdbTransactionManager")
    @Primary
    public DataSourceTransactionManager masterdbTransactionManager(@Qualifier("masterdbDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "masterdbSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate masterdbSqlSessionTemplate(@Qualifier("masterdbSqlSessionFactory")
                                                                 SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
