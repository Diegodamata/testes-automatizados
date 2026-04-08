package com.github.diegodamata.locadora.model;

import static org.assertj.core.api.Assertions.*;
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

        //assertions do pacote assertj
        assertThat(nome).isEqualTo("Maria");
        assertThat(nome).isLessThan("MariaS");
        assertThat(nome.length()).isLessThan(50);
        assertThat(nome).contains("r");
    }

    @Test
    @DisplayName("Deve criar cliente sem nome")
    void deveCriarClienteSemNome(){

        Cliente cliente = new Cliente(null);

        String nome = cliente.getNome();

        Assertions.assertNull(nome);
    }
}
