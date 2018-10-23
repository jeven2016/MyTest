package kafka.firstexample;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;


public class HellowordProducer {
    private Random random = new Random();

    public KafkaProducer initProducer() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.ACKS_CONFIG, "1");


//        properties.put("metadata.broker.list", "127.0.0.1:9092");
//        properties.put("serializer.class", "kafka.serializer.StringEncoder");
        properties.put("request.required.acks", "1");

        KafkaProducer<Integer, String> producerConfig = new KafkaProducer<>(properties);
        return producerConfig;
    }

    private ProducerRecord<Integer, String> getRecord(boolean isSync) {
        int nextValue = random.nextInt();
        String msg = String.format("This is a message(%d, %b).", nextValue, isSync);
        ProducerRecord<Integer, String> record = new ProducerRecord<Integer, String>("mytopic", msg);
        return record;
    }


    public static void main(String[] args) {
        HellowordProducer helloProducer = new HellowordProducer();
        KafkaProducer<Integer, String> producer = helloProducer.initProducer();

        //异步
        producer.send(helloProducer.getRecord(false));

        //with callback
        producer.send(helloProducer.getRecord(false), new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e != null) {
                    e.printStackTrace();
                }
            }
        });


        try {
            //同步等待
            RecordMetadata recordMetadata = producer.send(helloProducer.getRecord(true)).get();
            System.out.println(recordMetadata.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        producer.close();

        //在kafka/bin目录下使用console工具接受发送的message
        // ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic mytopic --from-beginning
    }
}
