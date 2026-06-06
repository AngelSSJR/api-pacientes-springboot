package com.paciente.controller;

import com.paciente.model.AuthRequest;
import com.paciente.model.Usuario;
import com.paciente.repository.UsuarioRepository;
import com.paciente.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequest request) {
        
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(request.getUsername());
        
        
        if (usuarioOpt.isPresent() && usuarioOpt.get().getPassword().equals(request.getPassword())) {
            
            String token = jwtUtil.generarToken(request.getUsername());
            return ResponseEntity.ok(token); // Entregamos el token
        }
        
        
        return ResponseEntity.status(401).body("Credenciales incorrectas");
    }
}
