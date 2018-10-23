package configure.java;


import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.ViewStatusMessagesServlet;
import ch.qos.logback.core.status.OnConsoleStatusListener;
import ch.qos.logback.core.status.StatusManager;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebLogAccess {
  static Logger logger = LoggerFactory.getLogger(WebLogAccess.class);

  public static void main(String[] args) {
    startServer();
  }

  static void startServer() {
    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
    StatusManager statusManager = lc.getStatusManager();
    OnConsoleStatusListener onConsoleListener = new OnConsoleStatusListener();
    statusManager.add(onConsoleListener);

    Server server = new Server();
    ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
    context.setContextPath("/");
    server.setHandler(context);
    // http://localhost:8080/goodbye/kongxx
    context.addServlet(new ServletHolder(new ViewStatusMessagesServlet()), "/status");

    try {
      server.start();
      logger.info("comeone......");
      server.join();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  static void startByWar() {
    Server server = new Server(8080);

    WebAppContext context = new WebAppContext();
    context.setContextPath("/myapp");
    context.setDescriptor("E:/share/test/struts2-blank/WEB-INF/web.xml");
    context.setResourceBase("E:/share/test/struts2-blank");
    context.setParentLoaderPriority(true);
    server.setHandler(context);

    try {
      server.start();
      server.join();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
