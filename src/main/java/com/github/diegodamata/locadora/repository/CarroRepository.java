package com.github.diegodamata.locadora.repository;

import com.github.diegodamata.locadora.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
