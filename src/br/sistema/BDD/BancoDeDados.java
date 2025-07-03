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
	private List<Usuario> Contas;
	
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
    }	    

    //Getter & Setter
    public List<Usuario> getContas() {
		return Contas;
	}
	public void setContas(List<Usuario> contas) {
		Contas = contas;
	}	
    
    //Methods
	public boolean AdicionarUsuario(Usuario usuario) 
	{
		if(!Contas.contains(usuario)) {
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
	
}
