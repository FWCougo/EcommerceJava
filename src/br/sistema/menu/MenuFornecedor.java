package br.sistema.menu;

import br.sistema.conta.*;
import br.sistema.BDD.*;
import br.sistema.exceptions.*;
import java.util.List;
import java.util.Scanner;

public class MenuFornecedor {
	private Loja loja;
	private Scanner scanner;
	private BancoDeDados bdd;
	private Fornecedor fornecedor;
	
	public MenuFornecedor (Loja loja, Fornecedor fornecedor, BancoDeDados bdd) {
		this.loja = loja;
		this.fornecedor = fornecedor;
		this.bdd = bdd;
		this.scanner = new Scanner(System.in);
	}
	
	public void exibirMenu() {
		int opcao = 10;
		do {
        bdd.SalvarUsuarios();
        bdd.SalvarEstoque();
        bdd.SalvarProdutos();
		System.out.println("\n--- Menu do Fornecedor ---");
        System.out.println("1. Ver todos os produtos");
        System.out.println("2. Buscar produto por nome");
        System.out.println("3. Buscar produto por código");
        System.out.println("4. Atualizar estoque do produto");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor, insira um número válido.");
            scanner.next();
        }
		opcao = scanner.nextInt();
		scanner.nextLine();
		
		switch (opcao) {
			default -> System.out.println("Opção inválida.");
			case 1 -> verProdutos();
			case 2 -> buscarProdutosNome();
			case 3 -> buscarProdutosCodigo();
			case 4 -> atualizarEstoque();
            case 0 -> System.out.println("Saindo...");
            	
		}
	} while (opcao != 0);
	}
	
	private void verProdutos() {
		for (ItemFornecedorProduto item : loja.listarItens()) {
            System.out.println(item.getProduto().getCodigo() + " - " + item.getProduto().getNome() +
                " | Preço: R$" + item.getPreco() +
                " | Quantidade: " + item.getQuantidade() +
                " | Fornecedor: " + item.getFornecedor().getNome());
        }
	}
	
	private void buscarProdutosNome() {
		 System.out.print("Nome: ");
	        String nome = scanner.nextLine();
	        List<ItemFornecedorProduto> encontrados = loja.buscarPorNome(nome);
	        for (ItemFornecedorProduto item : encontrados) {
	            System.out.println(item.getProduto().getCodigo() + " - " + item.getProduto().getNome() +
	                " | Preço: R$" + item.getPreco() +
	                " | Quantidade: " + item.getQuantidade() +
	                " | Fornecedor: " + item.getFornecedor().getNome());
	        }
	        if (encontrados.isEmpty()) {
	            System.out.println("Nenhum produto encontrado.");
	        }
	}
	
	private void buscarProdutosCodigo() {
		System.out.print("Código: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        ItemFornecedorProduto item = loja.buscarPorCodigo(codigo);
        if (item != null) {
            System.out.println(item.getProduto().getNome() + " | R$" + item.getPreco() +
                " | Qtd: " + item.getQuantidade() +
                " | Fornecedor: " + item.getFornecedor().getNome());
        } else {
            System.out.println("Produto não encontrado.");
        }
	}
	private void atualizarEstoque() {
		List<Fornecedor> lista = loja.getFornecedores();
        for (Fornecedor f : lista) {
            if (f.getNome().equalsIgnoreCase(fornecedor.getNome())) {
                List<ItemFornecedorProduto> itens = loja.buscarPorFornecedor(f);
                for (ItemFornecedorProduto item : itens) {
                    System.out.println(item.getProduto().getCodigo() + " - " + item.getProduto().getNome() +
                        " | Preço: R$" + item.getPreco() +
                        " | Quantidade: " + item.getQuantidade());
                }
            }
        }
        System.out.println("Digite o codigo do item a ser modificado.");
        int codigoMod = scanner.nextInt();
        ItemFornecedorProduto item = loja.buscarPorCodigo(codigoMod);
        if (item != null) {
        	System.out.println(item.getProduto().getNome() + " | R$" + item.getPreco() +
                    " | Qtd: " + item.getQuantidade() +
                    " | Fornecedor: " + item.getFornecedor().getNome());
        	System.out.println("O que modificar nesse item?");
        	System.out.println("Nome (nome), preço (prc), ou quantidade disponível (qtd)?");
        	String s = scanner.nextLine();
        	if (s.equals("nome")) {
        		System.out.println("Novo nome: ");
        		String n = scanner.nextLine();
        		item.getProduto().setNome(n);
        	}
        	else if (s.equals("prc")) {
        		System.out.println("Novo preco: ");
        		try {
        			Double p = scanner.nextDouble();
            		item.setPreco(p);
        		} catch (DadoErradoException err){
        			
        		}
        	}
        	else if (s.equals("qtd")) {
        		System.out.println("Nova quantidade: ");
        		int q = scanner.nextInt();
        		item.setQuantidade(q);
        	}
        	else {
        		System.out.println("Argumento inválido. Encerrando modificação.");
        		return;
        	}  	
	}
        else {
        	System.out.println("Código inválido. Encerrando modificação.");
        	return;
        }
}

}
