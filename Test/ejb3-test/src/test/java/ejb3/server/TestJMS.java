package ejb3.server;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by root on 3/24/14.
 */
public class TestJMS {
  private static ConnectionFactory connectionFactory;
  private static Queue queue;
  public static void main(String[] args) throws NamingException {
    Connection connection = null;
    Session session = null;
    MessageConsumer consumer = null;
    TextMessage message = null;

    Properties env = new Properties();

    //glassfish3V
    env.put(Context.PROVIDER_URL, "iiop://localhost:8080");
    InitialContext jndi = new InitialContext(env);
    connectionFactory = (ConnectionFactory) jndi.lookup("jms/ConnectionFactory");
    queue = (Queue) jndi.lookup("jms/Queue"); // put your Queue here


    try {
      connection = connectionFactory.createConnection();
      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      consumer = session.createConsumer(queue);
      connection.start();

      while (true) {
        Message m = consumer.receive(1);

        if (m != null) {
          if (m instanceof TextMessage) {
            message = (TextMessage) m;
            System.out.println(
                    "Reading message: " + message.getText());
          } else {
            break;
          }
        }
      }
    } catch (JMSException e) {
      System.err.println("Exception occurred: " + e.toString());
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (JMSException e) {
        }
      }
    }
  }
}
