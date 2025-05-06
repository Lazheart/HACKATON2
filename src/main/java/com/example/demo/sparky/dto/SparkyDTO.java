package com.example.demo.sparky.dto;

import java.util.List;

public class SparkyDTO {
    private Long id;
    private String nombre;
    private List<Long> empresaIds;

    public SparkyDTO() {}

    public SparkyDTO(Long id, String nombre, List<Long> empresaIds) {
        this.id = id;
        this.nombre = nombre;
        this.empresaIds = empresaIds;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Long> getEmpresaIds() {
        return empresaIds;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmpresaIds(List<Long> empresaIds) {
        this.empresaIds = empresaIds;
    }
}
