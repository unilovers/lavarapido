package com.grupo13.lavarapido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.grupo13.lavarapido.model.entities.Cliente;
import com.grupo13.lavarapido.model.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }
    
    @PostMapping("/novo")
    public ResponseEntity<?> novoCliente(@RequestBody Cliente cliente) {
        try {
            clienteService.novoCliente(cliente);
            return ResponseEntity.ok("Cliente criado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Cliente>> getClientes() {
        List<Cliente> clientes = clienteService.getClientes();
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/consulta")
    public ResponseEntity<List<Cliente>> consultaClientesNome(@RequestParam("primeiroNome") String primeiroNome) {
        List<Cliente> clientesEncontrados;
        clientesEncontrados = clienteService.consultarClientesPrimeiroNome(primeiroNome);
        return ResponseEntity.ok(clientesEncontrados);
    }

    @GetMapping("/consultaSobrenome")
    public ResponseEntity<List<Cliente>> consultaClientesSobrenome(@RequestParam("sobrenome") String sobrenome) {
        List<Cliente> clientesEncontrados;
        clientesEncontrados = clienteService.consultarClientesSobrenome(sobrenome);
        return ResponseEntity.ok(clientesEncontrados);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable("id") Long id) {
        try {
            clienteService.deletarCliente(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
        try {
            clienteService.atualizarCliente(id, cliente);
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