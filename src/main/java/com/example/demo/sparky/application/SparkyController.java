package com.example.demo.sparky.application;

import com.example.demo.empresa.domain.Empresa;
import com.example.demo.sparky.domain.SparkyService;
import com.example.demo.sparky.domain.Sparky;
import jakarta.persistence.OneToMany;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sparky")
public class SparkyController {

    private final SparkyService sparkyService;

    public SparkyController(SparkyService sparkyService) {
        this.sparkyService = sparkyService;
    }

    @PostMapping
    public ResponseEntity<Sparky> crearSparky(@RequestBody Sparky sparky) {
        return ResponseEntity.ok(sparkyService.crearSparky(sparky));
    }

    @GetMapping
    public ResponseEntity<List<Sparky>> listar() {
        return ResponseEntity.ok(sparkyService.listarSparky());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sparky> obtener(@PathVariable Long id) {
        return sparkyService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @OneToMany(mappedBy = "sparky")
    private List<Empresa> empresas;

}
