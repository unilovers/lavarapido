package com.grupo13.lavarapido.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.entities.Cliente;
import com.example.demo.model.repository.ClienteRepository;

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

        clienteRepositiry.save(cliente);
        return true;
    }

    public List<Cliente> getClientes() {
        Iterable<Cliente> clientes = clienteRepositiry.findAll();
        return (List<Cliente>) clientes;
    }

    public List<Cliente> consultarClientesPrimeiroNome (String primeiroNome) {
        Iterable<Cliente> clientesEncontrados = ClienteRepository.findByPrimeiroNome(primeiroNome);
        return (List<Cliente>) clientesEncontrados;
    }

    public List<Cliente> consultarClientesSobrenome (String sobrenome) {
        Iterable<Cliente> clientesEncontrados = ClienteRepository.findBySobrenome(sobrenome);
        return (List<Cliente>) clientesEncontrados;
    }

    public boolean deletarCliente(Long id) throws Exception {
        if (id = null) {
            throw new Exception("ID inválido");
        }
        
        try {
            clienteRepositiry.deleteById(id);
            return true;
        } catch (Exception e){
            throw new Exception("Erro ao deletar cliente" + e.getMessege());
        }
    }

    public boolean atualizarCliente(Cliente cliente) throws Exception {
        
        if (cliente.getPrimeiroNome() == null || cliente.getPrimeiroNome().isEmpty()) {
            throw new Exception("Primeiro nome do cliente é obrigatório.");
        }

        if (cliente.getSobrenome() == null || cliente.getSobrenome().isEmpty()) {
            throw new Exception("Sobrenome do cliente é obrigatório.");
        }

        try {
            clienteRepositiry.save(cliente);
            return true;
        }catch (Exception e) {
            throw new Exception("Erro em atualizar cliente: " + e.getMessege());
        }
    }
}