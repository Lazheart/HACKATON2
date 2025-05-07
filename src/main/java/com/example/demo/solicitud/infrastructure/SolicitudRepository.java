package com.example.demo.solicitud.infrastructure;

import com.example.demo.solicitud.domain.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Long> {
    List<Solicitud> findByUsuarioId(Long usuarioId);
    long countByUsuarioId(Long usuarioId);
    @Query("SELECT COALESCE(SUM(s.tokensConsumidos), 0) FROM Solicitud s WHERE s.usuario.id = :usuarioId")
    int sumTokensByUsuarioId(@Param("usuarioId") Long usuarioId);
}
