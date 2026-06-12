package com.example.copa.repositories;
import com.example.copa.entities.Selecao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SelecaoRepository extends JpaRepository<Selecao, Long> {
    Optional<Selecao> findByNome(String nome);
}