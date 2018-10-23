package ejb3.server;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(
        name = "MDBHellow",
        mappedName = "MyQueue", /** refer to a queue **/
        activationConfig = {
                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "MyQueue")
        },
        description = "A message driven bean"
)
public class MDBHellow implements MessageListener {
  @Override
  public void onMessage(Message msg) {
     MessageStorageForMDB.mess.add("a message:" + msg);
  }
}
