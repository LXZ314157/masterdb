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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author liumingming
 * @version 2018-01-30 11:27.
 */
@Configuration
@MapperScan(basePackages = "com.icicle.masterdb.dao.imagesearch", sqlSessionTemplateRef = "imageSearchSqlSessionTemplate")
public class ImageSearchDataSourceConfigurer {

    @Bean(name = "imageSearchDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.imagesearch")
    public DataSource imageSearchDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "imageSearchSqlSessionFactory")
    public SqlSessionFactory imageSearchSqlSessionFactory(@Qualifier("imageSearchDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:/mapper/imagesearch/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "imageSearchTransactionManager")
    public DataSourceTransactionManager imageSearchTransactionManager(@Qualifier("imageSearchDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "imageSearchSqlSessionTemplate")
    public SqlSessionTemplate imageSearchSqlSessionTemplate(@Qualifier("imageSearchSqlSessionFactory")
                                                                    SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
