package com.example.demo.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author acemma
 * @date 2017/12/21 11:53
 * @Description
 */
@Component
public class MsgConsumer {

    @KafkaListener(topics = "test")
    public void proccessMessage(String content){
        System.err.println("consumer:" + content);
    }

}
