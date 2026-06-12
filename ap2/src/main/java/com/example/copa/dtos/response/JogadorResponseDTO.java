package com.example.copa.dtos.response;
import com.example.copa.entities.Jogador;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JogadorResponseDTO {
    private Long id;
    private String nome;
    private Integer numeroCamisa;
    private String posicao;
    private Integer idade;
    private SelecaoResponseDTO selecao;

    public static JogadorResponseDTO fromEntity(Jogador jogador) {
        return JogadorResponseDTO.builder()
                .id(jogador.getId())
                .nome(jogador.getNome())
                .numeroCamisa(jogador.getNumeroCamisa())
                .posicao(jogador.getPosicao())
                .idade(jogador.getIdade())
                .selecao(SelecaoResponseDTO.fromEntity(jogador.getSelecao()))
                .build();
    }
}