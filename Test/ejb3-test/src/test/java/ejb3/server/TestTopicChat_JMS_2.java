package ejb3.server;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import java.beans.ExceptionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by root on 4/7/14.
 */
public class TestTopicChat_JMS_2 implements MessageListener, ExceptionListener {
  public static void main(String[] args) {
    TestTopicChat_JMS_2 testTopicChat = new TestTopicChat_JMS_2();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String line;

    System.out.println("Please input: ");
    try {
      while ((line = reader.readLine()) != null) {
        testTopicChat.send(line);
        System.out.print("<input>:");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  Destination destination;
  ConnectionFactory connectionFactory;

  public TestTopicChat_JMS_2() {
    try {
      InitialContext initialContext = new InitialContext(GlassfishProp.getProp("127.0.0.1"));

      //startup a server's consumer to listen to same topic
      ITopicChatServer iTopicChatServer = (ITopicChatServer) initialContext.lookup("TopicChatServer");
      iTopicChatServer.startup();

      connectionFactory = (TopicConnectionFactory) initialContext.lookup("MyConnectionFactory");
      //get a topic
      destination = (Topic) initialContext.lookup("MyTopic");
    } catch (Exception e) {
      e.printStackTrace();
    }

    //create a jms context
    try (JMSContext jmsContext = connectionFactory.createContext()) {
      /** create a consumer*/
      jmsContext.createConsumer(destination, null, true).setMessageListener(this);
    } catch (Exception e) {
      e.printStackTrace();
    }


  }

  public void send(String line) {
    //create a jms context
    try (JMSContext jmsContext = connectionFactory.createContext()) {
      /** create a consumer*/
      jmsContext.createProducer().send(destination, line);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


  @Override
  public void onMessage(Message message) {
    try {
      System.out.println(">" + message.getBody(String.class));
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }


  @Override
  public void exceptionThrown(Exception e) {
    System.out.println("exception thrown....");
    e.printStackTrace();
  }
}
