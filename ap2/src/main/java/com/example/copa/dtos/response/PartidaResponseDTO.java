package com.example.copa.dtos.response;
import com.example.copa.entities.Partida;
import lombok.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class PartidaResponseDTO {
    private Long id;
    private LocalDate data;
    private String estadio;
    private String fase;
    private String placar;
    private List<SelecaoResponseDTO> selecoes;

    public static PartidaResponseDTO fromEntity(Partida partida) {
        return PartidaResponseDTO.builder()
                .id(partida.getId())
                .data(partida.getData())
                .estadio(partida.getEstadio())
                .fase(partida.getFase())
                .placar(partida.getPlacar())
                .selecoes(partida.getSelecoes().stream()
                        .map(SelecaoResponseDTO::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}