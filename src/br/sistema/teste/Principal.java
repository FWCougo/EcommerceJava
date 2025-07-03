package br.sistema.teste;

import java.util.Scanner;

import br.sistema.BDD.BancoDeDados;
import br.sistema.conta.Cliente;
import br.sistema.conta.Fornecedor;
import br.sistema.conta.GerenciadorUsuarios;
import br.sistema.conta.ItemFornecedorProduto;
import br.sistema.conta.Login;
import br.sistema.conta.Loja;
import br.sistema.conta.Produto;
import br.sistema.menu.*;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BancoDeDados bdd = new BancoDeDados();        
<<<<<<< Updated upstream
        Loja loja = inicializarLojaComProdutos();
=======
        Loja loja = new Loja(bdd);
>>>>>>> Stashed changes
        //HistoricoCompras historico = new HistoricoCompras();
        GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios(bdd);
//        Usuario usuario = new Usuario();
        
        
        
        while (true) {
            System.out.println("\nEscolha uma opção:");
            /*System.out.println("1 - Login como Admin");
            System.out.println("2 - Login como Usuário");
            System.out.println("3 - Cadastrar Novo Usuário");
            System.out.println("0 - Sair do sistema");
            System.out.print("Opção: ");*/
            
            System.out.println("1 - Fazer Login");
            System.out.println("2 - Cadastre-se");
            System.out.println("0 - Sair do sistema");
            System.out.print("Opção: ");
            
            String tipo = scanner.nextLine();
            
            switch (tipo) {
            	case "1" -> {
            		Login l = new Login(bdd); 					      
                    int tipoDeConta = -1;						
                    
                    while (tipoDeConta == -1) { 				
                    	tipoDeConta = l.realizarLogin(scanner);       	
                    }                     
                    if(tipoDeConta == 0) { 						
<<<<<<< Updated upstream
                    	Menu menu = new Menu(loja);             
=======
                    	Menu menu = new Menu(loja, bdd);             
>>>>>>> Stashed changes
                        menu.exibirMenu();					    
                    }
                    else
                    {
                    	 if(l.getUsuario().getFornecedor()==0) {
                    		 
                    		 Cliente c = (Cliente) l.getUsuario();
                    		 MenuUsuario menuUsuario = new MenuUsuario(loja, c,bdd);
                             menuUsuario.exibirMenu();	 
                             
                    	 }else if(l.getUsuario().getFornecedor()==1) {
                    		 Fornecedor f = (Fornecedor) l.getUsuario();
                    		 MenuFornecedor menuFornecedor = new MenuFornecedor(loja, f, bdd);
                    		 menuFornecedor.exibirMenu();                   		
                    	 }
                    	 
                    	 
                    }

            	}
            	case "2" -> {             		
            		
                    /*System.out.print("Novo login: ");
                    String novoLogin = scanner.nextLine();
                    System.out.print("Nova senha: ");
                    String novaSenha = scanner.nextLine();
                    
                    boolean cadastrado = gerenciadorUsuarios.cadastrarUsuario(novoLogin, novaSenha);*/
            		
            		boolean cadastrado = gerenciadorUsuarios.cadastrarUsuario(scanner);
                    
                    if (cadastrado) {
                        System.out.println("Usuário cadastrado com sucesso!");
                    } else {
                        System.out.println("Usuário já existe.");
                    }
            	}
            	case "0" -> {
            		System.out.println("Encerrando o sistema...");
                    System.exit(0);
            	}
            }
            
            
            
        }
            

            /*switch (tipo) {
                case "1" -> {
                    while (!Login.realizarLogin()) {
                        System.out.println("Usuário ou senha incorretos. Tente novamente.\n");
                    }
                    Menu menu = new Menu(loja);
                    menu.exibirMenu();
                }
                case "2" -> {
                    if (realizarLoginUsuario(scanner, gerenciadorUsuarios)) {
                        MenuUsuario menuUsuario = new MenuUsuario(loja, historico);
                        menuUsuario.exibirMenu();
                    } else {
                        System.out.println("Usuário ou senha incorretos.\n");
                    }
                }
                case "3" -> {
                    System.out.print("Novo login: ");
                    String novoLogin = scanner.nextLine();
                    System.out.print("Nova senha: ");
                    String novaSenha = scanner.nextLine();
                    boolean cadastrado = gerenciadorUsuarios.cadastrarUsuario(novoLogin, novaSenha);
                    if (cadastrado) {
                        System.out.println("Usuário cadastrado com sucesso!");
                    } else {
                        System.out.println("Usuário já existe.");
                    }
                }
                case "0" -> {
                    System.out.println("Encerrando o sistema...");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }*/
    }

 /*   private static boolean realizarLoginUsuario(Scanner sc, GerenciadorUsuarios gUsuarios) {
        System.out.print("Usuário: ");
        String user = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();
        return gUsuarios.realizarLogin(user, senha);
    }*/

<<<<<<< Updated upstream
    private static Loja inicializarLojaComProdutos() {
        Loja loja = new Loja();
        Fornecedor fornecedor = new Fornecedor("Fornecedor Padrão", "12345678000199");
=======
/*    private static Loja inicializarLojaComProdutos() {
        Loja loja = new Loja();
        Fornecedor fornecedor = new Fornecedor("Fornecedor Padrão", "12345678000199", "Rua dos Bobos");
>>>>>>> Stashed changes
        loja.adicionarFornecedor(fornecedor);

        Produto p1 = new Produto("Mouse Gamer");
        loja.adicionarProduto(p1);
        loja.adicionarItem(new ItemFornecedorProduto(p1, fornecedor, 120.00, 10));

        Produto p2 = new Produto("Teclado Mecânico");
        loja.adicionarProduto(p2);
        loja.adicionarItem(new ItemFornecedorProduto(p2, fornecedor, 250.00, 5));

        return loja;
    }*/
} 
