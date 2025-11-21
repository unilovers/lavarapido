package com.grupo13.lavarapido.model.service;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo13.lavarapido.model.entities.Procedimento;
import com.grupo13.lavarapido.model.repository.ProcedimentoRepository;

@Service
public class ProcedimentoService {

    @Autowired
    private ProcedimentoRepository procedimentoRepository;

    public boolean novoProcedimento(Procedimento procedimento) throws Exception {

        if (procedimento.getNome() == null || procedimento.getNome().isEmpty()) {
            throw new Exception("o nome do procedimento é obrigatório!");
        }

        if (procedimento.getValor() == null || procedimento.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("o valor do procedimento deve ser informado e não pode ser negativo.");
        }

        if (procedimento.getTipoVeiculo() == null || procedimento.getTipoVeiculo().isEmpty()) {
            throw new Exception("o tipo de veículo é obrigatório!");
        }

        procedimentoRepository.save(procedimento);
        return true;
    }

    public List<Procedimento> getProcedimentos() {
        return procedimentoRepository.findAll();
    }

    public List<Procedimento> consultarProcedimentos(String nome) {
        return procedimentoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Procedimento> consultarProcedimentosByValor(BigDecimal valor) {
        return procedimentoRepository.findByValor(valor);
    }

    public List<Procedimento> consultarProcedimentosPorTipoVeiculo(String tipoVeiculo) {
        return procedimentoRepository.findByTipoVeiculoIgnoreCase(tipoVeiculo);
    }

    public boolean deletarProcedimento(Long id) throws Exception {
        if (id == null) {
            throw new Exception("ID inválido.");
        }
        
        try {
            procedimentoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception("Erro ao deletar procedimento: " + e.getMessage());
        }
    }

    public Procedimento buscarPorId(Long id) throws Exception {
        return procedimentoRepository.findById(id)
                .orElseThrow(() -> new Exception("procedimento não encontrado."));
    }

    public void atualizarProcedimento(Long id, Procedimento procedimentoAtualizado) throws Exception {

        Procedimento existente = procedimentoRepository.findById(id)
                .orElseThrow(() -> new Exception("procedimento não encontrado."));

        if (procedimentoAtualizado.getNome() == null || procedimentoAtualizado.getNome().isBlank()) {
            throw new Exception("o nome é obrigatório!");
        }

        if (procedimentoAtualizado.getValor() == null || procedimentoAtualizado.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("o valor não pode ser negativo ou nulo.");
        }

        existente.setNome(procedimentoAtualizado.getNome());
        existente.setValor(procedimentoAtualizado.getValor());
        existente.setTipoVeiculo(procedimentoAtualizado.getTipoVeiculo());

        procedimentoRepository.save(existente);
    }
}