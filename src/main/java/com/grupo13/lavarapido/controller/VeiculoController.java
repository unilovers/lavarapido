package com.grupo13.lavarapido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.grupo13.lavarapido.model.DTO.VeiculoRequestDTO;
import com.grupo13.lavarapido.model.entities.Veiculo;
import com.grupo13.lavarapido.model.service.VeiculoService;


@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    
    private VeiculoService veiculoService;

    @Autowired
    public VeiculoController(VeiculoService veiculoService) {
        this.veiculoService = veiculoService;
    }
    
 @PostMapping("/novo")
    public ResponseEntity<?> novoVeiculo(@RequestBody VeiculoRequestDTO veiculo) {
        try {
            veiculoService.novoVeiculo(veiculo);
            return ResponseEntity.ok("Veiculo criado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


      @GetMapping
    public ResponseEntity<List<Veiculo>> getVeiculo() {
        List<Veiculo> veiculo = veiculoService.getVeiculos();
        return ResponseEntity.ok(veiculo);
    }

      @GetMapping("/consultatipo")
    public ResponseEntity<List<Veiculo>> consultaVeiculoportipo(@RequestParam("tipo") String tipo) {
        List<Veiculo> veiculosEncontrados;
        veiculosEncontrados = veiculoService.consultarVeiculosPorTipo(tipo);
        return ResponseEntity.ok(veiculosEncontrados);
    }
   
       @GetMapping("/consultaPlaca")
    public ResponseEntity<List<Veiculo>> consultaVeiculosporplaca(@RequestParam("placa") String placa) {
        List<Veiculo> veiculosEncontrados;
        veiculosEncontrados = veiculoService.consultarVeiculosPorPlaca(placa);
        return ResponseEntity.ok(veiculosEncontrados);
    }

      @GetMapping("/consultaFabricante")
    public ResponseEntity<List<Veiculo>> consultarVeiculoporfabricante(@RequestParam("fabricante") String fabricante) {
        List<Veiculo> veiculosEncontrados;
        veiculosEncontrados = veiculoService.consultarVeiculosPorFabricante(fabricante);
        return ResponseEntity.ok(veiculosEncontrados);
    }

   

    @GetMapping("/consultaModelo")
    public ResponseEntity<List<Veiculo>> consultaVeiculopormodelo(@RequestParam("modelo") String modelo) {
        List<Veiculo> veiculosEncontrados;
        veiculosEncontrados = veiculoService.consultarVeiculosPorModelo(modelo);
        return ResponseEntity.ok(veiculosEncontrados);
    }

      @DeleteMapping("/apagar/{id}")
    public ResponseEntity<?> deletarVeiculo(@PathVariable("id") Long id) {
        try {
            veiculoService.deletarVeiculo(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    
        @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable("id") Long id, @RequestBody VeiculoRequestDTO veiculo) {
        try {
            veiculoService.atualizarVeiculo(id, veiculo);
            return ResponseEntity.ok(true);
        }catch (Exception e) {
            if (e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } 
            if (e.getMessage().contains("obrigatório")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }
    
}


