package com.example.demo.empresa.restriccion.application;

import com.example.demo.empresa.restriccion.domain.RestriccionEmpresa;
import com.example.demo.empresa.restriccion.domain.RestriccionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/restrictions")
public class RestriccionController {

    private final RestriccionService restriccionService;

    public RestriccionController(RestriccionService restriccionService) {
        this.restriccionService = restriccionService;
    }

    @PostMapping
    public ResponseEntity<RestriccionEmpresa> crear(@RequestBody RestriccionEmpresa restriccion) {
        return ResponseEntity.ok(restriccionService.crearRestriccion(restriccion));
    }

    @GetMapping
    public ResponseEntity<List<RestriccionEmpresa>> listar() {
        return ResponseEntity.ok(restriccionService.listarRestricciones());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestriccionEmpresa> actualizar(
            @PathVariable Long id,
            @RequestBody RestriccionEmpresa restriccion) {
        return ResponseEntity.ok(restriccionService.actualizarRestriccion(id, restriccion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        restriccionService.eliminarRestriccion(id);
        return ResponseEntity.noContent().build();
    }
}
