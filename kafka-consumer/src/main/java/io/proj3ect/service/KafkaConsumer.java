package io.proj3ect.service;

import io.proj3ect.data.UserRepository;
import io.proj3ect.models.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {
    UserRepository userRepository;

    public KafkaConsumer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "users", groupId = "message_consumer")
    public void consume(UserModel user) {
        this.saveUser(user);
    }
    private void saveUser(UserModel user){
        if (userRepository.findById(user.getId()).isEmpty()) {
            userRepository.save(user);
            log.info(String.format("User %s was saved into db", user.getUsername()));
        }
    }

}
