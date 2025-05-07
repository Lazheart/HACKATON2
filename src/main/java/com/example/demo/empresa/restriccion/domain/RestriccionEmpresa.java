package com.example.demo.empresa.restriccion.domain;

import com.example.demo.empresa.domain.Empresa;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RestriccionEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String tipoModelo;

    @Min(1)
    private int maxSolicitudes;

    @Min(1)
    private int maxTokens;

    @Min(1)
    private int ventanaTiempoHoras;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    @NotNull
    private Empresa empresa;
}
