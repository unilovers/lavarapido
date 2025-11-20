package com.grupo13.lavarapido.model.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.grupo13.lavarapido.model.entities.Produto;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long> {
    
    Iterable<Produto> findByNome(String nome);
    @Query("SELECT p FROM Produto p WHERE p.valor = :valorParam")
    List<Produto> findByValor(@Param("valorParam") BigDecimal valor);
}
