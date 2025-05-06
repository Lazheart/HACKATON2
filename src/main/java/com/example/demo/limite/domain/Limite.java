package com.example.demo.limite.domain;

import com.example.demo.usuario.domain.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "limites")
public class Limite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int solicitudesPermitidas;
    private int tokensPermitidos;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
