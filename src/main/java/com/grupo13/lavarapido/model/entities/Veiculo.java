package com.grupo13.lavarapido.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name="Veiculos")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String tipo;        // Tipo de veiculo (Carro, moto, etc)
    private String placa;       // Placa do veiculo
    private String fabricante;  // Fabricante do veiculo (Ex: Chevrolet)
    private String modelo;      // Modelo do veiculo (Ex: Onix)

    @ManyToOne
    @JoinColumn(name="cliente_id", nullable=false)
    private Cliente cliente;    // Dono do veiculo

    public Veiculo(){
    }

    public Veiculo(String tipo, String placa, String fabricante, String modelo){
        this.tipo = tipo;
        this.placa = placa;
        this.fabricante = fabricante;
        this.modelo = modelo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public String getFabricante() {
        return fabricante;
    }

    public String getModelo() {
        return modelo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "id='" + id + '\'' +
                "tipo='" + tipo + '\'' +
                ", placa='" + placa + '\'' +
                ", fabricante='" + fabricante + '\'' +
                ", modelo='" + modelo + '\'' +
                ", cliente='" + cliente.getNomeInteiro() + '\'' +
                '}';
    }

}