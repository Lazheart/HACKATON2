package com.example.demo.usuario.infraestructure;

import com.example.demo.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByEmpresaId(Long empresaId);
    Optional<Usuario> findByCorreo(String correo);
}
