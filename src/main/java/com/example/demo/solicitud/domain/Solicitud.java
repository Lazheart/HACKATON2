package com.example.demo.solicitud.domain;

import com.example.demo.usuario.domain.Usuario;
import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
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

    // Getters y setters

    public Long getId() {
        return id;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getTokensConsumidos() {
        return tokensConsumidos;
    }

    public void setTokensConsumidos(int tokensConsumidos) {
        this.tokensConsumidos = tokensConsumidos;
    }

    public ZonedDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(ZonedDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getModeloUsado() {
        return modeloUsado;
    }

    public void setModeloUsado(String modeloUsado) {
        this.modeloUsado = modeloUsado;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
