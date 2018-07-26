package com.example.demo.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author acemma
 * @date 2017/12/14 19:27
 * @Description
 */
@Component
public class HelloReceiver {

    @RabbitListener(queues = "queue")
    public void receive(String str){
        System.out.println("Receive:" + str);
    }

    @RabbitListener(queues = "topic.message")
    public void process1(String str){
        System.out.println("message:"+str);
    }

   /* @RabbitListener(queues = "topic.messages")
    public void process2(String str){
        System.out.println("messages:"+str);
    }

    @RabbitListener(queues = "fanout.A")
    public void processA(String str){
        System.out.println("ReceiveA:" + str);
    }

    @RabbitListener(queues = "fanout.B")
    public void processB(String str){
        System.out.println("ReceiveB:" + str);
    }

    @RabbitListener(queues = "fanout.C")
    public void processC(String str){
        System.out.println("ReceiveC:" + str);
    }*/

    @RabbitListener(queues = {"fanout.A","fanout.B","fanout.C"})
    public void processABC(String str){
        System.out.println("ReceiveABC:"+str);
    }
}
