package com.example.demo.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.util.Result;
import com.example.demo.repository.IUsuarioRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import java.util.Map;

@Service
public class TokenService {

    private final JwtEncoder encoder;
    private IUsuarioRepository usuarioRepository;

    public TokenService(JwtEncoder encoder, IUsuarioRepository usuarioRepository) {
        this.encoder = encoder;
        this.usuarioRepository = usuarioRepository;
    }

    public Result<String> generateToken(Usuario usuario) {

        Optional<Usuario> posibleUsuario = usuarioRepository.findByNombre(usuario.getNombre());
        if(posibleUsuario.isEmpty()){
            return Result.fallo("El usuario no existe.");
        }

        if(!posibleUsuario.get().getContrasenia().equals(usuario.getContrasenia())){
            return Result.fallo("Contraseña incorrecta");
        }

        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(usuario.getNombre())
                .claim("scope", "")
                .build();
        String token = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return Result.exito(token);
    }

    public Result<String> validarToken(Authentication authentication) {
        try {
            JwtAuthenticationToken token = (JwtAuthenticationToken) authentication;
            Map<String, Object> attributes = token.getTokenAttributes();
            String usuario = attributes.get("sub").toString();
            return Result.exito(usuario);
        }
        catch (Exception e) {
            return Result.fallo("Sesion invalida.");
        }

    }
}