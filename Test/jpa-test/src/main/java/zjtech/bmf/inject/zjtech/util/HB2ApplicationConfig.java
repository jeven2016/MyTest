package zjtech.bmf.inject.zjtech.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaRepositories("com.zjtech.dao")
@ComponentScan("com.zjtech.dao")
@EnableTransactionManagement
//@ImportResource("classpath:spring/spring-bmf.xml")
public class HB2ApplicationConfig {


  //为了在类中使用hibernate原生API
  @Bean
  public LocalSessionFactoryBean sessionFactory(){
    LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
    localSessionFactoryBean.setDataSource(dataSource());
    localSessionFactoryBean.setPackagesToScan("com.zjtech.dao");
    return localSessionFactoryBean;
  }

  @Bean
  public DataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName("org.h2.Driver");
    dataSource.setUsername("admin");
    dataSource.setPassword("admin123");
    dataSource.setUrl("jdbc:h2:file:/opt/projects/allProjects/smf_admin");
    dataSource.setInitialSize(5);
    dataSource.setMinIdle(1);
    dataSource.setMaxActive(10);
    dataSource.setDefaultAutoCommit(true);
    // 启用监控统计功能  dataSource.setFilters("stat");
    // for mysql
    dataSource.setPoolPreparedStatements(false);

    return dataSource;
  }

  @Bean
  public EntityManagerFactory entityManagerFactory() {

    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setGenerateDdl(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setPackagesToScan("com.acme.domain");
    factory.setDataSource(dataSource());//spring interceptor will intercept this method and return a unique data source
    factory.setJpaProperties(getHibernateProperties());
    factory.setPackagesToScan("com.zjtech.dao.entity");// this is required indeed
    factory.afterPropertiesSet();
    return factory.getObject();
  }

  Properties getHibernateProperties() {
    Properties props = new Properties();
    props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
//    props.setProperty("hibernate.connection.url", "jdbc:h2:file:/opt/projects/allProjects/smf_admin");
    props.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
//    props.setProperty("hibernate.connection.username", "admin");
//    props.setProperty("hibernate.connection.password", "admin123");
    props.setProperty("hibernate.show_sql", "true");
    props.setProperty("Hibernate.default_schema", "smf_admin");
    props.setProperty("hibernate.format_sql", "true");
    props.setProperty("hbm2ddl.auto", "update");
    props.setProperty("hibernate.max_fetch_depth", "3");
    return props;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory());
    txManager.setDataSource(dataSource());
    return txManager;
  }
}