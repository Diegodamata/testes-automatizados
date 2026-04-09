package com.github.diegodamata.locadora.model;

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
}
