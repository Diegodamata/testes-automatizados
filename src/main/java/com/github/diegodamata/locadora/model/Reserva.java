package com.github.diegodamata.locadora.model;

import com.github.diegodamata.locadora.exeptions.ReservaInvalidaException;

public class Reserva {

    private Cliente cliente;
    private Carro carro;
    private int quantidaDeDias;

    public Reserva(Cliente cliente, Carro carro, int quantidaDeDias) {
        this.cliente = cliente;
        this.carro = carro;
        this.quantidaDeDias = quantidaDeDias;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public int getQuantidaDeDias() {
        return quantidaDeDias;
    }

    public void setQuantidaDeDias(int quantidaDeDias) {
        this.quantidaDeDias = quantidaDeDias;
    }

    public double valorDaReserva(){
        if (quantidaDeDias <= 0){
            throw new ReservaInvalidaException("Quantidade de dias deve ser maior que 0");
        }
        return carro.calcularValorAluguel(quantidaDeDias);
    }
}
