package com.github.diegodamata.locadora.controller;

import com.github.diegodamata.locadora.entity.Carro;
import com.github.diegodamata.locadora.exeptions.EntityNotFoundException;
import com.github.diegodamata.locadora.service.CarroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("carros")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody Carro carro){
        try {
            var carroSalvo = carroService.salvar(carro);
            return ResponseEntity.status(HttpStatus.CREATED).body(carroSalvo);
        } catch (IllegalArgumentException e){
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_CONTENT)
                    .body(e.getMessage());
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Carro> detalhesCarro(@PathVariable Long id){
        try{
            var carroEncontrado = carroService.buscarPorId(id);
            return ResponseEntity.ok(carroEncontrado);
        } catch(EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Carro>> listarTodos(){
        return ResponseEntity.ok(carroService.listarTodos());
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> atualizar(@PathVariable("id") Long id, @RequestBody Carro carro){
        try {
            carroService.atualizar(id, carro);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.noContent().build();
        }
    }
}
