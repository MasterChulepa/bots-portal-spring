package io.proj3ct.tgbot.dto;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;

    private String username;

    private Timestamp registeredAt;

}
