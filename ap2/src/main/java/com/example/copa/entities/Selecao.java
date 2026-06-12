package com.example.copa.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "selecoes")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Selecao {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private String tecnico;

    @Column(nullable = false)
    private Integer rankingFifa;

    // Relacionamento 1:N com Jogador
    @OneToMany(mappedBy = "selecao", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Jogador> jogadores = new ArrayList<>();

    // Relacionamento N:M com Partida (Lado inverso)
    @ManyToMany(mappedBy = "selecoes")
    @Builder.Default
    private List<Partida> partidas = new ArrayList<>();

    @Override
    public String toString() {
        return "Selecao{id=" + id + ", nome='" + nome + "'}";
    }
}