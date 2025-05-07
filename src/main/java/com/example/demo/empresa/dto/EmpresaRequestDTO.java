package com.example.demo.empresa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EmpresaRequestDTO {

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotBlank
    private String ruc;

    private boolean estadoActivo;

}
