package com.github.diegodamata.locadora.controller;

import com.github.diegodamata.locadora.entity.Carro;
import com.github.diegodamata.locadora.service.CarroService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(CarroController.class) //anotacao do spring usada para testes unitarios na camada web (controller)
//carrega apenas componentes necessario para testar, nao o contexto completo da aplicacao
public class CarroControllerTest {

    @Autowired //deixo para o spring ser responsavel por criar uma instancia dessa dependencia
    MockMvc mvc; //objeto que ja vem dentro do webMvcTest, que praticamente se torna nosso cliente, para fazer as requisições

    @MockitoBean //faz a mesma coisa que o @Mock, ira mockar o carroService porem ira mockar dentro do contexto do spring
    CarroService carroService;

    @Test
    void deveSalvarUmCarro() throws Exception{
        Carro carro = new Carro(1L, "Honda Civic", 150, 2015);

        Mockito.when(carroService.salvar(Mockito.any())).thenReturn(carro);

        //json que sera enviado
        String json = """
                    {
                        "modelo": "Honda Civic",
                        "valorDiaria": 150,
                        "ano": 2015
                    }
                """;

        //MOckMvc o meu cliente da requisicao ira mandar esses dados
        //e ira capturar um valor dessa requisicao
        ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/carros")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json));

        //testo se o valor de retorno é o esperado
        //o '$' é uma referencia a raiz do json o '.' referencia o valor que eu quero buscar
        result.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.modelo").value("Honda Civic"));
    }
}
