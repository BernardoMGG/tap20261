package com.example.loja.controllers;

import com.example.loja.dtos.request.ProdutoRequestDTO;
import com.example.loja.dtos.response.ProdutoResponseDTO;
import com.example.loja.services.interfaces.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    // Injeta a INTERFACE — DIP em ação
    private final ProdutoService produtoService;

    // -------------------------------------------------------
    // CRUD básico
    // -------------------------------------------------------

    // POST /api/produtos
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criar(@RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO response = produtoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // GET /api/produtos
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    // GET /api/produtos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    // GET /api/produtos/categoria/{categoriaId}
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<ProdutoResponseDTO>> listarPorCategoria(
            @PathVariable Long categoriaId) {
        return ResponseEntity.ok(produtoService.listarPorCategoria(categoriaId));
    }

    // PUT /api/produtos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody ProdutoRequestDTO dto) {
        return ResponseEntity.ok(produtoService.atualizar(id, dto));
    }

    // DELETE /api/produtos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // -------------------------------------------------------
    // Endpoints de Associação N:M — Produto <-> Pedido
    // -------------------------------------------------------

    /**
     * Associa um Produto a um Pedido existente.
     * POST /api/produtos/{produtoId}/pedidos/{pedidoId}
     */
    @PostMapping("/{produtoId}/pedidos/{pedidoId}")
    public ResponseEntity<ProdutoResponseDTO> associarPedido(
            @PathVariable Long produtoId,
            @PathVariable Long pedidoId) {
        ProdutoResponseDTO response = produtoService.associarPedido(produtoId, pedidoId);
        return ResponseEntity.ok(response);
    }

    /**
     * Remove a associação entre um Produto e um Pedido.
     * DELETE /api/produtos/{produtoId}/pedidos/{pedidoId}
     */
    @DeleteMapping("/{produtoId}/pedidos/{pedidoId}")
    public ResponseEntity<ProdutoResponseDTO> desassociarPedido(
            @PathVariable Long produtoId,
            @PathVariable Long pedidoId) {
        ProdutoResponseDTO response = produtoService.desassociarPedido(produtoId, pedidoId);
        return ResponseEntity.ok(response);
    }
}
