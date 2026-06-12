package com.example.loja.services.interfaces;

import com.example.loja.dtos.request.ProdutoRequestDTO;
import com.example.loja.dtos.response.ProdutoResponseDTO;

import java.util.List;

public interface ProdutoService {

    ProdutoResponseDTO criar(ProdutoRequestDTO dto);

    ProdutoResponseDTO buscarPorId(Long id);

    List<ProdutoResponseDTO> listarTodos();

    List<ProdutoResponseDTO> listarPorCategoria(Long categoriaId);

    ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto);

    void deletar(Long id);

    /**
     * Associa um Produto a um Pedido (relacionamento N:M).
     *
     * @param produtoId ID do produto
     * @param pedidoId  ID do pedido a ser vinculado
     * @return ProdutoResponseDTO atualizado com a lista de pedidos
     */
    ProdutoResponseDTO associarPedido(Long produtoId, Long pedidoId);

    /**
     * Remove a associação entre um Produto e um Pedido.
     *
     * @param produtoId ID do produto
     * @param pedidoId  ID do pedido a ser desvinculado
     * @return ProdutoResponseDTO atualizado
     */
    ProdutoResponseDTO desassociarPedido(Long produtoId, Long pedidoId);
}
