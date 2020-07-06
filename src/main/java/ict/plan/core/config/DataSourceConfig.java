package ict.plan.core.config;

import ict.plan.core.datasource.MultiDataSourceBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceConfig {
    //开始创建多数据源
    @Bean
    public DataSource getDataSource() {
        MultiDataSourceBean dataSource = new MultiDataSourceBean();
        return dataSource;
    }
}