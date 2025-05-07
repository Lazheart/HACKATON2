package com.example.demo.security.Auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String correo;
    private String contrasena;
}
