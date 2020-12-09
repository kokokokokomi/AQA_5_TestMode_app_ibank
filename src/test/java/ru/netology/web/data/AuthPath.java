package ru.netology.web.data;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor

public class AuthPath {
    private String login;
    private String password;
    private String status;
}
