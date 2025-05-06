package com.example.demo.sparky.application;

import com.example.demo.sparky.domain.Sparky;
import com.example.demo.empresa.domain.Empresa;

import java.util.List;
import java.util.stream.Collectors;

public class SparkyMapper {

    public static Sparky toDomain(SparkyDTO dto) {
        Sparky sparky = new Sparky();
        sparky.setId(dto.getId());
        sparky.setNombre(dto.getNombre());

        return sparky;
    }

    public static SparkyDTO toDTO(Sparky sparky) {
        List<Long> empresaIds = sparky.getEmpresas().stream()
                .map(Empresa::getId)
                .collect(Collectors.toList());

        return new SparkyDTO(
                sparky.getId(),
                sparky.getNombre(),
                empresaIds
        );
    }
}
