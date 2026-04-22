package com.github.diegodamata.locadora.repository;

import com.github.diegodamata.locadora.entity.Carro;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //anotacao para testar a camada repository, com essa anotação em vez de sumir toda minha aplicação
    //eu digo ao jpa que quero testar apenas o que eu colocar dentro dessa classe de teste
@ActiveProfiles("test")
class CarroRepositoryTest {

    @Autowired
    CarroRepository repository;

    Carro carro;

    @BeforeEach
    void setUp(){
        carro = new Carro("Honda Civic", 100.0, 2027);
    }

    @Test
    void deseSalvarCarro(){
        repository.save(carro);

        assertNotNull(carro.getId());
    }

    @Test
    void deveBuscarCarroPorId(){
        Carro carroSalvo = repository.save(carro);

        Optional<Carro> carroEncontrado = repository.findById(carroSalvo.getId());

        assertThat(carroEncontrado).isPresent();
        assertThat(carroEncontrado.get().getModelo()).isEqualTo("Honda Civic");
    }

}