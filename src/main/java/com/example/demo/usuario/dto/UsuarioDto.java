package com.example.demo.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UsuarioDto {
    private Long usuarioId;
    private int totalSolicitudes;
    private int totalTokens;
}

