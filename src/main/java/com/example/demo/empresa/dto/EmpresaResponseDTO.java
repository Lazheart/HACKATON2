package com.example.demo.empresa.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmpresaResponseDTO {

    private Long id;
    private String nombre;
    private String ruc;
    private boolean estadoActivo;
    private LocalDateTime fechaAfiliacion;
}

