package com.example.demo.empresa.domain;

import com.example.demo.sparky.domain.Sparky;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nombre;

    @NotBlank
    @Column(unique = true)
    private String ruc;

    private LocalDateTime fechaAfiliacion;

    private boolean estadoActivo = true;

    @ManyToOne
    @JoinColumn(name = "sparky_id")
    private Sparky sparky;
}

