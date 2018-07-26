package com.example.demo.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author acemma
 * @date 2017/12/20 17:16
 * @Description
 */
@Configuration
public class FanoutSenderConfig {

    @Bean(name = "Amessage")
    public Queue AMessageQueue(){
        return new Queue("fanout.A");
    }

    @Bean(name = "Bmessage")
    public Queue BMessageQueue(){
        return new Queue("fanout.B");
    }

    @Bean(name = "Cmessage")
    public Queue CMessageQueue(){
        return new Queue("fanout.C");
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA(@Qualifier("Amessage") Queue AMessageQueue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(AMessageQueue).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(@Qualifier("Bmessage") Queue BMessageQueue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(BMessageQueue).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(@Qualifier("Cmessage") Queue CMessageQueue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(CMessageQueue).to(fanoutExchange);
    }

}
