package com.example.demo.kafka.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author acemma
 * @date 2017/12/21 11:59
 * @Description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MsgProducerTest {

    @Autowired
    private MsgProducer msgProducer;

    @Test
    public void testSend(){
        msgProducer.send();
    }

}
