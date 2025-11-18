package com.grupo13.lavarapido.model.entities;

import java.time.Instant;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="Clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="Primeiro nome")
    private String primeiroNome;
    @Column(name="Sobrenome")
    private String sobrenome;
    @Column(name="Data de registro")
    @CreationTimestamp
    private Instant dataRegistro;
    
    public Cliente(){
    }

    public Cliente(String primeiroNome, String sobrenome){
        this.primeiroNome = primeiroNome;
        this.sobrenome = sobrenome;
        this.dataRegistro = Instant.now();
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }
    public String getSobrenome() {
        return sobrenome;
    }
    public Instant getDataRegistro() {
        return dataRegistro;
    }
    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }
    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    public void setDataRegistro(Instant dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
