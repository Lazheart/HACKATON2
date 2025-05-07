package com.example.demo.solicitud.domain;

import com.example.demo.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String consulta;

    @Lob
    private String respuesta;

    private int tokensConsumidos;

    private ZonedDateTime fechaHora;

    private String modeloUsado;

    private String nombreArchivo; // Solo si es multimodal

    private String error;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}

