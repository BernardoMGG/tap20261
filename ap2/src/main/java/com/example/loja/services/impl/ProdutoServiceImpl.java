package com.example.loja.services.impl;

import com.example.loja.dtos.request.ProdutoRequestDTO;
import com.example.loja.dtos.response.ProdutoResponseDTO;
import com.example.loja.entities.Categoria;
import com.example.loja.entities.Pedido;
import com.example.loja.entities.Produto;
import com.example.loja.repositories.CategoriaRepository;
import com.example.loja.repositories.PedidoRepository;
import com.example.loja.repositories.ProdutoRepository;
import com.example.loja.services.interfaces.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {
        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException(
                        "Categoria não encontrada com id: " + dto.getCategoriaId()));

        // Builder Pattern para instanciar a entidade
        Produto produto = Produto.builder()
                .nome(dto.getNome())
                .descricao(dto.getDescricao())
                .preco(dto.getPreco())
                .estoque(dto.getEstoque())
                .categoria(categoria)
                .build();

        Produto salvo = produtoRepository.save(produto);
        return ProdutoResponseDTO.fromEntity(salvo);
    }

    @Override
    @Transactional(readOnly = true)
    public ProdutoResponseDTO buscarPorId(Long id) {
        // Usa query especializada para carregar pedidos e evitar LazyInitializationException
        Produto produto = produtoRepository.findByIdWithPedidos(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));
        return ProdutoResponseDTO.fromEntity(produto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> listarTodos() {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> listarPorCategoria(Long categoriaId) {
        return produtoRepository.findByCategoriaId(categoriaId)
                .stream()
                .map(ProdutoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produto = produtoRepository.findByIdWithPedidos(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + id));

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException(
                        "Categoria não encontrada com id: " + dto.getCategoriaId()));

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setEstoque(dto.getEstoque());
        produto.setCategoria(categoria);

        Produto atualizado = produtoRepository.save(produto);
        return ProdutoResponseDTO.fromEntity(atualizado);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado com id: " + id);
        }
        produtoRepository.deleteById(id);
    }

    // -------------------------------------------------------
    // Operações N:M — Associação Produto <-> Pedido
    // -------------------------------------------------------

    @Override
    @Transactional
    public ProdutoResponseDTO associarPedido(Long produtoId, Long pedidoId) {
        Produto produto = produtoRepository.findByIdWithPedidos(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + produtoId));

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com id: " + pedidoId));

        // Verifica se a associação já existe para evitar duplicatas
        boolean jaAssociado = produto.getPedidos().stream()
                .anyMatch(p -> p.getId().equals(pedidoId));

        if (jaAssociado) {
            throw new RuntimeException(
                    "Produto " + produtoId + " já está associado ao Pedido " + pedidoId);
        }

        // Usa o método auxiliar que mantém a consistência bidirecional
        produto.adicionarPedido(pedido);

        Produto salvo = produtoRepository.save(produto);
        return ProdutoResponseDTO.fromEntity(salvo);
    }

    @Override
    @Transactional
    public ProdutoResponseDTO desassociarPedido(Long produtoId, Long pedidoId) {
        Produto produto = produtoRepository.findByIdWithPedidos(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com id: " + produtoId));

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com id: " + pedidoId));

        produto.removerPedido(pedido);

        Produto salvo = produtoRepository.save(produto);
        return ProdutoResponseDTO.fromEntity(salvo);
    }
}
