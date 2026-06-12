package com.example.loja.repositories;

import com.example.loja.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoriaId(Long categoriaId);

    // Busca produtos com seus pedidos carregados (evita N+1)
    @Query("SELECT DISTINCT p FROM Produto p LEFT JOIN FETCH p.pedidos WHERE p.id = :id")
    java.util.Optional<Produto> findByIdWithPedidos(@Param("id") Long id);
}
