package com.example.demo.usuario.domain;

import com.example.demo.limite.domain.Limite;
import com.example.demo.limite.infraestructure.LimiteRepository;
import com.example.demo.usuario.infraestructure.SolicitudRepository;
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

    public ConsumoReporte obtenerConsumo(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        int totalSolicitudes = solicitudRepository.countByUsuarioId(usuarioId);
        int totalTokens = solicitudRepository.sumTokensByUsuarioId(usuarioId);

        return new ConsumoReporte(usuarioId, totalSolicitudes, totalTokens);
    }
}
