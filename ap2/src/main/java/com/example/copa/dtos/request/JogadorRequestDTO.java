package com.example.copa.dtos.request;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class JogadorRequestDTO {
    private String nome;
    private Integer numeroCamisa;
    private String posicao;
    private Integer idade;
    private String nomeSelecao;
}