package io.proj3ect.service;

import io.proj3ect.data.UserRepositoryBFF;
import io.proj3ect.exceptions.UserNotFoundException;
import io.proj3ect.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserService {

    UserRepositoryBFF userRepositoryBFF;

    @Autowired
    public UserService(UserRepositoryBFF userRepositoryBFF){
        this.userRepositoryBFF = userRepositoryBFF;
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
        return userRepositoryBFF.save(user);
    }
}
