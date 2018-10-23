package ejb3.server;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;

/*@MessageDriven(
        name = "TopicChatServer",
      //  mappedName = "MyTopic" ,   mapped to queue or topic
        activationConfig = {
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "MyTopic")
        }
) */
@Stateless(name = "TopicChatServer",
        mappedName = "TopicChatServer")
@Remote(ITopicChatServer.class)
public class TopicChatServer implements MessageListener, ITopicChatServer {

  @Resource(mappedName = "MyTopic")
  private Topic myTopic;

  @Resource(mappedName = "MyConnectionFactory")
  private ConnectionFactory connectionFactory;

  private TopicSession topicSession;

  TopicPublisher topicPublisher;

 /*  @Override
 public void onMessage(Message message) {
    try(JMSContext context = connectionFactory.createContext()){
      TextMessage textMessage = context.createTextMessage();

      JMSConsumer consumer = context.createConsumer(myTopic,null,true);
      consumer.setMessageListener(this);

      textMessage.setText("response from server: " + textMessage.getText());
      context.createProducer().send(myTopic,textMessage);

    } catch (Exception exp){
      exp.printStackTrace();
    }
  }*/

  @Override
  public void onMessage(Message message) {
    System.out.println("entering mdb now");
    try {
      String msg = " from server: " + ((TextMessage) message).getText();

      TextMessage textMessage = topicSession.createTextMessage();
      textMessage.setText(msg);

      /**create a publisher*/
      if (topicPublisher == null)
        topicPublisher = topicSession.createPublisher(myTopic);

      topicPublisher.publish(textMessage);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void startup() {
    TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) connectionFactory;
    TopicConnection topicConnection = null;
    try {
      topicConnection = topicConnectionFactory.createTopicConnection();

      topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);


      /** create a consumer*/
      TopicSubscriber messageConsumer = topicSession.createSubscriber(myTopic, null, true);
      messageConsumer.setMessageListener(this);
      topicConnection.start();
    } catch (JMSException e) {
      e.printStackTrace();
    }


  }
}
