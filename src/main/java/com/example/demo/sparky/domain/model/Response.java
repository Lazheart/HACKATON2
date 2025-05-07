package com.example.demo.sparky.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {
    private String message;

    public Response() {}

    public Response(String message) {
        this.message = message;
    }

}
