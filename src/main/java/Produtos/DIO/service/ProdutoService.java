package Produtos.DIO.service;

import Produtos.DIO.dto.*;
import Produtos.DIO.exception.ProdutoNaoEncontradoException;
import Produtos.DIO.model.Produto;
import Produtos.DIO.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<Produto> listar(String type) {
        return (type == null) ? repository.findAll() : repository.findByType(type);
    }

    public Produto buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException(id));
    }

    public Produto criar(ProdutoRequest req) {
        Produto p = new Produto();
        p.setName(req.name());
        p.setType(req.type());
        p.setPrice(req.price());
        p.setDescription(req.description());
        return repository.save(p);
    }

    // PUT: substitui tudo
    public Produto substituir(Long id, ProdutoRequest req) {
        Produto p = buscar(id);
        p.setName(req.name());
        p.setType(req.type());
        p.setPrice(req.price());
        p.setDescription(req.description());
        return repository.save(p);
    }

    // PATCH: só altera o que veio no JSON
    public Produto atualizarParcial(Long id, ProdutoPatchRequest req) {
        Produto p = buscar(id);
        if (req.name() != null)        p.setName(req.name());
        if (req.type() != null)        p.setType(req.type());
        if (req.price() != null)       p.setPrice(req.price());
        if (req.description() != null) p.setDescription(req.description());
        return repository.save(p);
    }

    public void apagar(Long id) {
        Produto p = buscar(id);
        repository.delete(p);
    }
}
