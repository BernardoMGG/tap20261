package com.example.copa.services.impl;

import com.example.copa.dtos.request.SelecaoRequestDTO;
import com.example.copa.dtos.response.SelecaoResponseDTO;
import com.example.copa.entities.Selecao;
import com.example.copa.repositories.SelecaoRepository;
import com.example.copa.services.interfaces.SelecaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SelecaoServiceImpl implements SelecaoService {
    private final SelecaoRepository selecaoRepository;

    @Override
    @Transactional
    public SelecaoResponseDTO criar(SelecaoRequestDTO dto) {
        Selecao selecao = Selecao.builder()
                .nome(dto.getNome())
                .tecnico(dto.getTecnico())
                .rankingFifa(dto.getRankingFifa())
                .build();
        return SelecaoResponseDTO.fromEntity(selecaoRepository.save(selecao));
    }

    @Override
    @Transactional(readOnly = true)
    public List<SelecaoResponseDTO> listarTodas() {
        return selecaoRepository.findAll().stream()
                .map(SelecaoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SelecaoResponseDTO atualizar(String nome, SelecaoRequestDTO dto) {
        Selecao selecao = selecaoRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Seleção não encontrada com o nome: " + nome));

        selecao.setNome(dto.getNome());
        selecao.setTecnico(dto.getTecnico());
        selecao.setRankingFifa(dto.getRankingFifa());

        return SelecaoResponseDTO.fromEntity(selecaoRepository.save(selecao));
    }

    @Override
    @Transactional
    public void deletar(String nome) {
        Selecao selecao = selecaoRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Seleção não encontrada com o nome: " + nome));

        selecaoRepository.delete(selecao);
    }
}