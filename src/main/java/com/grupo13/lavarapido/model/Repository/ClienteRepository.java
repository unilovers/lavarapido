package com.grupo13.lavarapido.model.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.grupo13.lavarapido.model.entities.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    
    Iterable<Cliente> findByPrimeiroNome(String primeiroNome);
    Iterable<Cliente> findBySobrenome(String sobrenome);
    
}