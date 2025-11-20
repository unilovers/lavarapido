

package com.grupo13.lavarapido.model.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.grupo13.lavarapido.model.entities.Veiculo;
import java.util.List;

@Repository
public interface VeiculoRepository extends CrudRepository<Veiculo, Long> {
  
    List<Veiculo> findByPlaca(String placa);
    List<Veiculo> findByTipo(String tipo);
    List<Veiculo> findByModelo(String modelo);
    List<Veiculo> findByFabricante(String fabricante);
}
