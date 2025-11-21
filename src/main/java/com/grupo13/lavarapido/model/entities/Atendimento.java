package com.grupo13.lavarapido.model.entities;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Atendimentos")
public class Atendimento {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="veiculo_id", nullable=false)
    private Veiculo veiculo;
    @ManyToOne
    @JoinColumn(name="procedimento_id", nullable=false)
    private Procedimento procedimento;

    private Instant dataCriacaoAtendimento;

    public Atendimento(){
    }

    public Atendimento(Veiculo veiculo, Procedimento procedimento){
        this.veiculo = veiculo;
        this.procedimento = procedimento;
        this.dataCriacaoAtendimento = Instant.now();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Procedimento getProcedimento() {
        return procedimento;
    }
    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    public BigDecimal getValor() {
        return procedimento.getValor();
    }

    public Instant getDataCriacaoAtendimento() {
        return dataCriacaoAtendimento;
    }

    public void setDataCriacaoAtendimento(Instant dataCriacaoAtendimento) {
        this.dataCriacaoAtendimento = dataCriacaoAtendimento;
    }

    @Override
    public String toString() {
        return "Atendimento{" +
                "id='" + id + '\'' +
                ", veiculo='" + veiculo + '\'' +
                ", procedimento='" + procedimento.getNome() + '\'' +
                ", valor='" + getValor() + '\'' +
                ", dataCriacaoAtendimento='" + dataCriacaoAtendimento + '\'' +
                '}';
    }
}