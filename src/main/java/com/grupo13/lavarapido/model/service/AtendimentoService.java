package com.grupo13.lavarapido.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo13.lavarapido.model.DTO.AtendimentoDTO;
import com.grupo13.lavarapido.model.entities.Atendimento;
import com.grupo13.lavarapido.model.entities.Cliente;
import com.grupo13.lavarapido.model.entities.Procedimento;
import com.grupo13.lavarapido.model.entities.Veiculo;
import com.grupo13.lavarapido.model.repository.AtendimentoRepository;
import com.grupo13.lavarapido.model.repository.ClienteRepository;
import com.grupo13.lavarapido.model.repository.ProcedimentoRepository;
import com.grupo13.lavarapido.model.repository.VeiculoRepository;

@Service
public class AtendimentoService {
    
    @Autowired
    private AtendimentoRepository atendimentoRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;
    @Autowired
    private ProcedimentoRepository procedimentoRepository;
    @Autowired
    private ClienteRepository clienteRepository;


    public boolean novoAtendimento(AtendimentoDTO atendimentoDTO) throws Exception {
        if (atendimentoDTO.getVeiculoId() == null || atendimentoDTO.getProcedimentoId() == null) {
            throw new Exception("Dados incompletos para criar um novo atendimento.");
        }

        Optional<Veiculo> veiculo = veiculoRepository.findById(atendimentoDTO.getVeiculoId());
        Optional<Procedimento> procedimento = procedimentoRepository.findById(atendimentoDTO.getProcedimentoId());

        if (!veiculo.isPresent()) {
            throw new Exception("Veículo não encontrado com o ID: " + atendimentoDTO.getVeiculoId());
        }

        if (!procedimento.isPresent()) {
            throw new Exception("Procedimento não encontrado com o ID: " + atendimentoDTO.getProcedimentoId());
        }

        if (procedimento.get().getTipoVeiculo() != veiculo.get().getTipo()) {
            throw new Exception("O tipo do veículo não é compatível com o procedimento selecionado.");
        }
        
        Atendimento atendimento = new Atendimento();
        atendimento.setVeiculo(veiculo.get());
        atendimento.setProcedimento(procedimento.get());
        atendimentoRepository.save(atendimento);
        return true;
    }

    public List<Atendimento> getAtendimentos() {
        return (List<Atendimento>) atendimentoRepository.findAll();
    }
    
    public Optional<Atendimento> getAtendimentoById(Long id) {
        return atendimentoRepository.findById(id);
    }

    public void deleteAtendimento(Long id) {
        atendimentoRepository.deleteById(id);
    }

    public List<Atendimento> getAtendimentosByVeiculoId(Long veiculoId) throws Exception {
        Optional<Veiculo> veiculo = veiculoRepository.findById(veiculoId);
        if (!veiculo.isPresent()) {
            throw new Exception("Veículo não encontrado com o ID: " + veiculoId);
        }
        return (List<Atendimento>) atendimentoRepository.findByVeiculo(veiculo.get());
    }

    public List<Atendimento> getAtendimentosByClienteId(Long clienteId) throws Exception {
        if (clienteId == null) {
            throw new Exception("ID do cliente não pode ser nulo.");
        }
        
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if (!cliente.isPresent()) {
            throw new Exception("Cliente não encontrado com o ID: " + clienteId);
        }
        
        List<Veiculo> veiculos = veiculoRepository.findByCliente(cliente.get());
        List<Atendimento> atendimentos = new java.util.ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            atendimentoRepository.findByVeiculo(veiculo).forEach(atendimentos::add);
        }
        return atendimentos;
    }

    public boolean apagarAtendimento(Long id) {
        if (!atendimentoRepository.existsById(id)) {
            return false;
        }
        atendimentoRepository.deleteById(id);
        return true;
    }

    public void atualizarAtendimento(Long id, AtendimentoDTO atendimentoDTO) throws Exception {
        Optional<Atendimento> atendimentoOpt = atendimentoRepository.findById(id);
        if (!atendimentoOpt.isPresent()) {
            throw new Exception("Atendimento não encontrado com o ID: " + id);
        }

        Atendimento atendimento = atendimentoOpt.get();

        if (atendimentoDTO.getVeiculoId() != null) {
            Optional<Veiculo> veiculo = veiculoRepository.findById(atendimentoDTO.getVeiculoId());
            if (!veiculo.isPresent()) {
                throw new Exception("Veículo não encontrado com o ID: " + atendimentoDTO.getVeiculoId());
            }
            atendimento.setVeiculo(veiculo.get());
        }

        if (atendimentoDTO.getProcedimentoId() != null) {
            Optional<Procedimento> procedimento = procedimentoRepository.findById(atendimentoDTO.getProcedimentoId());
            if (!procedimento.isPresent()) {
                throw new Exception("Procedimento não encontrado com o ID: " + atendimentoDTO.getProcedimentoId());
            }
            atendimento.setProcedimento(procedimento.get());
        }

        atendimentoRepository.save(atendimento);
    }
}
