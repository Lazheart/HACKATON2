package com.example.demo.empresa.restriccion.infrastructure;

import com.example.demo.empresa.restriccion.domain.RestriccionEmpresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestriccionRepository extends JpaRepository<RestriccionEmpresa, Long> {
}
