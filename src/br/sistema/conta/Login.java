package br.sistema.conta;

import java.util.Scanner;
import br.sistema.BDD.*;

public class Login {
    private static final String ADMIN_USUARIO = "admin";
    private static final String ADMIN_SENHA = "1234";
    
    private BancoDeDados bdd;
    
    private Usuario usuario;
    
    public Login(BancoDeDados bdd) 
    {
    	this.bdd = bdd;
    }
    
    public int realizarLogin(Scanner sc) {
    	System.out.println("---------- Login ----------");
    	
        System.out.print("Usuário: ");
        String usuario = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();         
        
        if(usuario.equals(ADMIN_USUARIO) && senha.equals(ADMIN_SENHA)) {
        	System.out.println("\nBem vindo Administrador");
        	return 0;
        }
                
        for(int i = 0; i<bdd.getContas().size();i++) {
        	if(bdd.getContas().get(i).getLogin().equals(usuario) && bdd.getContas().get(i).getSenha().equals(senha)){
        			System.out.println("\nBem vindo " + usuario);
        			this.usuario = bdd.getContas().get(i);
        			return 1;
        	}
        }
        
        System.out.println("Usuário ou senha incorretos. Tente novamente.\n");
		return -1;

    }

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
    
    
    
    /*public static boolean realizarLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Usuário: ");
        String usuario = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        return usuario.equals(ADMIN_USUARIO) && senha.equals(ADMIN_SENHA);
    }*/
}