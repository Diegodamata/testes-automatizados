package com.github.diegodamata.locadora.repository;

import com.github.diegodamata.locadora.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    List<Carro> findByModelo(String modelo);
}
