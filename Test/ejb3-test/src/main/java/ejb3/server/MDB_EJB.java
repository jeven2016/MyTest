package ejb3.server;


import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

@Remote({IMDBRemote.class})
@Stateless(name = "MDB_EJB", mappedName = "MDB_EJB")
public class MDB_EJB implements IHelloWorldRemote {
  @Override
  public String say(String msg) {
    MessageStorageForMDB messageStorageForMDB = MessageStorageForMDB.mess;
    return messageStorageForMDB.getQueue().toString() + ", size=" + messageStorageForMDB.getQueue().size();
  }
}
