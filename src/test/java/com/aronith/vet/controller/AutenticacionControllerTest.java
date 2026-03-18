package com.aronith.vet.controller;

import com.aronith.vet.model.Usuario;
import com.aronith.vet.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AutenticacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        usuarioRepository.deleteAll();
    }

    @Test
    @DisplayName("Debe devolver 200 y un token cuando las credenciales son correctas")
    void loginExitoso() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername("jhoan_dev");
        usuario.setEmail("jhoan@test.com");
        usuario.setPassword(passwordEncoder.encode("password123"));
        usuarioRepository.save(usuario);

        String json = "{\"email\": \"jhoan@test.com\", \"password\": \"password123\"}";

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.token").isNotEmpty());

    }

    @Test
    @DisplayName("Debe devolver 403 si las credenciales son incorrectas")
    void loginFallido() throws Exception {
        String json = "{\"email\": \"error@test.com\", \"password\": \"wrong12345\"}";
        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isForbidden());
    }

}
