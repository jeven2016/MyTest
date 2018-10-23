package ejb3.server;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class GlassfishProp {
  public static Properties getProp() {
    return getProp("");
  }

  public static Properties getProp(String ip) {
    Properties props = new Properties();
    props.setProperty("java.naming.factory.initial",
            "com.sun.enterprise.naming.SerialInitContextFactory");
    props.setProperty("java.naming.factory.url.pkgs",
            "com.sun.enterprise.naming");
    props.setProperty("java.naming.factory.state",
            "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

// optional. Defaults to localhost. Only needed if web server is running
// on a different host than the appserver
    if (ip == null || ip.trim().length() == 0) {
      ip = "127.0.0.1";
    }
    props.setProperty("org.omg.CORBA.ORBInitialHost", ip);

// optional. Defaults to 3700. Only needed if target orb port is not 3700.
    props.setProperty("org.omg.CORBA.ORBInitialPort", "3700");
    return props;
  }

  public static Destination getDest(String ip,String lookupName) {
    InitialContext initialContext = null;
    try {
      initialContext = new InitialContext(getProp(ip));
      javax.jms.Destination dest = (javax.jms.Destination) initialContext.lookup(lookupName);
      return dest;
    } catch (NamingException e) {
      e.printStackTrace();
    }
    return null;
  }


  public static ConnectionFactory getConnectionFactory(String ip,String lookupName) {
    InitialContext initialContext = null;
    try {
      initialContext = new InitialContext(getProp(ip));
      ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup(lookupName);
      return connectionFactory;
    } catch (NamingException e) {
      e.printStackTrace();
    }
    return null;
  }
}
