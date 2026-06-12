package com.example.copa.dtos.request;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class SelecaoRequestDTO {
    private String nome;
    private String tecnico;
    private Integer rankingFifa;
}