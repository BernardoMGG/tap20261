package com.example.loja.controllers;

import com.example.loja.dtos.request.CategoriaRequestDTO;
import com.example.loja.dtos.response.CategoriaResponseDTO;
import com.example.loja.services.interfaces.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    // Injeta a INTERFACE — Dependency Inversion Principle (DIP)
    private final CategoriaService categoriaService;

    // POST /api/categorias
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criar(@RequestBody CategoriaRequestDTO dto) {
        CategoriaResponseDTO response = categoriaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET /api/categorias
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarTodas() {
        return ResponseEntity.ok(categoriaService.listarTodas());
    }

    // GET /api/categorias/{id}
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.buscarPorId(id));
    }

    // PUT /api/categorias/{id}
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody CategoriaRequestDTO dto) {
        return ResponseEntity.ok(categoriaService.atualizar(id, dto));
    }

    // DELETE /api/categorias/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        categoriaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
