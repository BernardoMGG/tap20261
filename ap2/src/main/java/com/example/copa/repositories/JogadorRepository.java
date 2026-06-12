package com.example.copa.repositories;
import com.example.copa.entities.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface JogadorRepository extends JpaRepository<Jogador, Long> {
    List<Jogador> findBySelecaoNome(String nomeSelecao);
    Optional<Jogador> findByNome(String nome);
}