package com.example.demo.usuario.domain;

import jakarta.persistence.*;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String Nombre;
    private String Correo;
    private String Contraseña;

    @Enumerated(EnumType.STRING)
    private Rol Rol;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa Empresa;

    private boolean Activo;

}