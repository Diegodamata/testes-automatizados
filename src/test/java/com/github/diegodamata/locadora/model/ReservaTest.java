package com.github.diegodamata.locadora.model;

import static org.assertj.core.api.Assertions.*;
import com.github.diegodamata.locadora.exeptions.ReservaInvalidaException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReservaTest {

    Cliente cliente;
    Carro carro;

    @BeforeEach
    void setUp(){
        cliente = new Cliente("Diego");
        carro = new Carro("Honda", "123B4-6", 100.0);
    }

    @Test
    @DisplayName("Deve criar corretamente uma reserva")
    void deveCriarReserva(){
        var quantidadeDeDias = 5;

        var reserva = new Reserva(cliente, carro, quantidadeDeDias);

        Assertions.assertNotNull(reserva);
    }

    @Test
    @DisplayName("Deve dar erro ao criar uma reserva com dias negativo")
    void deveDarErroAoCriarUmaRservaComDiasNegativo(){
        // JUnit
        Assertions.assertThrows(ReservaInvalidaException.class, () -> new Reserva(cliente, carro, 0));
        Assertions.assertDoesNotThrow(() -> new Reserva(cliente, carro, 1));

        //AssertJ
        var erro = catchThrowable(() -> new Reserva(cliente, carro, 0));

        assertThat(erro)
                .isInstanceOf(ReservaInvalidaException.class)
                .hasMessage("Quantidade de dias deve ser maior que 0");
    }


    @Test
    @DisplayName("Deve calcular corretamente o valor da reserva")
    void deveCalcularValorReserva(){
        var reserva = new Reserva(cliente, carro, 3);

        double valorReserva = reserva.valorDaReserva();

        //JUnit
        Assertions.assertNotNull(valorReserva);
        Assertions.assertEquals(300.0, valorReserva);

        //Assertj
        assertThat(valorReserva).isEqualTo(300.0);
    }
}
