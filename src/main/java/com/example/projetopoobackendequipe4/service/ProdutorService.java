// ProdutorService.java
package com.example.projetopoobackendequipe4.service;

import com.example.projetopoobackendequipe4.exception.ProdutorNaoEncontradoException;
import com.example.projetopoobackendequipe4.model.Produtor;
import com.example.projetopoobackendequipe4.repository.ProdutorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutorService {

    @Autowired
    private final ProdutorRepository produtorRepository;
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    public ProdutorService(ProdutorRepository produtorRepository) {
        this.produtorRepository = produtorRepository;
    }

    public List<Produtor> listarTodosProdutores() {
        return produtorRepository.findAll();
    }

    public Produtor buscarProdutorPeloId(Long id) {
        Optional<Produtor> produtorOptional = produtorRepository.findById(id);
        if (produtorOptional.isEmpty()) {
            throw new ProdutorNaoEncontradoException("Produtor não encontrado com o id: " + id);
        }

        return produtorOptional.get();
    }

    public Produtor buscarProdutorPeloDocumento(String documento) {
        Optional<Produtor> produtorOptional = produtorRepository.findProdutorByDocumento(documento);
        if (produtorOptional.isEmpty()) {
            throw new ProdutorNaoEncontradoException("Produtor não encontrado com o documento: " + documento);
        }
       return produtorOptional.get();
    }

    public Produtor criarProdutor(Produtor produtor) {
        return (Produtor) usuarioService.criarUsuario(produtor);
    }

    public Produtor atualizarProdutor(Long id, Produtor produtor) throws ProdutorNaoEncontradoException {
        Optional<Produtor> produtorOptionalExistente = produtorRepository.findById(id);
        if (produtorOptionalExistente.isEmpty()) {
            throw new ProdutorNaoEncontradoException("Produtor não encontrado com o ID: " + id);
        }

        Produtor produtorExistente = produtorOptionalExistente.get();
        BeanUtils.copyProperties(produtor, produtorExistente, "id");
        return produtorRepository.save(produtorExistente);
    }

    public void excluirProdutor(Long id) throws ProdutorNaoEncontradoException {
        if (!produtorRepository.existsById(id)) {
            throw new ProdutorNaoEncontradoException("Produtor não encontrado com o ID: " + id);
        }
        produtorRepository.deleteById(id);
    }
}
