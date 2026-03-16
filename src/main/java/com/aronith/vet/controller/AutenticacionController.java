package com.aronith.vet.controller;

import com.aronith.vet.dto.request.AutenticacionRequestDTO;
import com.aronith.vet.dto.response.TokenResponseDTO;
import com.aronith.vet.security.JwtService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
@Tag(name = "Autenticación", description = "Endpoint para obtener el token JWT")
@RequiredArgsConstructor
public class AutenticacionController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid AutenticacionRequestDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(dto.email());
        String token = jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

}
