package br.sistema.conta;

import com.fasterxml.jackson.annotation.*;

@JsonTypeName("cliente")
public class Cliente extends Usuario{
	
	private String cartaoDeCredito;
	
    private Carrinho carrinho;
    private HistoricoCompras historicoCompras;
	
	public Cliente(){
		this.historicoCompras = new HistoricoCompras();
    	this.carrinho = new Carrinho(this); 
	}
	
	public Cliente(String nome){
		super(nome);	
		this.historicoCompras = new HistoricoCompras();
    	this.carrinho = new Carrinho(this);
	}
	
	public Cliente(String nome, String senha, String endereco) {	
		super(nome, senha, endereco);		
		this.historicoCompras = new HistoricoCompras();
    	this.carrinho = new Carrinho(this);
	}
	
	
	
	
	public String getCartaoDeCredito() {
		return cartaoDeCredito;
	}

	public void setCartaoDeCredito(String cartaoDeCredito) {
		this.cartaoDeCredito = cartaoDeCredito;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}
	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	public HistoricoCompras getHistoricoCompras() {
		return historicoCompras;
	}
	public void setHistoricoCompras(HistoricoCompras historicoCompras) {
		this.historicoCompras = historicoCompras;
	}    

}
