package com.grupo13.lavarapido.model.DTO;

public class AtendimentoDTO {
    
    private Long veiculoId;
    private Long procedimentoId;

    public Long getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
    }

    public Long getProcedimentoId() {
        return procedimentoId;
    }

    public void setProcedimentoId(Long procedimentoId) {
        this.procedimentoId = procedimentoId;
    }

    @Override
    public String toString() {
        return "AtendimentoDTO{" +
                ", veiculoId='" + veiculoId + '\'' +
                ", procedimentoId='" + procedimentoId + '\'' +
                '}';
    }
}
