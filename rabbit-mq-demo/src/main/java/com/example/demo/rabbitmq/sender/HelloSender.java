package com.example.demo.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author acemma
 * @date 2017/12/14 16:22
 * @Description
 */
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDirect(){
        amqpTemplate.convertAndSend("queue","hello world!");
    }

    public void sendTopic(){
        amqpTemplate.convertAndSend("exchange","topic.messages","hello,everybody!!");
    }

    public void sendFanout(){
        amqpTemplate.convertAndSend("fanoutExchange","","xixi,hahahaha");
    }

}
