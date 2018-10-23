package ejb3.server;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by root on 3/24/14.
 */
public class TestEJBCallEjb {

  public static void main(String[] args) {
    doCall();
  }

  static void doCall() {
    String ip = "localhost";
    Properties properties = GlassfishProp.getProp(ip);

    try {
      InitialContext initialContext = new InitialContext(properties);
      Object obj = initialContext.lookup("StateCal");
      System.out.println(obj.getClass().getName());
      IStateCalRemote stateCal = (IStateCalRemote) obj;
      System.out.println(stateCal.getInfo());
    } catch (NamingException e) {
      e.printStackTrace();
    }
  }
}
