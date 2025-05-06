package com.example.demo.sparky.infrastructure;

import com.example.demo.sparky.domain.Sparky;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SparkyRepository extends JpaRepository<Sparky, Long> {
}
