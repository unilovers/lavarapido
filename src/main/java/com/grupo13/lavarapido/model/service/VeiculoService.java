package com.grupo13.lavarapido.model.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo13.lavarapido.model.DTO.VeiculoRequestDTO;
import com.grupo13.lavarapido.model.entities.Cliente;
import com.grupo13.lavarapido.model.entities.Veiculo;
import com.grupo13.lavarapido.model.repository.ClienteRepository;
import com.grupo13.lavarapido.model.repository.VeiculoRepository;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ClienteRepository clienteRepository; 

 
    public boolean novoVeiculo(VeiculoRequestDTO veiculo) throws Exception {
        
        if (veiculo.getTipo() == null || veiculo.getTipo().isEmpty()) {
            throw new Exception("O tipo do veículo é obrigatório.");
        }
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isEmpty()) {
            throw new Exception("A placa do veículo é obrigatória.");
        }
        if (veiculo.getFabricante() == null || veiculo.getFabricante().isEmpty()) {
            throw new Exception("O fabricante do veículo é obrigatório.");
        }
        if (veiculo.getModelo() == null || veiculo.getModelo().isEmpty()) {
            throw new Exception("O modelo do veículo é obrigatório.");
        }
        
        if (veiculo.getClienteId() == null) {
            throw new Exception("O cliente (dono) do veículo é obrigatório.");
        }

       
        Optional<Cliente> cliente = clienteRepository.findById(veiculo.getClienteId());
        if (cliente.isEmpty()) {
            throw new Exception("Cliente com ID " + veiculo.getClienteId() + " não encontrado. O veículo não pode ser salvo.");
        }
        
        Veiculo novoVeiculo = new Veiculo();
        novoVeiculo.setTipo(veiculo.getTipo());
        novoVeiculo.setPlaca(veiculo.getPlaca());
        novoVeiculo.setFabricante(veiculo.getFabricante());
        novoVeiculo.setModelo(veiculo.getModelo());
        novoVeiculo.setCliente(cliente.get());

        veiculoRepository.save(novoVeiculo);
        return true;
    }
   
    public List<Veiculo> getVeiculos() {
        return (List<Veiculo>) veiculoRepository.findAll();
    }

    public Optional<Veiculo> getVeiculoById(Long id) {
        return veiculoRepository.findById(id);
    }


    public List<Veiculo> consultarVeiculosPorPlaca (String placa) {
      
        Iterable<Veiculo> veiculosEncontrados = veiculoRepository.findByPlaca(placa); 
        return (List<Veiculo>) veiculosEncontrados;
    }

   
    public List<Veiculo> consultarVeiculosPorTipo (String tipo) {
     
        Iterable<Veiculo> veiculosEncontrados = veiculoRepository.findByTipo(tipo); 
        return (List<Veiculo>) veiculosEncontrados;
    }
    
     public List<Veiculo> consultarVeiculosPorFabricante (String tipo) {
     
        Iterable<Veiculo> veiculosEncontrados = veiculoRepository.findByTipo(tipo); 
        return (List<Veiculo>) veiculosEncontrados;
    }
    
         public List<Veiculo> consultarVeiculosPorModelo (String tipo) {
     
        Iterable<Veiculo> veiculosEncontrados = veiculoRepository.findByTipo(tipo); 
        return (List<Veiculo>) veiculosEncontrados;
    }

    public Veiculo atualizarVeiculo(Long id, VeiculoRequestDTO veiculoDetails) throws Exception {
        Optional<Veiculo> veiculoExistente = veiculoRepository.findById(id);

        if (veiculoExistente.isEmpty()) {
            throw new Exception("Veículo com ID " + id + " não encontrado.");
        }
        
       
        if (veiculoDetails.getTipo() == null || veiculoDetails.getTipo().isEmpty()) {
            throw new Exception("O tipo do veículo é obrigatório.");
        }
        if (veiculoDetails.getPlaca() == null || veiculoDetails.getPlaca().isEmpty()) {
            throw new Exception("A placa do veículo é obrigatória.");
        }
        if (veiculoDetails.getFabricante() == null || veiculoDetails.getFabricante().isEmpty()) {
            throw new Exception("O fabricante do veículo é obrigatório.");
        }
        if (veiculoDetails.getModelo() == null || veiculoDetails.getModelo().isEmpty()) {
            throw new Exception("O modelo do veículo é obrigatório.");
        }
        
        if (veiculoDetails.getClienteId() == null) {
             throw new Exception("O cliente (dono) do veículo é obrigatório.");
        }
        
        Optional<Cliente> clienteExistente = clienteRepository.findById(veiculoDetails.getClienteId());
        if (clienteExistente.isEmpty()) {
            throw new Exception("Novo Cliente com ID " + veiculoDetails.getClienteId() + " não encontrado. A atualização falhou.");
        }

        Veiculo veiculoParaAtualizar = veiculoExistente.get();
        veiculoParaAtualizar.setTipo(veiculoDetails.getTipo());
        veiculoParaAtualizar.setPlaca(veiculoDetails.getPlaca());
        veiculoParaAtualizar.setFabricante(veiculoDetails.getFabricante());
        veiculoParaAtualizar.setModelo(veiculoDetails.getModelo());
        veiculoParaAtualizar.setCliente(clienteExistente.get());
        
        veiculoParaAtualizar.setCliente(clienteExistente.get());

        return veiculoRepository.save(veiculoParaAtualizar);
    }
    
    public boolean deletarVeiculo(Long id) throws Exception {
        if (id == null) {
            throw new Exception("ID inválido");
        }
        
        try {
            veiculoRepository.deleteById(id);
            return true;
        } catch (Exception e){
    
            throw new Exception("Erro ao deletar veículo: " + e.getMessage());
        }
    }
}