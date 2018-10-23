package helloworld;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Created by root on 16-10-17.
 */
public class FirstTopology {

    public static void main(String[] args) {
        init();
    }

    private static void init() {
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("FirstSpout", new FirstSpout(), 2);
        builder.setBolt("FirstBolt", new FirstBolt(), 4).shuffleGrouping("FirstSpout");

        Config config = new Config();
        config.setDebug(true);

        //use local cluster to deploy this topology
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("FirstTopology", config, builder.createTopology());

        try {
            TimeUnit.SECONDS.wait(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        localCluster.killTopology("FirstTopology");
        localCluster.shutdown();
    }
}
