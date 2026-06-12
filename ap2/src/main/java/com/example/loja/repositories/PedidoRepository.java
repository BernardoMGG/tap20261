package com.example.loja.repositories;

import com.example.loja.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByNomeClienteContainingIgnoreCase(String nomeCliente);

    // Busca pedido com seus produtos carregados (evita N+1)
    @Query("SELECT DISTINCT p FROM Pedido p LEFT JOIN FETCH p.produtos WHERE p.id = :id")
    Optional<Pedido> findByIdWithProdutos(@Param("id") Long id);
}
