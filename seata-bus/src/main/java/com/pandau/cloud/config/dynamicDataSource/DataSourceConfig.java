package com.pandau.cloud.config.dynamicDataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@Configuration
@MapperScan(basePackages = "com.pandau.cloud.mapper", sqlSessionFactoryRef = "SqlSessionFactory")
public class DataSourceConfig {
    @Autowired
    MasterProperties masterProperties;
    @Autowired
    SlaveProperties slaveProperties;

    @Primary
    @Bean(name = "masterDataSource")
    public DataSource getDateSource1() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(masterProperties.getUsername());
        druidDataSource.setPassword(masterProperties.getPassword());
        druidDataSource.setUrl(masterProperties.getUrl());
        druidDataSource.setFilters("stat,wall");
        druidDataSource.setUseGlobalDataSourceStat(true);
        druidDataSource.setDriverClassName(masterProperties.getDriverClassName());
        return druidDataSource;
    }
    @Bean(name = "slaveDataSource")
    public DataSource getDateSource2() throws SQLException {
        //springboot 自带连接池
        //return DataSourceBuilder.create().build();
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(slaveProperties.getUsername());
        druidDataSource.setPassword(slaveProperties.getPassword());
        druidDataSource.setUrl(slaveProperties.getUrl());
        druidDataSource.setFilters("stat,wall");
        druidDataSource.setUseGlobalDataSourceStat(true);
        druidDataSource.setDriverClassName(slaveProperties.getDriverClassName());
        return druidDataSource;
    }

    @Bean(name = "dynamicDataSource")
    public DynamicDataSource DataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                        @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DataSourceType.DataBaseType.MASTER, masterDataSource);
        targetDataSource.put(DataSourceType.DataBaseType.SLAVE, slaveDataSource);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        dataSource.setDefaultTargetDataSource(masterDataSource);
        return dataSource;
    }
    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory test1SqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dynamicDataSource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:mapping/*.xml"));
        return bean.getObject();
    }

    @Bean
    public ServletRegistrationBean druidServletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        return servletRegistrationBean;
    }

    /**
     * 注册DruidFilter拦截
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<String, String>();
        //设置忽略请求
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.setInitParameters(initParams);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
}
