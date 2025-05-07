package com.example.demo.solicitud.domain;

import com.example.demo.solicitud.infrastructure.SolicitudRepository;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;

    public SolicitudService(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    public Solicitud registrarSolicitud(Solicitud solicitud) {
        solicitud.setFechaHora(ZonedDateTime.now());
        return solicitudRepository.save(solicitud);
    }

    public List<Solicitud> obtenerHistorialPorUsuario(Long usuarioId) {
        return solicitudRepository.findByUsuarioId(usuarioId);
    }
}
