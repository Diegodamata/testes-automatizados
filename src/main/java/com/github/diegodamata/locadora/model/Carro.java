package com.github.diegodamata.locadora.model;

public class Carro {

    private String modelo;
    private String placa;
    private double valorDiaria;

    public Carro(String modelo, String placa, double valorDiaria) {
        this.modelo = modelo;
        this.placa = placa;
        this.valorDiaria = valorDiaria;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public double calcularValorAluguel(int dias){
        double desconto = 0;
        if (dias >= 5){
            desconto = 50.0;
        }
        return (dias * valorDiaria) - desconto;
    }
}
