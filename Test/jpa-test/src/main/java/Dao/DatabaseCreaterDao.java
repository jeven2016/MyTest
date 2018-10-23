package Dao;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by root on 14-11-21.
 */
public class DatabaseCreaterDao {
  public static void main(String[] a)
          throws Exception {
    Path path = Paths.get(".");
    System.out.println(path.toAbsolutePath().toString());
/*

    Class.forName("org.h2.Driver");
    Connection conn = DriverManager.
            getConnection("jdbc:h2:~/test", "sa", "");
    // add application code here
    conn.close();*/
  }
}
