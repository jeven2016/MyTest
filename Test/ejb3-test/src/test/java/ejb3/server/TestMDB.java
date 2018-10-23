package ejb3.server;


import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.xml.soap.Text;
import java.util.Properties;

/**
 * Created by root on 3/26/14.
 */
public class TestMDB {

  public static void main(String[] args) {
    TestMDB testMDB = new TestMDB();
    testMDB.sendMsg();
    try {
      Thread.currentThread().sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    testMDB.printResult();
  }

  public void sendMsg() {
    //send a message
    Properties properties = GlassfishProp.getProp();

    try {
      InitialContext initialContext = new InitialContext(properties);
      QueueConnectionFactory connectionFactory = (QueueConnectionFactory) initialContext.lookup("MyConnectionFactory");
      QueueConnection conn = connectionFactory.createQueueConnection();

      Session session = conn.createSession(false, TopicSession.AUTO_ACKNOWLEDGE);

      Destination dest = (Queue) initialContext.lookup("MyQueue");


      MessageProducer messageProducer = session.createProducer(dest);

      TextMessage textMessage = session.createTextMessage();
      textMessage.setText("from Test");

      messageProducer.send(textMessage);

      session.close();
      conn.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void printResult(){
    InitialContext ctx = null;
    try {
      ctx = new InitialContext(GlassfishProp.getProp());
      IMDBRemote remote = (IMDBRemote) ctx.lookup("MDB_EJB");
      String msg = remote.say("");
      System.out.println(msg);

    } catch (NamingException e) {
      e.printStackTrace();
    }


  }
}
