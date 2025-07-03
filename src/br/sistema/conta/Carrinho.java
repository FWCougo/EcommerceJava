package br.sistema.conta;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Carrinho {
    private List<ItemCarrinho> itens = new ArrayList<>();
    //private Usuario usuario;
    private Cliente cliente;
    
    
    public Carrinho() {
    	this.itens = new ArrayList<ItemCarrinho>();
    }
    
    public Carrinho(/*Usuario usuario*/ Cliente cliente) {
    	super();
    	//this.usuario=usuario;
    	this.cliente=cliente;
    }
    
    public List<ItemCarrinho> getItens() {
        return itens;
    }

    

    /*public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
*/
	public void adicionarProduto(ItemFornecedorProduto item, int quantidadeDesejada) {
        if (quantidadeDesejada <= item.getQuantidade()) {
            itens.add(new ItemCarrinho(item, quantidadeDesejada));
            System.out.println("Produto adicionado ao carrinho!");
        } else {
            System.out.println("Quantidade indisponível em estoque.");
        }
    }

    public void exibirCarrinho() {
        if (itens.isEmpty()) {
            System.out.println("Carrinho vazio.");
            return;
        }

        System.out.println("--- Seu Carrinho ---");
        for (ItemCarrinho ic : itens) {
            System.out.println("Produto: " + ic.getItem().getProduto().getNome() +
                    " | Preço Unitário: R$" + ic.getItem().getPreco() +
                    " | Quantidade: " + ic.getQuantidade() +
                    " | Total: R$" + (ic.getQuantidade() * ic.getItem().getPreco()));
        }
    }

    public double calcularTotal() {
        double total = 0;
        for (ItemCarrinho ic : itens) {
            total += ic.getQuantidade() * ic.getItem().getPreco();
        }
        return total;
    }

    public void finalizarCompra(Loja loja) {
        if (itens.isEmpty()) {
            System.out.println("Carrinho vazio. Nada para comprar.");
            return;
        }

        for (ItemCarrinho ic : itens) {
            ItemFornecedorProduto estoqueItem = loja.buscarPorCodigo(ic.getItem().getProduto().getCodigo());
            if (estoqueItem != null) {
                int novaQuantidade = estoqueItem.getQuantidade() - ic.getQuantidade();
                estoqueItem.setQuantidade(novaQuantidade);
            }
        }

        //this.usuario.getHistoricoCompras().registrarCompra(new Compra());
        this.cliente.getHistoricoCompras().registrarCompra(new Compra());
        System.out.println("Compra finalizada com sucesso! Total: R$" + calcularTotal());
        itens.clear();
    }

    public boolean isEmpty() {
        return itens.isEmpty();
    }
}
