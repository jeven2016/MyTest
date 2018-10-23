package configure.java;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;

/**
 * If you wish to override logback's default configuration mechanism for whatever reason,
 * you can do so by invoking JoranConfigurator directly.
 */
public class JoranConfiguratorTest {

  final static Logger logger = LoggerFactory.getLogger(JoranConfiguratorTest.class);


  /**
   * 清除历史配置并重置
   */
  public static void main(String[] args) {
    // assume SLF4J is bound to logback in the current environment
    LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();

    try {
      JoranConfigurator configurator = new JoranConfigurator();
      configurator.setContext(context);
      // Call context.reset() to clear any previous configuration, e.g. default
      // configuration. For multi-step configuration, omit calling context.reset().
      context.reset();
      InputStream inputStream = JoranConfiguratorTest.class.getResourceAsStream("/logback-2.xml");

      //重新加载配置文件，并设置日志级别为Error
      configurator.doConfigure(inputStream);
    } catch (JoranException je) {
      // StatusPrinter will handle this
      je.printStackTrace();
    }
    StatusPrinter.printInCaseOfErrorsOrWarnings(context);

    logger.error("Entering application.");

    // 以下日志会被忽略
    logger.info("hello");
    logger.debug("what");
    logger.warn("warn");
    logger.info("Exiting application.");
  }
}
