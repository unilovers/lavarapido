package com.grupo13.lavarapido.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo13.lavarapido.model.entities.Cliente;
import com.grupo13.lavarapido.model.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public boolean novoCliente(Cliente cliente) throws Exception {
        if (cliente.getPrimeiroNome() == null || cliente.getPrimeiroNome().isEmpty()) {
            throw new Exception("Primeiro nome do cliente é obrigatório.");
        }

        if (cliente.getSobrenome() == null || cliente.getSobrenome().isEmpty()) {
            throw new Exception("Sobrenome do cliente é obrigatório.");
        }

        clienteRepository.save(cliente);
        return true;
    }

    public List<Cliente> getClientes() {
        Iterable<Cliente> clientes = clienteRepository.findAll();
        return (List<Cliente>) clientes;
    }

    public List<Cliente> consultarClientesPrimeiroNome (String primeiroNome) {
        Iterable<Cliente> clientesEncontrados = clienteRepository.findByPrimeiroNome(primeiroNome);
        return (List<Cliente>) clientesEncontrados;
    }

    public List<Cliente> consultarClientesSobrenome (String sobrenome) {
        Iterable<Cliente> clientesEncontrados = clienteRepository.findBySobrenome(sobrenome);
        return (List<Cliente>) clientesEncontrados;
    }

    public boolean deletarCliente(Long id) throws Exception {
        if (id == null) {
            throw new Exception("ID inválido");
        }
        
        try {
            clienteRepository.deleteById(id);
            return true;
        } catch (Exception e){
            throw new Exception("Erro ao deletar cliente" + e.getMessage());
        }
    }
/* 
    public boolean atualizarCliente(Cliente cliente) throws Exception {
        
        if (cliente.getPrimeiroNome() == null || cliente.getPrimeiroNome().isEmpty()) {
            throw new Exception("Primeiro nome do cliente é obrigatório.");
        }

        if (cliente.getSobrenome() == null || cliente.getSobrenome().isEmpty()) {
            throw new Exception("Sobrenome do cliente é obrigatório.");
        }

        try {
            clienteRepository.save(cliente);
            return true;
        }catch (Exception e) {
            throw new Exception("Erro em atualizar cliente: " + e.getMessage());
        }
    }*/

    public Cliente atualizarCliente(Long id, Cliente clienteDetails) throws Exception {
        
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        
        if (clienteExistente.isEmpty()) {
            throw new Exception("Cliente com ID " + id + " não encontrado.");
        }

        if (clienteDetails.getPrimeiroNome() == null || clienteDetails.getPrimeiroNome().isEmpty()) {
            throw new Exception("Primeiro nome do cliente é obrigatório.");
        }

        if (clienteDetails.getSobrenome() == null || clienteDetails.getSobrenome().isEmpty()) {
            throw new Exception("Sobrenome do cliente é obrigatório.");
        }

        Cliente clienteParaAtualizar = clienteExistente.get();
        clienteParaAtualizar.setPrimeiroNome(clienteDetails.getPrimeiroNome());
        clienteParaAtualizar.setSobrenome(clienteDetails.getSobrenome());

        return clienteRepository.save(clienteParaAtualizar);
    }
}