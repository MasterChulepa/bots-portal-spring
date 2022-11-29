package io.proj3ect.webbff.service;

import io.proj3ect.webbff.models.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {
    private static final String TOPIC = "users";
    private final KafkaTemplate<String, UserModel> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(UserModel userdto){
        kafkaTemplate.send(TOPIC,  userdto);
    }
}