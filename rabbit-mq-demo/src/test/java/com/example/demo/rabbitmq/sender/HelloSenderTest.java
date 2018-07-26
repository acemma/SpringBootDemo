package com.example.demo.rabbitmq.sender;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author acemma
 * @date 2017/12/14 16:30
 * @Description
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class HelloSenderTest {

    @Autowired
    private HelloSender helloSender;

    @Test
    public void testSend(){
        helloSender.sendDirect();
    }

    @Test
    public void testSendTopic(){
        helloSender.sendTopic();
    }

    @Test
    public void testSendFanout(){
        helloSender.sendFanout();
    }


}
