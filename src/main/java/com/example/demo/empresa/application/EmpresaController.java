package com.example.demo.empresa.application;

import com.example.demo.empresa.domain.Empresa;
import com.example.demo.empresa.domain.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/companies")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<Empresa> crearEmpresa(@RequestBody Empresa empresa) {
        return ResponseEntity.ok(empresaService.crearEmpresa(empresa));
    }

    @GetMapping
    public ResponseEntity<List<Empresa>> listarEmpresas() {
        return ResponseEntity.ok(empresaService.listarEmpresas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> obtenerEmpresa(@PathVariable Long id) {
        return empresaService.obtenerEmpresaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> actualizarEmpresa(@PathVariable Long id, @RequestBody Empresa datosActualizados) {
        return ResponseEntity.ok(empresaService.actualizarEmpresa(id, datosActualizados));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> cambiarEstado(@PathVariable Long id, @RequestParam boolean activo) {
        empresaService.cambiarEstadoEmpresa(id, activo);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/consumption")
    public ResponseEntity<String> reporteConsumo(@PathVariable Long id) {
        return ResponseEntity.ok(empresaService.obtenerReporteConsumo(id));
    }
}

