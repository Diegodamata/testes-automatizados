package com.github.diegodamata.locadora.service;

import com.github.diegodamata.locadora.repository.CarroRepository;
import org.springframework.stereotype.Service;

@Service
public class CarroService {

    private final CarroRepository repository;

    public CarroService(CarroRepository repository) {
        this.repository = repository;
    }
}
