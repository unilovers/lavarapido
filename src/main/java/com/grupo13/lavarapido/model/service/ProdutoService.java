package com.grupo13.lavarapido.model.service;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo13.lavarapido.model.entities.Produto;
import com.grupo13.lavarapido.model.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    public boolean novoProduto(Produto produto) throws Exception {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new Exception("Nome do produto é obrigatório.");
        }

        if (produto.getValor().compareTo(BigDecimal.ZERO) == -1) {
            throw new Exception("Valor do produto deve ser maior que zero.");
        }

        produtoRepository.save(produto);
        return true;
    }

    public List<Produto> getProdutos() {
        return (List<Produto>) produtoRepository.findAll();
    }

    public List<Produto> consultarProdutos(String nome) {
        Iterable<Produto> produtosEncontrados = produtoRepository.findByNome(nome);
        return (List<Produto>) produtosEncontrados;
    }

    public List<Produto> consultarProdutosByValor(BigDecimal valor) {
        BigDecimal valorPadronizado = valor.setScale(2, RoundingMode.HALF_UP);
        Iterable<Produto> produtosEncontrados = produtoRepository.findByValor(valorPadronizado);
        return (List<Produto>) produtosEncontrados;
    }

    public boolean deletarProduto(Long id) throws Exception {
        if (id == null) {
            throw new Exception("ID inválido.");
        }

        try {
            produtoRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception("Erro ao deletar produto: " + e.getMessage());
        }
    }

    public boolean atualizarProduto(Long id, Produto detalhesProduto) throws Exception {

        Optional<Produto> produtoExistente = produtoRepository.findById(id);

        if(produtoExistente.isEmpty()) {
            throw new Exception("Produto com ID " + id + " não encontrado.");
        }

        if (detalhesProduto.getNome() == null || detalhesProduto.getNome().isEmpty()) {
            throw new Exception("Nome do produto é obrigatório.");
        }

        if (detalhesProduto.getValor().compareTo(BigDecimal.ZERO) == -1) {
            throw new Exception("Valor do produto deve ser maior que zero.");
        }

        Produto produtoParaAtualizar = produtoExistente.get();
        produtoParaAtualizar.setNome(detalhesProduto.getNome());
        produtoParaAtualizar.setDescricao(detalhesProduto.getDescricao());
        produtoParaAtualizar.setValor(detalhesProduto.getValor());

        produtoRepository.save(produtoParaAtualizar);
        return true;
    }

    public List<Produto> consultarProdutosPorValor(BigDecimal valor) {
        Iterable<Produto> produtosEncontrados = produtoRepository.findByValor(valor);
        return (List<Produto>) produtosEncontrados;
    }
}
