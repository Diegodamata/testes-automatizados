package com.github.diegodamata.locadora.service;

import com.github.diegodamata.locadora.entity.Carro;
import com.github.diegodamata.locadora.exeptions.EntityNotFoundException;
import com.github.diegodamata.locadora.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Carro atualizar(Long id, Carro carro){
        var carroEncontrado = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado!"));

        atulizarCarroEncontrado(carro, carroEncontrado);

        return repository.save(carroEncontrado);
    }

    private static void atulizarCarroEncontrado(Carro carro, Carro carroEncontrado) {
        carroEncontrado.setAno(carro.getAno());
        carroEncontrado.setModelo(carro.getModelo());
        carroEncontrado.setValorDiaria(carro.getValorDiaria());
    }

    public void deletar(Long id){
        var carroEncontrado = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado!"));
        repository.deleteById(id);
    }

    public Carro buscarPorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Carro não encontrado!"));
    }

    public List<Carro> listarTodos(){
        return repository.findAll();
    }
}
