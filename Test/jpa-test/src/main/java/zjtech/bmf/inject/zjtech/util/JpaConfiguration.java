/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package zjtech.bmf.inject.zjtech.util;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.spi.PersistenceUnitInfo;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan("com.zjtech.dao")
@EnableJpaRepositories(basePackages = "com.zjtech.dao.rep")
public class JpaConfiguration implements ApplicationContextAware {

  ApplicationContext ctx;

  @Bean(name = "dataSource")
  public DataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUsername("root");
    dataSource.setPassword("1");
    dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/smf_admin");
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
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan("com.zjtech.dao");
    sessionFactory.setHibernateProperties(getHibernateProperties());
    return sessionFactory;
  }

  Properties getHibernateProperties() {
    Properties props = new Properties();
    props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
//    props.getProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
    props.setProperty("hibernate.connection.username", "root");
    props.setProperty("hibernate.connection.password", "1");
//    props.setProperty("hibernate.archive.autodetection", "class"); //会额外触发查询语句
    props.setProperty("hibernate.show_sql", "true");
    props.setProperty("hibernate.format_sql", "true");
    props.setProperty("hbm2ddl.auto", "update");
    props.setProperty("Hibernate.default_schema", "smf_admin");
    props.setProperty("hibernate.max_fetch_depth", "3");
    props.setProperty("connection.autocommit", "true");
//    props.setProperty("hibernate.globally_quoted_identifiers", "true");
    return props;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    DataSource ds = dataSource();
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setDatabase(Database.MYSQL);
    vendorAdapter.setGenerateDdl(true);
    vendorAdapter.setShowSql(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setJpaProperties(getHibernateProperties());
//    factory.setPackagesToScan(getClass().getPackage().getName());
    factory.setPackagesToScan("com.zjtech.dao.entity");// this is required indeed
    factory.setDataSource(ds);
    PersistenceUnitInfo persistenceUnitInfo = factory.getPersistenceUnitInfo();
    System.out.println(persistenceUnitInfo);

    return factory;
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory().getObject());
    txManager.setDataSource(dataSource());
    return txManager;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ctx = applicationContext;
  }
}
