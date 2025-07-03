package br.sistema.conta;

import java.util.ArrayList;
import java.util.List;

public class Loja {
    private List<Fornecedor> fornecedores = new ArrayList<>();
    private List<Produto> produtos = new ArrayList<>();
    private List<ItemFornecedorProduto> estoque = new ArrayList<>();

    public void adicionarFornecedor(Fornecedor f) {
        if (!fornecedores.contains(f)) {
            fornecedores.add(f);
        }
    }
<<<<<<< Updated upstream

=======
    
    public void removerFornecedor(Fornecedor f) {
    	if (fornecedores.contains(f)) {
    		fornecedores.remove(f);
    		bdd.RemoveUsuario(f.getNome());
    	}
    }
    
>>>>>>> Stashed changes
    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void adicionarProduto(Produto p) {
        if (!produtos.contains(p)) {
            produtos.add(p);
        }
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void adicionarItem(ItemFornecedorProduto item) {
        estoque.add(item);
    }

    public List<ItemFornecedorProduto> listarItens() {
        return estoque;
    }

    public List<ItemFornecedorProduto> buscarPorNome(String nome) {
        List<ItemFornecedorProduto> encontrados = new ArrayList<>();
        for (ItemFornecedorProduto item : estoque) {
            if (item.getProduto().getNome().equalsIgnoreCase(nome)) {
                encontrados.add(item);
            }
        }
        return encontrados;
    }

    public List<ItemFornecedorProduto> buscarPorFornecedor(Fornecedor f) {
        List<ItemFornecedorProduto> encontrados = new ArrayList<>();
        for (ItemFornecedorProduto item : estoque) {
            if (item.getFornecedor().equals(f)) {
                encontrados.add(item);
            }
        }
        return encontrados;
    }

    public ItemFornecedorProduto buscarPorCodigo(int codigo) {
        for (ItemFornecedorProduto item : estoque) {
            if (item.getProduto().getCodigo() == codigo) {
                return item;
            }
        }
        return null;
    }

    public boolean removerItem(int codigo) {
        ItemFornecedorProduto item = buscarPorCodigo(codigo);
        if (item != null) {
            estoque.remove(item);
            return true;
        }
        return false;
    }
}