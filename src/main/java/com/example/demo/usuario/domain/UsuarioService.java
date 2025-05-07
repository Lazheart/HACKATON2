package com.example.demo.usuario.domain;

import com.example.demo.limite.domain.Limite;
import com.example.demo.limite.infraestructure.LimiteRepository;
import com.example.demo.usuario.dto.UsuarioDto;
import org.springframework.security.core.Authentication;
import com.example.demo.solicitud.infrastructure.SolicitudRepository;
import com.example.demo.usuario.infraestructure.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final LimiteRepository limiteRepository;
    private final SolicitudRepository solicitudRepository;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          LimiteRepository limiteRepository,
                          SolicitudRepository solicitudRepository) {
        this.usuarioRepository = usuarioRepository;
        this.limiteRepository = limiteRepository;
        this.solicitudRepository = solicitudRepository;
    }

    public Usuario crearUsuario(Usuario usuario) {
        usuario.setActivo(true);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerTodosPorEmpresa(Long empresaId) {
        return usuarioRepository.findByEmpresaId(empresaId);
    }

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario actualizarUsuario(Long id, Usuario datosActualizados) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setNombre(datosActualizados.getNombre());
        usuario.setCorreo(datosActualizados.getCorreo());
        return usuarioRepository.save(usuario);
    }

    public void asignarLimite(Long usuarioId, Limite limite) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        limite.setUsuario(usuario);
        limiteRepository.save(limite);
    }

    public UsuarioDto obtenerConsumo(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        int totalSolicitudes = Math.toIntExact(solicitudRepository.countByUsuarioId(usuarioId));
        int totalTokens = solicitudRepository.sumTokensByUsuarioId(usuarioId);

        return new UsuarioDto(usuarioId, totalSolicitudes, totalTokens);
    }
    // Este metodo valida acceso con empresaId
    public void validarEmpresa(Authentication auth, Long empresaId) {
        Usuario actual = usuarioRepository.findByCorreo(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!actual.getRol().equals(Rol.ROLE_COMPANY_ADMIN)) {
            throw new RuntimeException("No autorizado.");
        }

        if (!actual.getEmpresa().getId().equals(empresaId)) {
            throw new RuntimeException("Acceso denegado a esta empresa.");
        }
    }

    // Este valida acceso entre usuarios (por usuarioId)
    public void validarAccesoUsuario(Authentication auth, Long usuarioId) {
        Usuario actual = usuarioRepository.findByCorreo(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Usuario objetivo = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario destino no encontrado"));

        if (!actual.getRol().equals(Rol.ROLE_COMPANY_ADMIN)) {
            throw new RuntimeException("No autorizado.");
        }

        if (!actual.getEmpresa().getId().equals(objetivo.getEmpresa().getId())) {
            throw new RuntimeException("Acceso denegado a usuarios fuera de su empresa.");
        }
    }

    public void validarAccesoConsumo(Authentication auth, Long usuarioId) {
        Usuario actual = usuarioRepository.findByCorreo(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (actual.getRol().equals(Rol.ROLE_USER) && !actual.getId().equals(usuarioId)) {
            throw new RuntimeException("No puede ver el consumo de otro usuario.");
        }

        if (actual.getRol().equals(Rol.ROLE_COMPANY_ADMIN)) {
            Usuario destino = usuarioRepository.findById(usuarioId)
                    .orElseThrow(() -> new RuntimeException("Usuario destino no encontrado"));

            if (!actual.getEmpresa().getId().equals(destino.getEmpresa().getId())) {
                throw new RuntimeException("No autorizado para ver el consumo de este usuario.");
            }
        }
    }
}
