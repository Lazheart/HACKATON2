package com.example.demo.empresa.domain;

import com.example.demo.sparky.domain.SparkyAdmin;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
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
    @JoinColumn(name = "sparky_admin_id")
    private SparkyAdmin sparkyAdmin;

    // Getters y setters

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public LocalDateTime getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    public void setFechaAfiliacion(LocalDateTime fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public boolean isEstadoActivo() {
        return estadoActivo;
    }

    public void setEstadoActivo(boolean estadoActivo) {
        this.estadoActivo = estadoActivo;
    }

    public SparkyAdmin getSparkyAdmin() {
        return sparkyAdmin;
    }

    public void setSparkyAdmin(SparkyAdmin sparkyAdmin) {
        this.sparkyAdmin = sparkyAdmin;
    }
}
