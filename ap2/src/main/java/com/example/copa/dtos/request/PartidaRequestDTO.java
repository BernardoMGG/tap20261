package com.example.copa.dtos.request;
import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PartidaRequestDTO {
    private LocalDate data;
    private String estadio;
    private String fase;
    private String placar;
}