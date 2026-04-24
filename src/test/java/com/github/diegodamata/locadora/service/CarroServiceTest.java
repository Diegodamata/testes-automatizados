package com.github.diegodamata.locadora.service;

import com.github.diegodamata.locadora.repository.CarroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class) //para ativar o plugin do mockito, para simular moks na minha casse
class CarroServiceTest {

    //se simplesmente chamar as dependencias necessarias dentro desse teste, é basicamente um teste integrado e nao é isso que eu quero
    //preciso fazer uma 'copia' das dependencias que eu preciso e para isso eu utilizo o mockito para mockar minhas dependencias

    @InjectMocks //anotacao para instanciar a casse que eu quero testar, e injeta mocks criado atraves da anotacao @Mock para teste
    CarroService carroService;

    @Mock //anotacao para criar uma instacia ficticia para teste, sem precisar utilizar a casse real
    CarroRepository carroRepository;

    @Test
    void deveSalvarUmCarro(){

    }
}