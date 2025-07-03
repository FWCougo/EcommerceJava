package br.sistema.conta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemCarrinho {
    private ItemFornecedorProduto item;
    private int quantidade;

    public ItemCarrinho() {
    }    
    
    
    public ItemCarrinho(ItemFornecedorProduto item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    public ItemFornecedorProduto getItem() {
        return item;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
