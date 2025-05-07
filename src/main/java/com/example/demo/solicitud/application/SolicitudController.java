package com.example.demo.solicitud.application;

import com.example.demo.solicitud.domain.Solicitud;
import com.example.demo.solicitud.domain.SolicitudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping("/chat")
    public ResponseEntity<Solicitud> crearChat(@RequestBody Solicitud solicitud) {
        return ResponseEntity.ok(solicitudService.registrarSolicitud(solicitud));
    }

    @PostMapping("/completion")
    public ResponseEntity<Solicitud> crearCompletion(@RequestBody Solicitud solicitud) {
        return ResponseEntity.ok(solicitudService.registrarSolicitud(solicitud));
    }

    @PostMapping("/multimodal")
    public ResponseEntity<Solicitud> crearMultimodal(@RequestBody Solicitud solicitud) {
        return ResponseEntity.ok(solicitudService.registrarSolicitud(solicitud));
    }

    @GetMapping("/history")
    public ResponseEntity<List<Solicitud>> historialUsuario(@RequestParam Long usuarioId) {
        return ResponseEntity.ok(solicitudService.obtenerHistorialPorUsuario(usuarioId));
    }
}
