package com.grupo13.lavarapido.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.grupo13.lavarapido.model.entities.Atendimento;
import com.grupo13.lavarapido.model.entities.Procedimento;
import com.grupo13.lavarapido.model.entities.Veiculo;

public interface AtendimentoRepository extends CrudRepository<Atendimento, Long> {
    
    Iterable<Atendimento> findByVeiculo(Veiculo veiculo);
    Iterable<Atendimento> findByProcedimento(Procedimento procedimento);
}
