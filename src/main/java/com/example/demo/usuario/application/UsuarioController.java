package com.example.demo.usuario.application;

import com.example.demo.limite.domain.Limite;
import com.example.demo.usuario.domain.Usuario;
import com.example.demo.usuario.domain.UsuarioService;
import com.example.demo.usuario.dto.UsuarioDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Solo COMPANY_ADMIN puede crear usuarios dentro de su empresa
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_COMPANY_ADMIN')")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario, Authentication auth) {
        usuarioService.validarEmpresa(auth, usuario.getEmpresa().getId());
        return ResponseEntity.ok(usuarioService.crearUsuario(usuario));
    }

    // COMPANY_ADMIN solo puede listar usuarios de su empresa
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_COMPANY_ADMIN')")
    public ResponseEntity<List<Usuario>> listarUsuarios(@RequestParam Long empresaId, Authentication auth) {
        usuarioService.validarEmpresa(auth, empresaId);
        return ResponseEntity.ok(usuarioService.obtenerTodosPorEmpresa(empresaId));
    }

    // COMPANY_ADMIN solo puede ver usuarios de su empresa
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_COMPANY_ADMIN')")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable Long id, Authentication auth) {
        usuarioService.validarAccesoUsuario(auth, id);
        return usuarioService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // COMPANY_ADMIN solo puede actualizar usuarios de su empresa
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_COMPANY_ADMIN')")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario, Authentication auth) {
        usuarioService.validarEmpresa(auth, id);
        return ResponseEntity.ok(usuarioService.actualizarUsuario(id, usuario));
    }

    // COMPANY_ADMIN puede asignar límites solo a usuarios de su empresa
    @PostMapping("/{id}/limits")
    @PreAuthorize("hasAuthority('ROLE_COMPANY_ADMIN')")
    public ResponseEntity<String> asignarLimiteUsuario(@PathVariable Long id, @RequestBody Limite limite, Authentication auth) {
        usuarioService.validarAccesoUsuario(auth, id);
        usuarioService.asignarLimite(id, limite);
        return ResponseEntity.ok("Límite asignado correctamente al usuario.");
    }

    // USER puede ver solo su propio consumo
    @GetMapping("/{id}/consumption")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_COMPANY_ADMIN')")
    public ResponseEntity<UsuarioDto> obtenerConsumoUsuario(@PathVariable Long id, Authentication auth) {
        usuarioService.validarAccesoConsumo(auth, id);
        UsuarioDto reporte = usuarioService.obtenerConsumo(id);
        return ResponseEntity.ok(reporte);
    }
}


