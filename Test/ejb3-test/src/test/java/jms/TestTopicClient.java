package jms;

import ejb3.server.GlassfishProp;
import junit.framework.Assert;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.io.FileOutputStream;
import java.io.IOException;


public class TestTopicClient implements ICmdLineExecutor, MessageListener {
  static String ip = "127.0.0.1";

  public static void main(String[] args) {
    TestTopicClient testTopicClient = new TestTopicClient();
    testTopicClient.startListen();
    CmdServer cmdServer = new CmdServer(testTopicClient);
    cmdServer.start();
  }

  Destination respDestination;

  public void startListen() {
    ConnectionFactory connectionFactory = GlassfishProp.getConnectionFactory(ip, "MyConnectionFactory");
    respDestination = GlassfishProp.getDest(ip, "responseChatTopic");

    try (JMSContext jmsContext = connectionFactory.createContext()) {
      jmsContext.createConsumer(respDestination).setMessageListener(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public Object execute(String... msgs) {
    for (String msg : msgs) {
      sendAndReceive(msg);
    }
    return null;
  }


  public void sendAndReceive(String msg) {
    ConnectionFactory connectionFactory = GlassfishProp.getConnectionFactory(ip, "MyConnectionFactory");
    Destination destination = GlassfishProp.getDest(ip, "requestChatTopic");
    //Destination respDestination = GlassfishProp.getDest(ip, "responseChatTopic");

    Assert.assertNotNull(destination);
    Assert.assertNotNull(respDestination);
    Assert.assertNotNull(connectionFactory);

    try (JMSContext jmsContext = connectionFactory.createContext()) {
      jmsContext.createProducer().setJMSReplyTo(respDestination).send(destination, msg);
      Message mess = jmsContext.createConsumer(respDestination).receive();
      if (mess != null) {
        System.out.println("out+" + mess.getBody(String.class));
      } else {
        System.out.println("no data.");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


  @Override
  public void onMessage(Message message) {
    if (message instanceof TextMessage) {
      try {
        System.out.println(message.getBody(String.class));
      } catch (JMSException e) {
        e.printStackTrace();
      }
    } else {
      System.out.println("not text message");
    }
  }

}
