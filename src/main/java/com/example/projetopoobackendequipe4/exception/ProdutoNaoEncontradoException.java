
public Produto atualizarProduto(Long Id, Produto p) throws ProdutoNaoEncontradoException {
        Optional<Produto> opProduto = produtoRepository.findById(Id);

        if(opProduto.isEmpty()) {
            throw new ProdutoNaoEncontradoException("Produto Não Encontrado");

        }

        Produto produto = opProduto.get();
        produto.setNomeProduto(p.getNomeProduto());
        produto.setDescricao(p.getDescricao());
        produto.setCategoria(p.getCategoria());
        produto.setEstoque(p.getEstoque());
        produto.setFotoProduto(p.getFotoProduto());

        return produtoRepository.save(produto);
    }
