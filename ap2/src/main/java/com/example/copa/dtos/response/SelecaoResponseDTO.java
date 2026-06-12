package com.example.copa.dtos.response;
import com.example.copa.entities.Selecao;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SelecaoResponseDTO {
    private Long id;
    private String nome;
    private String tecnico;
    private Integer rankingFifa;

    public static SelecaoResponseDTO fromEntity(Selecao selecao) {
        return SelecaoResponseDTO.builder()
                .id(selecao.getId())
                .nome(selecao.getNome())
                .tecnico(selecao.getTecnico())
                .rankingFifa(selecao.getRankingFifa())
                .build();
    }
}