package Produtos.DIO.controller;


import Produtos.DIO.dto.*;
import Produtos.DIO.model.Produto;
import Produtos.DIO.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")

public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Produto> criar(@Valid @RequestBody ProdutoRequest req) {
        Produto criado = service.criar(req);
        return ResponseEntity.created(URI.create("/api/produtos/" + criado.getId())).body(criado);
    }

    @GetMapping
    public List<Produto> listar(@RequestParam(required = false) String type) {
        return service.listar(type);
    }

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    public Produto substituir(@PathVariable Long id, @Valid @RequestBody ProdutoRequest req) {
        return service.substituir(id, req);
    }

    @PatchMapping("/{id}")
    public Produto atualizarParcial(@PathVariable Long id, @Valid @RequestBody ProdutoPatchRequest req) {
        return service.atualizarParcial(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable Long id) {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }
}
