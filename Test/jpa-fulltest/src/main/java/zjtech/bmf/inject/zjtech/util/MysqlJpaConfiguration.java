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
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.persistence.spi.PersistenceUnitInfo;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan("com.zjtech")
@EnableJpaRepositories(basePackages = "com.zjtech")
public class MysqlJpaConfiguration implements ApplicationContextAware {

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
    sessionFactory.setPackagesToScan("com.zjtech");
    sessionFactory.setHibernateProperties(getHibernateProperties());
    return sessionFactory;
  }

  Properties getHibernateProperties() {
  /*
    在persistence.xml中若配置了<property name="hibernate.hbm2ddl.auto" value="create" />，那么每次访问数据库都会创建新的表。导致始终只插入最后一条数据。可能值有
    none                   无
    validate               加载hibernate时，验证创建数据库表结构
    create                  每次加载hibernate，重新创建数据库表结构，这就是导致数据库表数据丢失的原因。
    create-drop        加载hibernate时创建，退出是删除表结构
    update                 加载hibernate自动更新数据库结构*/

    Properties props = new Properties();
    props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLInnoDBDialect");
    props.getProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
    props.setProperty("hibernate.connection.username", "root");
    props.setProperty("hibernate.connection.password", "1");
//    props.setProperty("hibernate.archive.autodetection", "class"); //会额外触发查询语句
    props.setProperty("hibernate.show_sql", "true");
    props.setProperty("hibernate.use_sql_comments", "true"); //是否打印出注释
    props.setProperty("hibernate.format_sql", "true");
//    props.setProperty("hibernate.generateDdl", "true");      //可以自动生成表
//    props.setProperty("hbm2ddl.auto", "none"); //可以自动生成表

    props.setProperty("Hibernate.default_schema", "smf_admin");
    props.setProperty("hibernate.max_fetch_depth", "3");
    props.setProperty("connection.autocommit", "true");
    props.setProperty("hibernate.globally_quoted_identifiers", "true");
    return props;
  }

  @Bean
  public EntityManagerFactory entityManagerFactory() {
    DataSource ds = dataSource();
    HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
    vendorAdapter.setDatabase(Database.MYSQL);
    vendorAdapter.setGenerateDdl(false); //是否更新DDL
    vendorAdapter.setShowSql(true);

    LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
    factory.setJpaVendorAdapter(vendorAdapter);
    factory.setJpaProperties(getHibernateProperties());
//    factory.setPackagesToScan(getClass().getPackage().getName());
    factory.setPackagesToScan("com.zjtech");// this is required indeed
    factory.setDataSource(ds);
    factory.afterPropertiesSet();

//    PersistenceUnitInfo persistenceUnitInfo = factory.getPersistenceUnitInfo();
    return factory.getObject();
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    JpaTransactionManager txManager = new JpaTransactionManager();
    txManager.setEntityManagerFactory(entityManagerFactory());
    txManager.setDataSource(dataSource());
    return txManager;
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    ctx = applicationContext;
  }
}
