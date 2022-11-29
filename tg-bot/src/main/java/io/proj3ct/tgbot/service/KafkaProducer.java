package io.proj3ct.tgbot.service;

import io.proj3ct.tgbot.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {
    private static final String TOPIC = "users";
    private final KafkaTemplate<String, UserDto> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(UserDto userdto){
        kafkaTemplate.send(TOPIC,  userdto);
    }
}
