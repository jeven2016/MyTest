package jms;

import ejb3.server.IStateCalRemote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by root on 4/8/14.
 */
public class CmdServer {
  ICmdLineExecutor executor;

  public CmdServer(ICmdLineExecutor executor) {
    this.executor = executor;
  }

  public void start() {
    System.out.println("Please input: ");
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String line;
    try {
      while ((line = reader.readLine()) != null) {
        executor.execute(line);
        System.out.print("<input>:");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
