package com.example.demo.limite.infraestructure;

import com.example.demo.limite.domain.Limite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LimiteRepository extends JpaRepository<Limite, Long> {

}