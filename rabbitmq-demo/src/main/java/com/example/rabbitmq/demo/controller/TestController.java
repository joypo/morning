package com.example.rabbitmq.demo.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunx
 * @date 2019-10-17
 * @description
 */
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("send")
    public void send(String msg) {
        rabbitTemplate.convertAndSend("current-exchange", "current-router", msg);
    }

    @GetMapping("remove")
    public void remove(String id) {

    }
}
