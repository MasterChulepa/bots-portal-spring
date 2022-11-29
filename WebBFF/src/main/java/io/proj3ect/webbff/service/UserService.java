package io.proj3ect.webbff.service;

import io.proj3ect.webbff.data.UserRepositoryBFF;
import io.proj3ect.webbff.exceptions.UserNotFoundException;
import io.proj3ect.webbff.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    UserRepositoryBFF userRepositoryBFF;
    KafkaProducer kafkaProducer;

    @Autowired
    public UserService(UserRepositoryBFF userRepositoryBFF,
                       KafkaProducer kafkaProducer){
        this.userRepositoryBFF = userRepositoryBFF;
        this.kafkaProducer = kafkaProducer;
    }

    public UserModel findById(Long id){
        return userRepositoryBFF.findById(id).orElseThrow(
                () -> new UserNotFoundException("User with id " + id + " not found")
        );
    }
    public Iterable<UserModel> findAll(){
        return userRepositoryBFF.findAll();
    }

    public UserModel saveUser(UserModel user) {
       kafkaProducer.sendMessage(user);
       return user;
    }
}
