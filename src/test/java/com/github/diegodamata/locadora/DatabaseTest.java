package com.github.diegodamata.locadora;

import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;

class DatabaseTest {

    static Connection connection;

    //a anotação BeforeAll precisa estar em um metodo estatico obrigatoriamente

    @BeforeAll
    static void setUpDatabase() throws Exception{
        connection = DriverManager
                .getConnection("jdbc:h2:mem:testedb", "sa", "");

        connection.createStatement().execute("CREATE TABLE users(id INT primary key, nome VARCHAR)");
    }

    @BeforeEach
    void insertUserTest() throws Exception{
        connection.createStatement()
                .execute("insert into users(id, nome) values (1, 'Diego')");
    }

    @Test
    void verificarUserExisteTest() throws Exception{
        var result = connection.createStatement()
                .executeQuery("select * from users where id = 1");

        Assertions.assertTrue(result.next());
    }

    // anotação AfterAll obrigatoriamente presica estar em um metodo static
    @AfterAll
    static void closeDatabase() throws Exception{
        connection.close();
    }
}
