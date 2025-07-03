package br.sistema.conta;

import br.sistema.BDD.*;
import java.util.ArrayList;
import java.util.List;

public class Loja {
    private List<Fornecedor> fornecedores = new ArrayList<>();
    private List<Produto> produtos = new ArrayList<>();
    private List<ItemFornecedorProduto> estoque = new ArrayList<>();
    private BancoDeDados bdd;
    
    public Loja(BancoDeDados bdd) {
    	this.bdd = bdd;
    	carregarFornecedores();
    	carregarProdutos();
    	carregarItems();
    }
    
    //Fornecedores
    public void mostraFornecedoresLoja() {
    	for(Fornecedor f : fornecedores) {
    		System.out.println(f.getNome() + " | " + f.getClass());
    	}
    }    
    public void carregarFornecedores()
    {
    	fornecedores.clear();
    	
    	for(Usuario u: bdd.getContas()) {
    		if(u.getFornecedor() == 1) 
    		{ 
    			fornecedores.add((Fornecedor) u);
    		}
    	}
    }    
    public void adicionarFornecedor(Fornecedor f) {
        if (!fornecedores.contains(f)) {
        		f.setFornecedor(1);
        		bdd.AdicionarUsuario(f);
        		carregarFornecedores();
                //fornecedores.add(f);                	  	       
        }
    }
    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }
    
    //Produtos
    public void adicionarProduto(Produto p) {
        if (!produtos.contains(p)) {
        	bdd.adicionarProduto(p);
        	carregarProdutos();
            //produtos.add(p);
        }
    }
    public void carregarProdutos() 
    {
    	produtos.clear();     	
    	for(Produto p: bdd.getProdutos()) {
    		produtos.add(p);    		
    	}
    }  
    public List<Produto> getProdutos() {
        return produtos;
    }
    
    //ItemFornecedorProduto
    public void adicionarItem(ItemFornecedorProduto item) {
    	if(!estoque.contains(item)) 
    	{
    		bdd.adicionarItem(item);
    		carregarItems();
    		//estoque.add(item);
    	}
    }
    public void carregarItems() {
    	estoque.clear();
    	for(ItemFornecedorProduto item : bdd.getEstoque()) {
    		estoque.add(item);    	
    	}

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
