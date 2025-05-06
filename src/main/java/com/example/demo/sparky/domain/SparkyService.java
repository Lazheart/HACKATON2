package com.example.demo.sparky.domain;

import org.springframework.stereotype.Service;
import com.example.demo.sparky.infrastructure.SparkyRepository;
import java.util.List;
import java.util.Optional;

@Service
public class SparkyService {

    private final SparkyRepository sparkyRepository;

    public SparkyService(SparkyRepository sparkyRepository) {
        this.sparkyRepository = sparkyRepository;
    }

    public Sparky crearSparky(Sparky sparky) {
        return sparkyRepository.save(sparky);
    }

    public List<Sparky> listarSparky() {
        return sparkyRepository.findAll();
    }

    public Optional<Sparky> obtenerPorId(Long id) {
        return sparkyRepository.findById(id);
    }
}
