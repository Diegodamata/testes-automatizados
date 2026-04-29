package com.github.diegodamata.locadora.service;

import com.github.diegodamata.locadora.entity.Carro;
import com.github.diegodamata.locadora.exeptions.EntityNotFoundException;
import com.github.diegodamata.locadora.repository.CarroRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

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

        Carro carroParaSalvar = new Carro("Honda HRV", 150.0, 2015);

        Carro carroSalvo = new Carro("Honda HRV", 150.0, 2015);
        carroSalvo.setId(1L);

        Mockito.when(carroRepository.save(Mockito.any())).thenReturn(carroSalvo);

        Carro carro = carroService.salvar(carroParaSalvar);

        assertNotNull(carro);
        assertEquals("Honda HRV", carroSalvo.getModelo());

        Mockito.verify(carroRepository).save(Mockito.any());
    }

    @Test
    void deveDarErroAoSalvarCarroComDiariaNegativa(){
        Carro carro = new Carro("Honda HRV", 0, 2015);

        var erro = Assertions.catchThrowable(() -> carroService.salvar(carro));

        Assertions.assertThat(erro).isInstanceOf(IllegalArgumentException.class);

        Mockito.verify(carroRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void deveAtualizarUmCarro(){

        var carroEncontrado = new Carro("Gol", 100.0, 2015);
        Mockito.when(carroRepository.findById(1L)).thenReturn(Optional.of(carroEncontrado));

        var carroAtualizado = new Carro("Gol", 100.0, 2015);
        Mockito.when(carroRepository.save(Mockito.any())).thenReturn(carroAtualizado);

        Long id = 1L;
        var carro = new Carro("Honda HRV", 0, 2015);

        var resultado = carroService.atualizar(id, carro);

        assertEquals("Gol", resultado.getModelo());
        assertEquals(100.0, resultado.getValorDiaria());

        Mockito.verify(carroRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    void deveDarErroAoAtualizarCarroInexistente(){
        Long id = 1L;
        var carro = new Carro("Honda HRV", 150.0, 2015);

        Mockito.when(carroRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        var erro = Assertions.catchThrowable(() -> carroService.atualizar(id, carro));

        Assertions.assertThat(erro).isInstanceOf(EntityNotFoundException.class);

        Mockito.verify(carroRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void deveDeletarUmCarroPorId(){
        Long id = 1L;
        var carro = new Carro("Honda HRV", 150.0, 2015);

        Mockito.when(carroRepository.findById(id)).thenReturn(Optional.of(carro));

        carroService.deletar(id);

        Mockito.verify(carroRepository, Mockito.times(1)).deleteById(Mockito.any());
    }

    @Test
    void deveDarErroAoDeletarCarroPorId(){

        Mockito.when(carroRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        var erro = Assertions.catchThrowable(() -> carroService.deletar(1L));

        Assertions.assertThat(erro).isInstanceOf(EntityNotFoundException.class);
        Assertions.assertThat(erro).hasMessage("Carro não encontrado!", erro.getMessage());

        Mockito.verify(carroRepository, Mockito.never()).deleteById(Mockito.any());
    }

    @Test
    void deveBuscarCarroPorId(){
        Long id = 1L;
        var carro = new Carro("Honda HRV", 150.0, 2015);

        Mockito.when(carroRepository.findById(Mockito.any())).thenReturn(Optional.of(carro));

        var resultado = carroService.buscarPorId(id);

        assertEquals("Honda HRV", resultado.getModelo());

        Mockito.verify(carroRepository, Mockito.atLeastOnce()).findById(Mockito.any());
    }

    @Test
    void deveDarErroAoBuscarCarroPorId(){
        Mockito.when(carroRepository.findById(Mockito.any())).thenReturn(Optional.empty());

        var erro = Assertions.catchThrowable(() -> carroService.buscarPorId(1L));

        Assertions.assertThat(erro).isInstanceOf(EntityNotFoundException.class);
        Assertions.assertThat(erro).hasMessage("Carro não encontrado!", erro.getMessage());
    }
}