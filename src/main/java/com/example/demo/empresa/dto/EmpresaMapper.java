package com.example.demo.empresa.dto;

import com.example.demo.empresa.domain.Empresa;

public class EmpresaMapper {

    public static Empresa toEntity(EmpresaRequestDTO dto) {
        Empresa empresa = new Empresa();
        empresa.setNombre(dto.getNombre());
        empresa.setRuc(dto.getRuc());
        empresa.setEstadoActivo(dto.isEstadoActivo());
        return empresa;
    }

    public static EmpresaResponseDTO toResponse(Empresa empresa) {
        EmpresaResponseDTO dto = new EmpresaResponseDTO();
        dto.setId(empresa.getId());
        dto.setNombre(empresa.getNombre());
        dto.setRuc(empresa.getRuc());
        dto.setEstadoActivo(empresa.isEstadoActivo());
        dto.setFechaAfiliacion(empresa.getFechaAfiliacion());
        return dto;
    }
}
