package configure.java;


import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class HelloWorld {
  final static Logger adminLogger = LoggerFactory.getLogger("admin");
  Logger logger = LoggerFactory.getLogger(HelloWorld.class);
  Logger loggerTest = LoggerFactory.getLogger("test1");

  public static void main(String[] args) {
    HelloWorld hw = new HelloWorld();
//    hw.printHellow();
    hw.printAdminLog();
  }

  /**
   * Let us begin by discussing the initialization steps that logback follows to try to configure itself:
   * <p>
   * Logback tries to find a file called logback.groovy in the classpath.
   * <p>
   * If no such file is found, logback tries to find a file called logback-test.xml in the classpath.
   * <p>
   * If no such file is found, it checks for the file logback.xml in the classpath..
   * <p>
   * If no such file is found, and the executing JVM has the ServiceLoader (JDK 6 and above) the ServiceLoader will
   * be used to resolve an implementation of com.qos.logback.classic.spi.Configurator. The first implementation found
   * will be used. See ServiceLoader documentation for more details.
   * <p>
   * If none of the above succeeds, logback configures itself automatically using the BasicConfigurator which will cause
   * logging output to be directed to the console.
   * <p>
   * The fourth and last step is meant to provide a default (but very basic) logging functionality in the absence of a
   * configuration file.
   */
  void printHellow() {
    logger.debug("hellow world begin....");
    logger.info("it's a info message");
    logger.warn("it's a warning message");
    loggerTest.warn("from test1 :....");
    loggerTest.info("info from test1 :....");

    // print internal state
    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    StatusPrinter.print(lc);
  }

  void printAdminLog() {
    for (long i = 0; i < 1000000000; i++) {
      adminLogger.warn("/**\n" +
              "   * Let us begin by discussing the initialization steps that logback follows to try to configure itself:\n" +
              "   * <p>\n" +
              "   * Logback tries to find a file called logback.groovy in the classpath.\n" +
              "   * <p>\n" +
              "   * If no such file is found, logback tries to find a file called logback-test.xml in the classpath.\n" +
              "   * <p>\n" +
              "   * If no such file is found, it checks for the file logback.xml in the classpath..\n" +
              "   * <p>\n" +
              "   * If no such file is found, and the executing JVM has the ServiceLoader (JDK 6 and above) the ServiceLoader will\n" +
              "   * be used to resolve an implementation of com.qos.logback.classic.spi.Configurator. The first implementation found\n" +
              "   * will be used. See ServiceLoader documentation for more details.\n" +
              "   * <p>\n" +
              "   * If none of the above succeeds, logback configures itself automatically using the BasicConfigurator which will cause\n" +
              "   * logging output to be directed to the console.\n" +
              "   * <p>\n" +
              "   * The fourth and last step is meant to provide a default (but very basic) logging functionality in the absence of a\n" +
              "   * configuration file.\n" +
              "   */");

      if (i != 0 && i % 1000 == 0) {
        try {
          TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
