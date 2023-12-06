package com.library.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class User {
    private Long userId;
    private String username;
    private String password;
}
