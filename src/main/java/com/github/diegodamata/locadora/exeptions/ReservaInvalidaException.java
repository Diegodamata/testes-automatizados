package com.github.diegodamata.locadora.exeptions;

public class ReservaInvalidaException extends RuntimeException{

    public ReservaInvalidaException(String message){
        super(message);
    }
}
