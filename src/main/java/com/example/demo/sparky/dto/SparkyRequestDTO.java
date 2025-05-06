package com.example.demo.sparky.dto;

import java.util.List;

public class SparkyRequestDTO {
    private String nombre;
    private List<Long> empresaIds;

    public SparkyRequestDTO() {}

    public SparkyRequestDTO(String nombre, List<Long> empresaIds) {
        this.nombre = nombre;
        this.empresaIds = empresaIds;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Long> getEmpresaIds() {
        return empresaIds;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmpresaIds(List<Long> empresaIds) {
        this.empresaIds = empresaIds;
    }
}
