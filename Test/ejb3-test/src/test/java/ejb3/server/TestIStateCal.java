package ejb3.server;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * 测试SessionBean
 */
public class TestIStateCal {
  public static void main(String[] args) throws NamingException {
    doTest();
  }

  static void doTest() throws NamingException {
    Properties prop = GlassfishProp.getProp();
    InitialContext context = new InitialContext(prop);

    final IStateCalLocal stateCalRemote = (IStateCalLocal) context.lookup("StateCal");
    for (int i = 0; i < 10; i++) {

      Thread th = new Thread(new Runnable() {
        @Override
        public void run() {
          int val = stateCalRemote.add();

          String name = Thread.currentThread().getName();
          System.out.println(name+",add value="+ val);
        }
      });
      th.start();
    }

    for (int i = 0; i < 3; i++) {

      Thread th = new Thread(new Runnable() {
        @Override
        public void run() {
          int val = stateCalRemote.del();

          String name = Thread.currentThread().getName();
          System.out.println(name+",del value="+ val);
        }
      });
      th.start();
    }

    IStateCalLocal stateCalRemote2 = (IStateCalLocal) context.lookup("StateCal");
    System.out.println(stateCalRemote2.add());

  }
}
