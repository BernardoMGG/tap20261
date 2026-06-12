package com.example.copa.services.impl;

import com.example.copa.dtos.request.JogadorRequestDTO;
import com.example.copa.dtos.response.JogadorResponseDTO;
import com.example.copa.entities.Jogador;
import com.example.copa.entities.Selecao;
import com.example.copa.repositories.JogadorRepository;
import com.example.copa.repositories.SelecaoRepository;
import com.example.copa.services.interfaces.JogadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JogadorServiceImpl implements JogadorService {
    private final JogadorRepository jogadorRepository;
    private final SelecaoRepository selecaoRepository;

    @Override
    @Transactional
    public JogadorResponseDTO criar(JogadorRequestDTO dto) {
        Selecao selecao = selecaoRepository.findByNome(dto.getNomeSelecao())
                .orElseThrow(() -> new RuntimeException("Seleção não encontrada com o nome: " + dto.getNomeSelecao()));

        Jogador jogador = Jogador.builder()
                .nome(dto.getNome())
                .numeroCamisa(dto.getNumeroCamisa())
                .posicao(dto.getPosicao())
                .idade(dto.getIdade())
                .selecao(selecao)
                .build();
        return JogadorResponseDTO.fromEntity(jogadorRepository.save(jogador));
    }

    @Override
    @Transactional(readOnly = true)
    public List<JogadorResponseDTO> listarPorSelecao(String nomeSelecao) {
        return jogadorRepository.findBySelecaoNome(nomeSelecao).stream()
                .map(JogadorResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public JogadorResponseDTO atualizar(String nome, JogadorRequestDTO dto) {
        Jogador jogador = jogadorRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado com o nome: " + nome));

        jogador.setNome(dto.getNome());
        jogador.setNumeroCamisa(dto.getNumeroCamisa());
        jogador.setPosicao(dto.getPosicao());
        jogador.setIdade(dto.getIdade());

        if (dto.getNomeSelecao() != null) {
            Selecao novaSelecao = selecaoRepository.findByNome(dto.getNomeSelecao())
                    .orElseThrow(() -> new RuntimeException("Seleção não encontrada: " + dto.getNomeSelecao()));
            jogador.setSelecao(novaSelecao);
        }

        return JogadorResponseDTO.fromEntity(jogadorRepository.save(jogador));
    }

    @Override
    @Transactional
    public void deletar(String nome) {
        Jogador jogador = jogadorRepository.findByNome(nome)
                .orElseThrow(() -> new RuntimeException("Jogador não encontrado com o nome: " + nome));

        jogadorRepository.delete(jogador);
    }
}