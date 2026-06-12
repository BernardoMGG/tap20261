package com.example.copa.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "partidas")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Partida {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate data;

    @Column(nullable = false)
    private String estadio;

    @Column(nullable = false)
    private String fase;

    @Column
    private String placar;

    // Relacionamento N:M com Selecao (Lado Dono)
    @ManyToMany
    @JoinTable(
            name = "partida_selecao",
            joinColumns = @JoinColumn(name = "partida_id"),
            inverseJoinColumns = @JoinColumn(name = "selecao_id")
    )
    @Builder.Default
    private List<Selecao> selecoes = new ArrayList<>();

    // Métodos para Clean Code e consistência bidirecional
    public void adicionarSelecao(Selecao selecao) {
        this.selecoes.add(selecao);
        selecao.getPartidas().add(this);
    }

    public void removerSelecao(Selecao selecao) {
        this.selecoes.remove(selecao);
        selecao.getPartidas().remove(this);
    }

    @Override
    public String toString() {
        return "Partida{id=" + id + ", fase='" + fase + "'}";
    }
}