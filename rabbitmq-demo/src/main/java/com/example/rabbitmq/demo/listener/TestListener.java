package com.example.rabbitmq.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author sunx
 * @date 2019-10-17
 * @description
 */
@Component
@Slf4j
public class TestListener {

    /**
     * 监听
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "current-queue", durable = "true"),
            exchange = @Exchange(value = "current-exchange", durable = "true"),
            key = "current-router"))
    public void listener(String message) {
        log.info("listener----->{}", message);
        try {
            Thread.sleep(50000);
            log.info(message);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}
