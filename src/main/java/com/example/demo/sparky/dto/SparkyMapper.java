package com.example.demo.sparky.dto;

import com.example.demo.sparky.domain.Sparky;
import com.example.demo.empresa.domain.Empresa;

import java.util.List;
import java.util.stream.Collectors;

public class SparkyMapper {

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

    public static Sparky toDomain(SparkyRequestDTO request) {
        Sparky sparky = new Sparky();
        sparky.setNombre(request.getNombre());
        return sparky;
    }
}
