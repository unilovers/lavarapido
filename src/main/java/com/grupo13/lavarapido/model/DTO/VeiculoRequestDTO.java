package com.grupo13.lavarapido.model.DTO;

public class VeiculoRequestDTO {
    
    private String tipo;
    private String placa;
    private String fabricante;
    private String modelo;
    private Long clienteId;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public String toString() {
        return "VeiculoRequestDTO{" +
                "tipo='" + tipo + '\'' +
                ", placa='" + placa + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", modelo='" + modelo + '\'' +
                ", clienteId=" + clienteId +
                '}';
    }
}
