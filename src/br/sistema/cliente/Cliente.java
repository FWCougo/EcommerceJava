package br.sistema.cliente;

import br.sistema.conta.*;

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
	
	public Cliente(String nome, String senha) {	
		super(nome, senha);		
		this.historicoCompras = new HistoricoCompras();
    	this.carrinho = new Carrinho(this);
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
