package ejb3.server;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by root on 3/26/14.
 */
public class MessageStorageForMDB {
  private Queue<String> queue = new ConcurrentLinkedQueue();
  public static final MessageStorageForMDB mess = new MessageStorageForMDB();

  public void add(String mesg){
    queue.add(mesg);
  }

  public Queue<String> getQueue(){
    return this.queue;
  }
}
