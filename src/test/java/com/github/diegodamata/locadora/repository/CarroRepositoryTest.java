package com.github.diegodamata.locadora.repository;

import com.github.diegodamata.locadora.entity.Carro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest //anotacao para testar a camada repository, com essa anotação em vez de sumir toda minha aplicação
    //eu digo ao jpa que quero testar apenas o que eu colocar dentro dessa classe de teste
@ActiveProfiles("test")
class CarroRepositoryTest {

    @Autowired
    CarroRepository repository;

    @Test
    void deseSalvarCarro(){
        var entity = new Carro("Honda", 100.0);

        repository.save(entity);

        assertNotNull(entity.getId());
    }

}