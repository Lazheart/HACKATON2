package com.example.demo.sparky.domain;

import com.example.demo.empresa.domain.Empresa;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Sparky {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre = "Sparky AI Hub"; // solo si quieres mostrarlo

    @OneToMany(mappedBy = "sparky", cascade = CascadeType.ALL)
    private List<Empresa> empresas = new ArrayList<>();

    // Getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public List<Empresa> getEmpresas() { return empresas; }
    public void setEmpresas(List<Empresa> empresas) { this.empresas = empresas; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}
