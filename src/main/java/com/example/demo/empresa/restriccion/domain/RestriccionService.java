package com.example.demo.empresa.restriccion.domain;

import com.example.demo.empresa.restriccion.infrastructure.RestriccionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestriccionService {

    private final RestriccionRepository restriccionRepository;

    public RestriccionService(RestriccionRepository restriccionRepository) {
        this.restriccionRepository = restriccionRepository;
    }

    public RestriccionEmpresa crearRestriccion(RestriccionEmpresa restriccion) {
        return restriccionRepository.save(restriccion);
    }

    public List<RestriccionEmpresa> listarRestricciones() {
        return restriccionRepository.findAll();
    }

    @Transactional
    public RestriccionEmpresa actualizarRestriccion(Long id, RestriccionEmpresa actualizada) {
        return restriccionRepository.findById(id).map(restriccion -> {
            restriccion.setTipoModelo(actualizada.getTipoModelo());
            restriccion.setMaxSolicitudes(actualizada.getMaxSolicitudes());
            restriccion.setMaxTokens(actualizada.getMaxTokens());
            restriccion.setVentanaTiempoHoras(actualizada.getVentanaTiempoHoras());
            return restriccion;
        }).orElseThrow(() -> new RuntimeException("Restricción no encontrada con ID: " + id));
    }

    public void eliminarRestriccion(Long id) {
        restriccionRepository.deleteById(id);
    }
}
