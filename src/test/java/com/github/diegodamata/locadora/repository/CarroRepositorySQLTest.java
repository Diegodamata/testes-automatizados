package com.github.diegodamata.locadora.repository;

import com.github.diegodamata.locadora.entity.Carro;
import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class CarroRepositorySQLTest {

    @Autowired
    CarroRepository repository;

    @Test
    @Sql("/sql/popular-carros.sql") //anotacao para testar um arquivo especifico, aponta para a pasta raiz preciso passar o caminho
    void deveBuscarCarroPorModelo(){
        List<Carro> carros = repository.findByModelo("SUV");

        var modelo = carros.stream().findFirst().get();

        assertEquals(1, carros.size());

        assertThat(modelo.getValorDiaria()).isEqualTo(150.0);
        assertThat(modelo.getModelo()).isEqualTo("SUV");
    }
}
