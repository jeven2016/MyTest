package configure.java;


//import org.apache.log4j.*;
//import org.apache.log4j.Logger;
import org.slf4j.*;

public class SimpleConsoleLogger {
  /*
     %m：输出代码中指定的消息。
             %p：输出优先级。
             %r：输入自应用启动到输出该log信息耗费的毫秒数。
             %c：输出所属的类目，通常就是所在类的全名。
             %t：输出产生该日志线程的线程名。
             %n：输出一个回车换行符。Windows平台为“/r/n”，UNIX为“/n”。
             %d：输出日志时间点的日期或时间，默认格式为ISO8601，推荐使用“%d{ABSOLUTE}”，这个输出格式形如：“2007-05-07 18:23:23,500”，符合中国人习惯。
             %l：输出日志事件发生的位置，包括类名、线程名，以及所在代码的行数*/
  /*public void init() {
    BasicConfigurator.configure(); //enough for configuring log4j
    Logger.getRootLogger().setLevel(Level.WARN); //changing log level

    ConsoleAppender console = new ConsoleAppender(); //create appender
    //configure the appender
    String PATTERN = "%d [%p|%c|%C{1}] %m%n";
    console.setLayout(new PatternLayout(PATTERN));
    console.setThreshold(Level.FATAL);
    console.activateOptions();
    //add appender to any Logger (here is root)
    Logger.getRootLogger().addAppender(console);

    FileAppender fa = new FileAppender();
    fa.setName("FileLogger");
    fa.setFile("mylog.log");
    fa.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
    fa.setThreshold(Level.DEBUG);
    fa.setAppend(true);
    fa.activateOptions();

    //add appender to any Logger (here is root)
    Logger.getRootLogger().addAppender(fa);
    //repeat with all other desired appenders

    //删除所有log的配置
//    Logger.getRootLogger().getLoggerRepository().resetConfiguration();
  }*/

  public static void main(String[] args) {
    SimpleConsoleLogger sm = new SimpleConsoleLogger();
//    sm.init();

//    org.slf4j.Logger logger = LoggerFactory.getLogger("FileLogger");
//    logger.debug("test ,now");

  }
}
