package com.grupo13.lavarapido.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import com.example.demo.model.entities.Cliente;
import com.example.demo.model.service.ClienteService;

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
    public ResponseEntity<List<Cliente>> consultaClientes(@RequestParam("nome") String nome) {
        List<Cliente> clientesEncontrados;
        
        clientesEncontrados = clienteService.consultarClientes(nome);

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

    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizarCliente(@RequestBody Cliente cliente) {
        try {
            clienteService.atualizarCliente(cliente);
            return ResponseEntity.ok(true);
        }catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
    
}