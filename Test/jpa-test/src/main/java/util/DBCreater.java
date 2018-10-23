package util;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by root on 14-11-24.
 */
public class DBCreater {
  private static final String DRIVER = "org.h2.Driver";
  private static final String PASS = "admin123";

  public static void main(String[] args) {
    new DBCreater().create();
//    testPath();
  }

  public void create() {
    try {
      Path path = Paths.get("");
      Path filePath = path.resolve("localdb");
      System.out.println(filePath.toAbsolutePath().toString());
      Class.forName(DRIVER);
      Connection conn = DriverManager.getConnection("jdbc:h2:" + filePath.toAbsolutePath().toString(), "admin", "admin123");
      conn.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void testPath() {
    Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
    for (Path name : dirs) {
      System.err.println(name);
    }
    System.out.println(System.getProperty("java.vendor").toString());
    System.out.println(System.getProperty("java.version").toString());
    System.out.println(System.getProperty("java.vm.name"));
  }


}
