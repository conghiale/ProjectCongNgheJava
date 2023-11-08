package com.example.bookstore.auth;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticateRequest {

    private String username;
    private String password;
}
