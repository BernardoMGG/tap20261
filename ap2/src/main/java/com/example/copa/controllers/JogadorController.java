package com.example.copa.controllers;

import com.example.copa.dtos.request.JogadorRequestDTO;
import com.example.copa.dtos.response.JogadorResponseDTO;
import com.example.copa.services.interfaces.JogadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jogadores")
@RequiredArgsConstructor
public class JogadorController {
    private final JogadorService jogadorService;

    @PostMapping
    public ResponseEntity<JogadorResponseDTO> criar(@RequestBody JogadorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(jogadorService.criar(dto));
    }

    @GetMapping("/selecao/{nomeSelecao}")
    public ResponseEntity<List<JogadorResponseDTO>> listarPorSelecao(@PathVariable String nomeSelecao) {
        return ResponseEntity.ok(jogadorService.listarPorSelecao(nomeSelecao));
    }

    @PutMapping("/{nome}")
    public ResponseEntity<JogadorResponseDTO> atualizar(@PathVariable String nome, @RequestBody JogadorRequestDTO dto) {
        return ResponseEntity.ok(jogadorService.atualizar(nome, dto));
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<Void> deletar(@PathVariable String nome) {
        jogadorService.deletar(nome);
        return ResponseEntity.noContent().build();
    }
}