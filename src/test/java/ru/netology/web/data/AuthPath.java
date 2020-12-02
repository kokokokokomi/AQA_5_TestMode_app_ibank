package ru.netology.web.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class AuthPath {
    private String login;
    private String password;
    private String status;

    public AuthPath(String login, String password, String status) {
        this.login = login;
        this.password = password;
        this.status = status;
    }
}
