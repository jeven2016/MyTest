package ejb3.server;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by root on 3/31/14.
 */
public class TestTopicChat implements MessageListener {
  public static void main(String[] args) {
    TestTopicChat testTopicChat = new TestTopicChat();
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

  /*public void test(String msg) {
    try {
      InitialContext initialContext = new InitialContext(GlassfishProp.getProp("127.0.0.1"));
      ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup("MyConnectionFactory");

      //create a jms context
      JMSContext jmsContext = connectionFactory.createContext();

      //get a topic
      Destination destination = (Destination) initialContext.lookup("MyTopic");

      jmsContext.createConsumer(destination, null, true).setMessageListener(this);

      TextMessage textMessage = jmsContext.createTextMessage();
      textMessage.setText(msg);
      jmsContext.createProducer().send(destination, textMessage);

      jmsContext.close();
    } catch (Exception e) {
      e.printStackTrace();
    }


  }
*/

  public void send(String msg) {
    try {
      TextMessage textMessage = session.createTextMessage();
      textMessage.setText(msg);

      /**create a publisher*/
      TopicPublisher topicPublisher = session.createPublisher(destination);
      topicPublisher.publish(textMessage);

    } catch (JMSException e) {
      e.printStackTrace();
    }

  }

  TopicSession session;
  Topic destination;

  public TestTopicChat() {
    try {
      InitialContext initialContext = new InitialContext(GlassfishProp.getProp("127.0.0.1"));

      //startup a server's consumer to listen to same topic
      ITopicChatServer iTopicChatServer = (ITopicChatServer)initialContext.lookup("TopicChatServer");
      iTopicChatServer.startup();

      TopicConnectionFactory connectionFactory = (TopicConnectionFactory) initialContext.lookup("MyConnectionFactory");
      //get a topic
      destination = (Topic) initialContext.lookup("MyTopic");

      //create a jms context
      TopicConnection topicConnection = connectionFactory.createTopicConnection();
      session = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
      TopicSession consumerSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);


      /** create a consumer*/
      TopicSubscriber messageConsumer = consumerSession.createSubscriber(destination, null, true);
      messageConsumer.setMessageListener(this);


      topicConnection.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Override
  public void onMessage(Message message) {
    try {
      System.out.println("> " + ((TextMessage) message).getText());
    } catch (JMSException e) {
      e.printStackTrace();
    }
  }
}
