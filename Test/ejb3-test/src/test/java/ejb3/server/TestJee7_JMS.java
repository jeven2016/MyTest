package ejb3.server;

import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.naming.InitialContext;

/**
 * Created by root on 3/31/14.
 */
public class TestJee7_JMS {

  public void sendMsg(){
    try {
      InitialContext initialContext = new InitialContext(GlassfishProp.getProp("localhost"));
      QueueConnectionFactory connectionFactory = (QueueConnectionFactory) initialContext.lookup("MyConnectionFactory");
      JMSContext jmsContext = connectionFactory.createContext();
//      jmsContext.
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
