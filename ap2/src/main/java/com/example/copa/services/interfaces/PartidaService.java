package com.example.copa.services.interfaces;
import com.example.copa.dtos.request.PartidaRequestDTO;
import com.example.copa.dtos.response.PartidaResponseDTO;
import java.util.List;

public interface PartidaService {
    PartidaResponseDTO criar(PartidaRequestDTO dto);
    List<PartidaResponseDTO> listarPorSelecao(String nomeSelecao);
    PartidaResponseDTO associarSelecao(Long partidaId, String nomeSelecao);
    PartidaResponseDTO atualizar(Long id, PartidaRequestDTO dto);
    void deletar(Long id);
}