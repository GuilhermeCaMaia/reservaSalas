package br.com.alura.reservaSalas.controller;

import br.com.alura.reservaSalas.model.Reserva;
import br.com.alura.reservaSalas.service.ReservaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
public class ReservaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private ReservaService reservaService;

    @Mock
    private Reserva reserva;

    @Test
    void deveriaRetornar200ParaCadastrarReservaComSucesso() throws Exception {
        String json = """
                {
                    "dataInicio": "2021-09-20",
                    "dataFim": "2021-09-20",
                    "status": "ATIVO",
                    "idSala": 1,
                    "idUsuario": 1
                }
                """;
        var response = mvc.perform(
                post("/api/v1/reserva").content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaRetornar400ParaListarReservaComErro() throws Exception {
        String json = """
                {
                }
                """;
        var response = mvc.perform(
                post("/api/v1/reserva").content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaRetornar200ParaListarReservasComSucesso() throws Exception {
        var response = mvc.perform(
                get("/api/v1/reserva")
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaRetornar200ParaBuscarReservaPorIdComSucesso() throws Exception {
        Long idReserva = 1L;
        var response = mvc.perform(
                get("/api/v1/reserva/{id}", idReserva)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaRetornar200ParaAtualizarReservaComSucesso() throws Exception {
        String json = """
                {
                    "id": 1,
                    "dataInicio": "2021-09-20",
                    "dataFim": "2021-09-20",
                    "status": "ATIVO",
                    "idSala": 1,
                    "idUsuario": 1
                }
                """;
        var response = mvc.perform(
                put("/api/v1/reserva").content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(200, response.getStatus());
    }

    @Test
    void deveriaRetornar400ParaAtualizarReservaComErro() throws Exception {
        String json = """
                {
                    
                }
                """;
        var response = mvc.perform(
                put("/api/v1/reserva").content(json)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();

        assertEquals(400, response.getStatus());
    }

    @Test
    void deveriaRetornar200ParaCancelarReservaComSucesso() throws Exception {
        Long idReserva = 1L;
        var response = mvc.perform(
                put("/api/v1/reserva/{id}/cancelar", idReserva)
        ).andReturn().getResponse();
    }

    @Test
    void deveriaRetornar204ParaDeletarReservaPorIdComSucesso() throws Exception {
        Long idReserva = 1L;
        var response = mvc.perform(
                delete("/api/v1/reserva/{id}", idReserva)
        ).andReturn().getResponse();

        assertEquals(204, response.getStatus());
    }
}
