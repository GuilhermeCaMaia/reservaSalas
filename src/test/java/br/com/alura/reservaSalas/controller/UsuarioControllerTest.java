package br.com.alura.reservaSalas.controller;

import br.com.alura.reservaSalas.model.Usuario;
import br.com.alura.reservaSalas.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Mock
    private Usuario usuario;

    @Test
    void deveriaRetornar200ParaCadastrarUsuarioComSucesso() throws Exception {
        String json = """
                {
                    "nome": "Guilherme",
                    "email": "guilherme@gmail.com"
                }
                """;
        var response = mvc.perform(
                post("/api/v1/usuario").content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaRetornar400ParaCadastrarUsuarioComErro() throws Exception {
        String json = """
                {}
                """;
        var response = mvc.perform(
                post("/api/v1/usuario").content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaRetornar200ParaListarUsuariosComSucesso() throws Exception {
        var response = mvc.perform(
                get("/api/v1/usuario")
        ).andReturn().getResponse();
        assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaRetornar200ParaBuscarUsuariosPorIdComSucesso() throws Exception {
        Long idUsuario = 1L;
        var response = mvc.perform(
                get("/api/v1/usuario/{id}",idUsuario)
        ).andReturn().getResponse();
        assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaRetornar404ParaBuscarUsuarioPorIdComErro() throws Exception {
        Long idInexistente = 99L;

        Mockito.when(usuarioService.buscarSalaPorId(idInexistente))
                .thenThrow(new EntityNotFoundException());
    }

    @Test
    void deveriaRetornar200ParaAtualizarUsuarioComSucesso() throws Exception {
        String json = """
                {
                    "id": 1,
                    "nome": "Guilherme",
                    "email": "guilherme@gmail.com"
                }
                """;
        var response = mvc.perform(
                put("/api/v1/usuario").content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaRetornar400ParaAtualizarUsuarioComSucesso() throws Exception {
        String json = """
                {
                    
                }
                """;
        var response = mvc.perform(
                put("/api/v1/usuario").content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaRetornar204ParaDeletarUsuarioComSucesso() throws Exception {
        Long idUsuario = 1L;
        var response = mvc.perform(
                delete("/api/v1/usuario/{id}",idUsuario)
        ).andReturn().getResponse();
        assertEquals(204, response.getStatus());
    }
}
