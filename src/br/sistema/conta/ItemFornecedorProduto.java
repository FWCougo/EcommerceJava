package br.sistema.conta;

public class ItemFornecedorProduto {
    private Produto produto;
    private Fornecedor fornecedor;
    private double preco;
    private int quantidade;
    
    public ItemFornecedorProduto() {
    }
    
    public ItemFornecedorProduto(Produto produto, Fornecedor fornecedor, double preco, int quantidade) {
        this.produto = produto;
        this.fornecedor = fornecedor;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
