package br.sistema.BDD;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.sistema.conta.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.annotation.*;
public class BancoDeDados {

	private ObjectMapper mapper;
    private final String filePath = "usuarios.json"; // Caminho do arquivo JSON
    private final String filePathProdutos = "produtos.json"; // Caminho do arquivo JSON
    private final String filePathEstoque = "estoque.json"; // Caminho do arquivo JSON
	private List<Usuario> Contas;
	
    private List<Produto> produtos = new ArrayList<>();
    private List<ItemFornecedorProduto> estoque = new ArrayList<>();
	
    //Constructor
    public BancoDeDados()
    {
    	this.mapper = new ObjectMapper();    	
    	this.mapper.registerModule(new JavaTimeModule());   	       	   
    	this.mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    	
    	
        this.mapper.enableDefaultTyping(
                ObjectMapper.DefaultTyping.NON_FINAL,
                JsonTypeInfo.As.PROPERTY
            );	
    	
        CarregarUsuarios();
        CarregarProdutos();
        CarregarEstoque();
    }	    

    //Getter & Setter
    public List<Usuario> getContas() {
		return Contas;
	}
	public void setContas(List<Usuario> contas) {
		Contas = contas;
	} 
    public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public List<ItemFornecedorProduto> getEstoque() {
		return estoque;
	}
	public void setEstoque(List<ItemFornecedorProduto> estoque) {
		this.estoque = estoque;
	}

	//Methods Usuario
	public boolean AdicionarUsuario(Usuario usuario) 
	{
		if(ChecaUsuario(usuario)) {
			Contas.add(usuario);
			SalvarUsuarios();
			return true;
		}
		
		return false;
	}	
    public void VerUsuarios() throws Exception {
        String json = this.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(Contas);
        System.out.println("JSON serializado:");
        System.out.println(json);
    }        
	public void SalvarUsuarios(Usuario usuario) {
    	Contas.add(usuario);
    	try {        	
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), Contas);
            System.out.println("Usuários salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }    
    public void SalvarUsuarios(List<Usuario> contas) {
        try {        	
        	Contas = contas;
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), Contas);
            System.out.println("Usuários salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }
    public void SalvarUsuarios() {
        try {        	
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), Contas);
            //System.out.println("Usuários salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }
        
    }      
    public boolean ChecaUsuario(Usuario u)
    {    	
    	if(!Contas.contains(u)) {
    		return true;
    	}
    	return false;
    }     
    public void RemoveUsuario(String nome) {

        for (Usuario u : getContas()) {
            if (u.getNome().equals(nome)) {         	            	            	
                Contas.remove(u);
                System.out.println("O usuário " + nome + " foi removido.");
                SalvarUsuarios();
                return;
            }
        }

        System.out.println("O usuário " + nome + " não foi encontrado.");
    }    
    public List<Usuario> CarregarUsuarios() {
        try {
            File file = new File(filePath);
            if (file.exists()) {
                Contas = mapper.readValue(file, new TypeReference<List<Usuario>>() {});
                System.out.println("Usuários carregados com sucesso.");
            } else {
                System.out.println("Arquivo de usuários não encontrado. Criando novo cadastro.");
                Contas = new ArrayList<Usuario>();
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar usuários: " + e.getMessage());
            Contas = new ArrayList<Usuario>();
        }
        
        return Contas;
    }
    
    //Methods Produtos
    public boolean checaProduto(Produto p) {
    	if (!produtos.contains(p)) {
            return true;
        }
        return false;
    }    
    public void adicionarProduto(Produto p) {
    	if(checaProduto(p)) 
    	{
            produtos.add(p);
            SalvarProdutos();
            return;
    	}    	
    }
    public void SalvarProdutos() {
    	try {        	
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePathProdutos), produtos);
            System.out.println("Produtos salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar Produtos: " + e.getMessage());
        }
    }   
    public List<Produto> CarregarProdutos() {
        try {
            File file = new File(filePathProdutos);
            if (file.exists()) {
                produtos = mapper.readValue(file, new TypeReference<List<Produto>>() {});
                System.out.println("Produtos carregados com sucesso.");
            } else {
                System.out.println("Arquivo de usuários não encontrado. Criando novo cadastro.");
                produtos = new ArrayList<Produto>();
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar Produtos: " + e.getMessage());
            produtos = new ArrayList<Produto>();
        }
        
        return produtos;
    }
	
    //Methods Items
    public boolean checaItem(ItemFornecedorProduto item) 
    {
    	if (!estoque.contains(item)) {
            return true;
        }
    	
    	return false;
    }
    public void adicionarItem(ItemFornecedorProduto item) {
    	if(checaItem(item)) 
    	{
            estoque.add(item);
            SalvarEstoque();
            return;
    	}    	
    }
    public void SalvarEstoque() {
    	try {        	
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePathEstoque), estoque);
            System.out.println("Produtos salvos com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar Produtos: " + e.getMessage());
        }
    }   
    public List<ItemFornecedorProduto> CarregarEstoque() {
        try {
            File file = new File(filePathEstoque);
            if (file.exists()) {
                estoque = mapper.readValue(file, new TypeReference<List<ItemFornecedorProduto>>() {});
                System.out.println("Estoque carregados com sucesso.");
            } else {
                System.out.println("Arquivo de estoque não encontrado. Criando novo cadastro.");
                estoque = new ArrayList<ItemFornecedorProduto>();
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar Estoque: " + e.getMessage());
            estoque = new ArrayList<ItemFornecedorProduto>();
        }
        
        return estoque;
    }
    
    
}
