package com.example.demo.usuario.application;

import com.example.demo.limite.domain.Limite;
import com.example.demo.usuario.domain.Usuario;
import com.example.demo.usuario.domain.UsuarioService;
import com.example.demo.usuario.dto.UsuarioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(@RequestParam Long empresaId) {
        return ResponseEntity.ok(usuarioService.obtenerTodosPorEmpresa(empresaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id) {
        return usuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuario));
    }

    @PostMapping("/{id}/limits")
    public ResponseEntity<String> asignarLimiteUsuario(@PathVariable Long id, @RequestBody Limite limite) {
        usuarioService.asignarLimite(id, limite);
        return ResponseEntity.ok("Límite asignado correctamente al usuario.");
    }

    @GetMapping("/{id}/consumption")
    public ResponseEntity<UsuarioDto> obtenerConsumoUsuario(@PathVariable Long id) {
        UsuarioDto reporte = usuarioService.obtenerConsumo(id);
        return ResponseEntity.ok(reporte);
    }
}

