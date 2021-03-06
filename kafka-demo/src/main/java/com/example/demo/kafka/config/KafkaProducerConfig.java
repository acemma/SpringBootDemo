//package com.example.demo.kafka.config;
//
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.ByteArraySerializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.core.ProducerFactory;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableKafka
//public class KafkaProducerConfig {
//
//    @Value("${spring.kafka.bootstrap-servers}")
//    private String brokerAddress;
//
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs());
//    }
//
//    @Bean
//    public Map<String, Object> producerConfigs() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddress);
//        props.put(ProducerConfig.RETRIES_CONFIG, 0);
//        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
//        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
//        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        return props;
//    }
////
////
////    @Bean
////    public ProducerFactory<String, Object> producerFactory1() {
////        return new DefaultKafkaProducerFactory<>(producerConfigs1());
////    }
////
////    @Bean
////    public Map<String, Object> producerConfigs1() {
////        JsonSerializer<Member> memberDeserializer = new JsonSerializer<>();
////        Map<String, Object> props = new HashMap<>();
////        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddress);
////        props.put(ProducerConfig.RETRIES_CONFIG, 0);
////        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
////        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
////        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
////        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
////        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
////        return props;
////    }
//
//    @Bean
//    public ProducerFactory<String, Object> producerFactory2() {
//        return new DefaultKafkaProducerFactory<>(producerConfigs2());
//    }
//
//    @Bean
//    public Map<String, Object> producerConfigs2() {
////        JsonSerializer<Member> memberDeserializer = new JsonSerializer<>();
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, this.brokerAddress);
//        props.put(ProducerConfig.RETRIES_CONFIG, 0);
//        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
//        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
//        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
//        return props;
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate() {
//        return new KafkaTemplate<String, String>(producerFactory());
//    }
////
////    @Bean("kafkaTemplate1")
////    public KafkaTemplate<String, Object> kafkaTemplate1() {
////        return new KafkaTemplate<String, Object>(producerFactory1());
////    }
//
//    @Bean("kafkaTemplate2")
//    public KafkaTemplate<String, Object> kafkaTemplate2() {
//        return new KafkaTemplate<String, Object>(producerFactory2());
//    }
//}