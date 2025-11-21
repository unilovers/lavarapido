package com.grupo13.lavarapido.controller;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.grupo13.lavarapido.model.entities.Procedimento;
import com.grupo13.lavarapido.model.service.ProcedimentoService;

@RestController
@RequestMapping("/procedimentos")
public class ProcedimentoController {

    private final ProcedimentoService procedimentoService;

    @Autowired
    public ProcedimentoController(ProcedimentoService procedimentoService) {
        this.procedimentoService = procedimentoService;
    }

    @PostMapping("/novo")
    public ResponseEntity<?> novoProcedimento(@RequestBody Procedimento procedimento) {
        try {
            procedimentoService.novoProcedimento(procedimento);
            return ResponseEntity.ok("Procedimento criado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Procedimento>> getProcedimentos() {
        return ResponseEntity.ok(procedimentoService.getProcedimentos());
    }

    @GetMapping("/consulta")
    public ResponseEntity<List<Procedimento>> consultarProcedimentos(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "valor", required = false) BigDecimal valor,
            @RequestParam(name = "tipoVeiculo", required = false) String tipoVeiculo
    ) {
        List<Procedimento> encontrados = new ArrayList<>();

        if (nome != null && !nome.isBlank()) {
            encontrados = procedimentoService.consultarProcedimentos(nome);

        } else if (valor != null) {
            encontrados = procedimentoService.consultarProcedimentosByValor(valor);

        } else if (tipoVeiculo != null && !tipoVeiculo.isBlank()) {
            encontrados = procedimentoService.consultarProcedimentosPorTipoVeiculo(tipoVeiculo);
        }

        return ResponseEntity.ok(encontrados);
    }

    @DeleteMapping("/apagar/{id}")
    public ResponseEntity<?> deletarProcedimento(@PathVariable("id") Long id) {
        try {
            procedimentoService.deletarProcedimento(id);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarProcedimento(
            @PathVariable("id") Long id,
            @RequestBody Procedimento procedimento
    ) {
        try {
            procedimentoService.atualizarProcedimento(id, procedimento);
            return ResponseEntity.ok(true);

        } catch (Exception e) {

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