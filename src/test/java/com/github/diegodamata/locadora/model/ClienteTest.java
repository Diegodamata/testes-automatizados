package com.github.diegodamata.locadora.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClienteTest {

    @Test
    @DisplayName("Deve criar cliente com nome")
    void deveCriarClienteComNome(){

        Cliente cliente = new Cliente("Maria");

        String nome = cliente.getNome();

        Assertions.assertNotNull(nome);
        Assertions.assertTrue(nome.startsWith("M"));
        Assertions.assertFalse(nome.length() == 100);
    }

}
