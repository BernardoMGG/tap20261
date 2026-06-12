package com.example.copa.services.impl;

import com.example.copa.dtos.request.PartidaRequestDTO;
import com.example.copa.dtos.response.PartidaResponseDTO;
import com.example.copa.entities.Partida;
import com.example.copa.entities.Selecao;
import com.example.copa.repositories.PartidaRepository;
import com.example.copa.repositories.SelecaoRepository;
import com.example.copa.services.interfaces.PartidaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PartidaServiceImpl implements PartidaService {
    private final PartidaRepository partidaRepository;
    private final SelecaoRepository selecaoRepository;

    @Override
    @Transactional
    public PartidaResponseDTO criar(PartidaRequestDTO dto) {
        Partida partida = Partida.builder()
                .data(dto.getData())
                .estadio(dto.getEstadio())
                .fase(dto.getFase())
                .placar(dto.getPlacar())
                .build();
        return PartidaResponseDTO.fromEntity(partidaRepository.save(partida));
    }

    @Override
    @Transactional(readOnly = true)
    public List<PartidaResponseDTO> listarPorSelecao(String nomeSelecao) {
        return partidaRepository.findBySelecaoNome(nomeSelecao).stream()
                .map(PartidaResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PartidaResponseDTO associarSelecao(Long partidaId, String nomeSelecao) {
        Partida partida = partidaRepository.findByIdWithSelecoes(partidaId)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada"));

        // Aqui está o segredo: buscar a seleção pelo nome!
        Selecao selecao = selecaoRepository.findByNome(nomeSelecao)
                .orElseThrow(() -> new RuntimeException("Seleção não encontrada: " + nomeSelecao));

        partida.adicionarSelecao(selecao);
        return PartidaResponseDTO.fromEntity(partidaRepository.save(partida));
    }

    @Override
    @Transactional
    public PartidaResponseDTO atualizar(Long id, PartidaRequestDTO dto) {
        Partida partida = partidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Partida não encontrada"));

        // Atualiza os dados do jogo
        partida.setData(dto.getData());
        partida.setEstadio(dto.getEstadio());
        partida.setFase(dto.getFase());
        partida.setPlacar(dto.getPlacar());

        return PartidaResponseDTO.fromEntity(partidaRepository.save(partida));
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        partidaRepository.deleteById(id);
    }
}