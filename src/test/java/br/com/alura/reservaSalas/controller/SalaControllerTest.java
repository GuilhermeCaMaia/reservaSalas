package br.com.alura.reservaSalas.controller;


import br.com.alura.reservaSalas.model.Sala;
import br.com.alura.reservaSalas.service.SalaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
public class SalaControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private SalaService salaService;

    @Mock
    private Sala sala;

    @Test
    void deveriaRetornar200ParaCadastrarSalaComSucesso() throws Exception {
        String json = """
                {
                    "nome": "301-a",
                    "capacidade": "40",
                    "salaInativa": true
                }
                """;
        var response = mvc.perform(
                post("/api/v1/sala").content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaRetornar400ParaCadastrarSalaComErro() throws Exception {
        String json = """
                {
                    
                }
                """;
        var response = mvc.perform(
                post("/api/v1/sala").content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaRetornar200ParaListarSalas() throws Exception {
        var response = mvc.perform(
                get("/api/v1/sala")
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

//    @Test
//    void deveriaRetornar400ParaListarSalasComErro() throws Exception {
//
//    }

    @Test
    void deveriaRetronar200ParaBuscarSalaPorIdComSucesso() throws Exception {
        Long idSala = 1L;
        var response = mvc.perform(
                get("/api/v1/sala/{id}", idSala)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

//    @Test
//    void deveriaRetornar400ParaBuscarSalaPorIdComErro() throws Exception {
//
//    }

    @Test
    void deveriaRetornar200ParaAtualizarSalaComSucesso() throws Exception {
        String json = """
                {
                    "id": 1,
                    "nome": "301-a",
                    "capacidade": "40",
                    "salaInativa": true
                }
                """;
        var response = mvc.perform(
                put("/api/v1/sala").content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaRetornar400ParaAtualizarSalaComErro() throws Exception {
        String json = """
                {
                    
                }
                """;
        var response = mvc.perform(
                put("/api/v1/sala").content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaRetornar200ParaDesativarSalaComSucesso() throws Exception {
        Long idSala = 1L;
        var response = mvc.perform(
                put("/api/v1/sala/{id}/desativar", idSala)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaRetornar200ParaAtivarSalaComSucesso() throws Exception {
        Long idSala = 1L;
        var response = mvc.perform(
                put("/api/v1/sala/{id}/desativar", idSala)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaRetornar204ParaDeletarSalaComSucesso() throws Exception {
        Long idSala = 1L;
        var response = mvc.perform(
                delete("/api/v1/sala/{id}", idSala)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(204, response.getStatus());
    }
}
