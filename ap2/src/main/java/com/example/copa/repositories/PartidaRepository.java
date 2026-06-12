package com.example.copa.repositories;
import com.example.copa.entities.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
    @Query("SELECT DISTINCT p FROM Partida p LEFT JOIN FETCH p.selecoes WHERE p.id = :id")
    Optional<Partida> findByIdWithSelecoes(@Param("id") Long id);

    @Query("SELECT p FROM Partida p JOIN p.selecoes s WHERE s.nome = :nomeSelecao")
    List<Partida> findBySelecaoNome(@Param("nomeSelecao") String nomeSelecao);
}