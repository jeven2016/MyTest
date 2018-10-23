package jms;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

@MessageDriven(
        name = "TopicServerMDB",
        mappedName = "requestChatTopic",
        activationConfig = {
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
        }
)
public class TopicServer implements MessageListener {
  private Logger logger = Logger.getLogger(TopicServer.class.getName());

  /**
   * @JMSConnectionFactory("MyConnectionFactory") : This will hit a nullPoint exception*
   */
  @Resource(mappedName = "MyConnectionFactory")
  private ConnectionFactory connectionFactory;

  @Override
  public void onMessage(Message message) {
    //use temp topic
    try {
      Destination destFromClient = message.getJMSReplyTo();

      if (message instanceof TextMessage) {
        sendTextMsg(message, destFromClient);
      }

    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private void sendTextMsg(Message message, Destination destFromClient) throws javax.jms.JMSException {
    String msg = message.getBody(String.class);

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Calendar calendar = dateFormat.getCalendar();
    Date date = calendar.getTime();
    String formatDate = dateFormat.format(date);

    try (JMSContext jmsContext = connectionFactory.createContext()) {
      String msgForSend = "From server: " + formatDate + " " + msg;
      jmsContext.createProducer().send(destFromClient, msgForSend);
      logger.warning("sended msg: " + msgForSend);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
