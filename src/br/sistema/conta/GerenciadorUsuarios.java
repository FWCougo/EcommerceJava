package br.sistema.conta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.sistema.BDD.*;
import br.sistema.cliente.*;



public class GerenciadorUsuarios {	
	
    private BancoDeDados bdd;
    
    public GerenciadorUsuarios(BancoDeDados bdd) {
    	this.bdd = bdd;
    } 
    
    public boolean cadastrarUsuario(/*String login, String senha, */Scanner sc) {
    	
    	 System.out.println("1 - Fornecedor");
         System.out.println("2 - Cliente");
         System.out.println("0 - voltar");
         System.out.print("Opção: ");
    	 
         String tipo = sc.nextLine();
         
         switch(tipo) {
    		case "1" -> {
    			System.out.println("Nome do Fornecedor: "); 
    			String nome = sc.nextLine();
    			System.out.println("Senha do Fornecedor: "); 
    			String senha = sc.nextLine();
    			
    			Fornecedor f = new Fornecedor(nome, senha);
    			
    			if (!bdd.ChecaUsuario(f)) {    				
    	            return false;
    	        }
    			
    			System.out.println("Email do Fornecedor: "); 
    			f.setEmail(sc.nextLine());
    			System.out.println("Telefone do Fornecedor: "); 
    			f.setTelefone(sc.nextLine());
    			System.out.println("CEP do Fornecedor: "); 
    			f.setCEP(sc.nextLine());    
    			
    	        if (bdd.AdicionarUsuario(f)) {
    	            return true;
    	        }
    		}
    		
    		case "2" -> {
    			System.out.println("Nome de Usuario: "); 
    			String nome = sc.nextLine();
    			System.out.println("Senha do Cliente: "); 
    			String senha = sc.nextLine();
    			
    			Cliente c = new Cliente(nome, senha);
    			
    			if (!bdd.ChecaUsuario(c)) {    				
    	            return false;
    	        }
    			System.out.println("Nome Completo: ");
    			c.setNome(sc.nextLine());
    			System.out.println("Email do Cliente: "); 
    			c.setEmail(sc.nextLine());
    			System.out.println("Telefone do Cliente: "); 
    			c.setTelefone(sc.nextLine());
    			System.out.println("CEP do Cliente: "); 
    			c.setCEP(sc.nextLine());   
    			
    	        if (bdd.AdicionarUsuario(c)) {
    	            return true;
    	        }
    		}
    		
         }
        	 ;        
        
        return false; // Já existe
    }
	
	
    /*private List<Usuario> usuarios = new ArrayList<>();

    public GerenciadorUsuarios() {
        // Conta padrão para testes
        usuarios.add(new Usuario("user", "1234"));
    }

    public boolean cadastrarUsuario(String login, String senha) {
        Usuario novo = new Usuario(login, senha);
        if (!usuarios.contains(novo)) {
            usuarios.add(novo);
            return true;
        }
        return false; // Já existe
    }

    public boolean realizarLogin(String login, String senha) {
        for (Usuario u : usuarios) {
            if (u.getLogin().equals(login) && u.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }*/
}
