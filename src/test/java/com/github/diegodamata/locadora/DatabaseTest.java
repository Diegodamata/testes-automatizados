package com.github.diegodamata.locadora;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseTest {

    static Connection connection;

    //a anotação BeforeAll precisa estar em um metodo estatico obrigatoriamente

    @BeforeAll
    static void setUpDatabase() throws Exception{
        connection = DriverManager
                .getConnection("jdbc:h2:mem", "sa", "");

        connection.createStatement().execute("CREATE TABLE users(id INT primary key, nome VARCHAR)");
    }

    @BeforeEach
    void insertUserTest() throws Exception{
        connection.createStatement()
                .execute("insert into users(id, nome) values (1, 'Diego')");
    }
}
