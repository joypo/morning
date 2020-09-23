package com.example.morning.sink;

import com.alibaba.fastjson.JSON;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * @author sunx
 * @date 2020/8/25
 */
public class Main {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("zookeeper.connect", "localhost:2181");
        props.put("group.id", "metric-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");  //key 反序列化
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "latest"); //value 反序列化

        SingleOutputStreamOperator<Student> stream = env
                .addSource(new FlinkKafkaConsumer<>("student", new SimpleStringSchema(), props))
                .setParallelism(1)
                .map(a -> JSON.parseObject(a, Student.class));
        stream.print(); //把从 kafka 读取到的数据打印在控制台
//        PrintSinkFunction printSinkFunction = new PrintSinkFunction<>();
//        printSinkFunction.setTargetToStandardErr();
//        stream.addSink(printSinkFunction);
        stream.addSink(new SinkToMySQL());
        env.execute("Flink add sink mysql");

    }
}
