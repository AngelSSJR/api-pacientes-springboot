package com.paciente.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        
        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        // 1. Verificar si la petición trae el encabezado "Authorization" con el formato "Bearer <token>"
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            jwt = authHeader.substring(7); // Extraemos solo el token, quitando los 7 caracteres de "Bearer "
            try {
                username = jwtUtil.extraerUsername(jwt);
            } catch (Exception e) {
                System.out.println("Token inválido o expirado");
            }
        }

        // 2. Si el token tiene un usuario válido y aún no ha sido autenticado en esta petición
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // 3. Validamos matemáticamente el token
            if (jwtUtil.validarToken(jwt, username)) {
                // 4. Creamos el pase VIP interno de Spring Security
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        username, null, new ArrayList<>());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                
                // 5. Le damos el pase al portero
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        
        // 6. Permitimos que la petición continúe su camino
        chain.doFilter(request, response);
    }
}
