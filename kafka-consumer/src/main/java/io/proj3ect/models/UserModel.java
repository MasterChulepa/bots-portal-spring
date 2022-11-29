package io.proj3ect.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Timestamp;

@Entity(name = "usersData")
@Data
public class UserModel {
    @Id
    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private Timestamp registeredAt;

}
