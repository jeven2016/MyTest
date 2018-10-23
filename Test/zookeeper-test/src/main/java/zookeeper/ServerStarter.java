package zookeeper;

import org.apache.zookeeper.server.ServerConfig;
import org.apache.zookeeper.server.ZooKeeperServerMain;
import org.apache.zookeeper.server.quorum.QuorumPeerConfig;

import java.io.IOException;
import java.util.Properties;

/**
 * 通过Java程序启动Zookeeper
 */
public class ServerStarter {

  public static void main(String[] args) {
    start();
  }

  public static void start() {
    ZooKeeperServerMain serverMain = new ZooKeeperServerMain();

    Properties prop = new Properties();
    prop.setProperty("tickTime", "2000");
    prop.setProperty("initLimit", "20");
    prop.setProperty("syncLimit", "15");
    prop.setProperty("dataDir", "/opt/install/zookeeper-3.4.6/conf/data_test");
    prop.setProperty("clientPort", "2181");
    prop.setProperty("maxClientCnxns", "60");
    prop.setProperty("autopurge.snapRetainCount", "3");
    prop.setProperty("autopurge.purgeInterval", "1");

    QuorumPeerConfig qpConfig = new QuorumPeerConfig();
    try {
      qpConfig.parseProperties(prop);
    } catch (Exception e) {
      e.printStackTrace();
    }
    ServerConfig config = new ServerConfig();
    config.readFrom(qpConfig);
    try {
      serverMain.runFromConfig(config);
    } catch (IOException e) {
      e.printStackTrace();
    }

  }
}
