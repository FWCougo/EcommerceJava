package br.sistema.conta;

import com.fasterxml.jackson.annotation.*;

//import com.fasterxml.jackson.annotation.JsonTypeInfo;
//import com.fasterxml.jackson.annotation.JsonSubTypes;

/*@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Cliente.class, name = "cliente"),
    @JsonSubTypes.Type(value = Fornecedor.class, name = "fornecedor")
})*/
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public abstract class Usuario {
	private int fornecedor;
	private String nome;
	private String email;
	private String telefone;
    private String login;
    private String senha;
    private String CEP;
    private String endereco;
//    private Carrinho carrinho;
//    private HistoricoCompras historicoCompras;

    //Constructor
    public Usuario()
    {
    	//this.historicoCompras = new HistoricoCompras();
    	//this.carrinho = new Carrinho(this); 
    }
    public Usuario(String nome)
    {
    	this();
    	this.nome = nome;
    }    
    public Usuario(String login, String senha, String endereco) {
    	this();
        this.login = login;
        this.senha = senha;
        this.setEndereco(endereco);
    }
    
    
    //Getter && Setter    
    
    /*public Carrinho getCarrinho() {
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
	} */   
    
	public String getCEP() {
		return CEP;
	}
	public void setCEP(String cEP) {
		CEP = cEP;
	}
    
    public int getFornecedor() {
		return fornecedor;
	}    

	public void setFornecedor(int fornecedor) {
		this.fornecedor = fornecedor;
	}
    
    public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario u) {
            return this.login.equals(u.getLogin());
        }
        return false;
    }
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
}
