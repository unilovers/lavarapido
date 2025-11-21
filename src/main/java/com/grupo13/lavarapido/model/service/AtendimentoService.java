package com.grupo13.lavarapido.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo13.lavarapido.model.DTO.AtendimentoDTO;
import com.grupo13.lavarapido.model.entities.Atendimento;
import com.grupo13.lavarapido.model.entities.Procedimento;
import com.grupo13.lavarapido.model.entities.Veiculo;
import com.grupo13.lavarapido.model.repository.AtendimentoRepository;
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
        Atendimento atendimento = new Atendimento();
        atendimento.setVeiculo(veiculo.get());
        atendimento.setProcedimento(procedimento.get());
        atendimentoRepository.save(atendimento);
        return true;
    }

    public List<Atendimento> getAtendimentos() {
        return (List<Atendimento>) atendimentoRepository.findAll();
    }
    
}
