package com.example.demo.empresa.domain;

import com.example.demo.empresa.infrastructure.EmpresaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa crearEmpresa(Empresa empresa) {
        empresa.setFechaAfiliacion(LocalDateTime.now());
        empresa.setEstadoActivo(true);
        return empresaRepository.save(empresa);
    }

    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }

    public Optional<Empresa> obtenerEmpresaPorId(Long id) {
        return empresaRepository.findById(id);
    }

    public Empresa actualizarEmpresa(Long id, Empresa datosActualizados) {
        return empresaRepository.findById(id).map(empresa -> {
            empresa.setNombre(datosActualizados.getNombre());
            empresa.setRuc(datosActualizados.getRuc());
            return empresaRepository.save(empresa);
        }).orElseThrow(() -> new RuntimeException("Empresa no encontrada con ID: " + id));
    }

    @Transactional
    public void cambiarEstadoEmpresa(Long id, boolean nuevoEstado) {
        empresaRepository.findById(id).ifPresent(empresa -> {
            empresa.setEstadoActivo(nuevoEstado);
        });
    }

    public String obtenerReporteConsumo(Long id) {
        // Placeholder hasta implementar consumo real
        return "Consumo de la empresa ID " + id + " (pendiente de implementación)";
    }
}
