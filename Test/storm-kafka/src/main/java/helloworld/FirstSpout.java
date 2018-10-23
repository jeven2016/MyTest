package helloworld;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FirstSpout extends BaseRichSpout {
    private SpoutOutputCollector collector;
    private static Map<String, String> msgPool = new HashMap();

    static {
        msgPool.put("0", "this is message 0");
        msgPool.put("1", "this is message 1");
        msgPool.put("2", "this is message 2");
        msgPool.put("3", "this is message 3");
        msgPool.put("4", "this is message 4");
        msgPool.put("5", "this is message 5");
        msgPool.put("6", "this is message 6");
        msgPool.put("7", "this is message 7");
        msgPool.put("8", "this is message 8");
        msgPool.put("9", "this is message 9");
    }


    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        collector = spoutOutputCollector;
    }

    @Override
    public void nextTuple() {
        Random random = new Random();
        int key = random.nextInt(10);
        String msg = msgPool.get(key + "");

        //获取到数据后讲数据Stream 提交至Bolt处理
        collector.emit(new Values(msg));

    }

    //提交的数据对外展示的field名
    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("msg"));
    }
}
