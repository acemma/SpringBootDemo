package com.example.demo.rabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author acemma
 * @date 2017/12/14 16:15
 * @Description
 */
@Configuration
public class DirectSenderConfig {

    @Bean
    public Queue queue(){
        return new Queue("queue");
    }
}
