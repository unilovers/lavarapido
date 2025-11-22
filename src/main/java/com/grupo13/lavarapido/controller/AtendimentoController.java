package com.grupo13.lavarapido.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo13.lavarapido.model.DTO.AtendimentoDTO;
import com.grupo13.lavarapido.model.service.AtendimentoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {
    
    private AtendimentoService atendimentoService;

    @Autowired
    public AtendimentoController(AtendimentoService atendimentoService) {
        this.atendimentoService = atendimentoService;
    }

    @PostMapping("/novo")
    public ResponseEntity<?> novoAtendimento(@RequestBody AtendimentoDTO atendimentoDTO) {
        try {
            atendimentoService.novoAtendimento(atendimentoDTO);
            return ResponseEntity.ok("Atendimento criado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAtendimentos() {
        return ResponseEntity.ok(atendimentoService.getAtendimentos());
    }
    
    @GetMapping("/consultaPorCliente")
    public ResponseEntity<?> getAtendimentosByClienteId(@RequestParam("clienteId") Long clienteId) {
        try {
            return ResponseEntity.ok(atendimentoService.getAtendimentosByClienteId(clienteId));
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<?> deletarAtendimento(@RequestParam("id") Long id) {
        try {
            atendimentoService.apagarAtendimento(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarAtendimento(@PathVariable("id") Long id, @RequestBody AtendimentoDTO atendimentoDTO) {
        try {
            atendimentoService.atualizarAtendimento(id, atendimentoDTO);
            return ResponseEntity.ok("Atendimento atualizado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
    
}
