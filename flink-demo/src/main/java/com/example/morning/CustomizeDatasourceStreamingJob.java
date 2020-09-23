package com.example.morning;

import com.example.morning.datasource.SourceFromMySQL;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author sunx
 * @date 2020/8/20
 */
public class CustomizeDatasourceStreamingJob {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.addSource(new SourceFromMySQL()).print();
        env.execute("Flink add data sourc");
    }
}
