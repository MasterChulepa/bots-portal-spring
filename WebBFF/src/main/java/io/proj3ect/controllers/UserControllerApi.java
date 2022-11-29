package io.proj3ect.controllers;

import io.proj3ect.data.UserRepositoryBFF;
import io.proj3ect.models.UserModel;
import io.proj3ect.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/users", produces = "application/json")
@CrossOrigin(origins = "*")
public class UserControllerApi {
    private final UserService userService;

    @Autowired
    public UserControllerApi(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public Iterable<UserModel> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserModel getUser(@PathVariable("id") Long id){
        return userService.findById(id);
    }
    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public UserModel postUser(@RequestBody UserModel user) {
        return userService.saveUser(user);
    }
}
