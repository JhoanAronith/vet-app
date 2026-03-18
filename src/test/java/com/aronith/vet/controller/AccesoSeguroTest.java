package com.aronith.vet.controller;

import com.aronith.vet.model.Usuario;
import com.aronith.vet.repository.UsuarioRepository;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class AccesoSeguroTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Debe devolver 403 Forbidden al intentar acceder a un recurso protegido")
    void accesoDenegadoSinToken() throws Exception {
        mockMvc.perform(get("/api/mascota/listar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Debe devolver 403 Forbidden si el token enviado es inválido")
    void accesoDenegadoConTokenInvalido() throws Exception {
        mockMvc.perform(get("/api/mascota/listar")
                        .header("Authorization", "Bearer token.falso.invalido")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("Debe permitir el acceso cuando se envia un token JWT válido")
    void accesoAutorizadoConTokenValido() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername("jhoan_prueba");
        usuario.setEmail("jhoan_prueba@test.com");
        usuario.setPassword(passwordEncoder.encode("test123456"));
        usuarioRepository.save(usuario);

        String loginJson = "{\"email\": \"jhoan_prueba@test.com\", \"password\": \"test123456\"}";
        String response = mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginJson))
                .andReturn().getResponse().getContentAsString();

        String token = JsonPath.read(response, "$.token");

        mockMvc.perform(get("/api/mascota/listar")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @BeforeEach
    void limpiar() {
        usuarioRepository.findByEmail("jhoan_prueba@test.com")
                .ifPresent(usuarioRepository::delete);
    }

}
