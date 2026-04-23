package com.github.diegodamata.locadora.service;

import com.github.diegodamata.locadora.entity.Carro;
import com.github.diegodamata.locadora.repository.CarroRepository;
import org.springframework.stereotype.Service;

@Service
public class CarroService {

    private final CarroRepository repository;

    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }

    public Carro salvar(Carro carro){
        if (carro.getValorDiaria() <= 0){
            throw new IllegalArgumentException("O valor da diária não pode ser negativo ou zero!");
        }
        return repository.save(carro);
    }
}
