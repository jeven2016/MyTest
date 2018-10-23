package zjtech.bmf.inject.zjtech;

import com.alibaba.druid.pool.DruidDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by root on 14-11-25.
 */
public class DruidDatasourceTest {

  @Test
  public void first() {
    DruidDataSource dataSource = new DruidDataSource();
    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    dataSource.setUsername("root");
    dataSource.setPassword("1");
    dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test");
    dataSource.setInitialSize(5);
    dataSource.setMinIdle(1);
    dataSource.setMaxActive(10);
    // 启用监控统计功能  dataSource.setFilters("stat");
    // for mysql
    dataSource.setPoolPreparedStatements(false);

    String sql = "select u.name from user u";

    try {
      Connection connection = dataSource.getConnection();
      ResultSet rs = connection.createStatement().executeQuery(sql);
      while (rs.next()) {
        String name = rs.getString(1);
        System.out.println("name=" + name);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
}
