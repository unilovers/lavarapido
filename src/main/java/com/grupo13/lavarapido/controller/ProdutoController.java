package com.grupo13.lavarapido.controller;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.grupo13.lavarapido.model.entities.Produto;
import com.grupo13.lavarapido.model.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    private ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping("/novo")
    public ResponseEntity<?> novoProduto(@RequestBody Produto produto) {
        try {
            produtoService.novoProduto(produto);
            return ResponseEntity.ok("Produto criado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(Response.SC_BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Produto>> getProdutos() {
        List<Produto> produtos = produtoService.getProdutos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/consulta")
    public ResponseEntity<List<Produto>> consultaProdutos(
        @RequestParam(name = "nome", required = false) String nome, 
        @RequestParam(name = "valor", required = false) BigDecimal valor) {
        List<Produto> produtosEncontrados = new ArrayList<>();
        
        if(nome != null && !nome.isEmpty()) {
            produtosEncontrados = produtoService.consultarProdutos(nome);
        } else if (valor != null) {
            produtosEncontrados = produtoService.consultarProdutosByValor(valor);
        }

        return ResponseEntity.ok(produtosEncontrados);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable("id") Long id) {
        try {
            produtoService.deletarProduto(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable("id") Long id, @RequestBody Produto produto) {
        try {
            produtoService.atualizarProduto(id, produto);
            return ResponseEntity.ok(true);
        }catch (Exception e) {
            if (e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } 
            if (e.getMessage().contains("obrigatório")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno: " + e.getMessage());
        }
    }
}
