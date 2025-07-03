package br.sistema.menu;

import br.sistema.conta.*;

import java.util.*;
import br.sistema.BDD.*;

public class Menu {
    private Loja loja;
    private Scanner scanner;
    private BancoDeDados bdd;

    public Menu(Loja loja, BancoDeDados bdd) {
    	this.bdd = bdd;
        this.loja = loja;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;
        do {
        	bdd.SalvarUsuarios();
        	bdd.SalvarEstoque();
        	bdd.SalvarProdutos();
            System.out.println("\n------ Menu Principal ------");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Listar Produtos");
            System.out.println("3. Alterar Produto");
            System.out.println("4. Excluir Produto");
            System.out.println("5. Buscar Produto por Nome");
            System.out.println("6. Buscar Produto por Código");
            System.out.println("7. Listar Produtos de um Fornecedor");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, insira um número válido.");
                scanner.next();
                System.out.print("Escolha: ");
            }

            opcao = scanner.nextInt();
            scanner.nextLine();
            System.out.print("\n\n");

            switch (opcao) {
                case 1 -> adicionarProduto();
                case 2 -> listarProdutos();
                case 3 -> alterarProduto();
                case 4 -> excluirProduto();
                case 5 -> buscarPorNome();
                case 6 -> buscarPorCodigo();
                case 7 -> listarPorFornecedor();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void adicionarProduto() {
        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();

        Produto produto = new Produto(nome);
        loja.adicionarProduto(produto);

        Fornecedor fornecedor = selecionarOuCriarFornecedor();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();

        System.out.print("Quantidade: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        ItemFornecedorProduto item = new ItemFornecedorProduto(produto, fornecedor, preco, quantidade);
        loja.adicionarItem(item);

        System.out.println("Produto adicionado com sucesso! Código: " + produto.getCodigo());
        System.out.print("\n\n");
    }

    private Fornecedor selecionarOuCriarFornecedor() {
        List<Fornecedor> lista = loja.getFornecedores();
        System.out.println("Fornecedores disponíveis:");
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + ". " + lista.get(i).getNome() + " (" + lista.get(i).getCnpj() + ")");
        }
        System.out.println("0. Criar novo fornecedor");
        System.out.print("Escolha: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        if (escolha > 0 && escolha <= lista.size()) {
            return lista.get(escolha - 1);
        } else {
            System.out.print("Nome do novo fornecedor: ");
            String s = scanner.nextLine();
            Fornecedor novo = new Fornecedor(s);            
            System.out.print("CNPJ do novo fornecedor: ");
            String cnpj = scanner.nextLine();
            novo.setCnpj(cnpj);
            
            loja.adicionarFornecedor(novo);
            return novo;
        }
    }

    private void listarProdutos() {
        for (ItemFornecedorProduto item : loja.listarItens()) {
            System.out.println("Código: " + item.getProduto().getCodigo() +
                    " | Nome: " + item.getProduto().getNome() +
                    " | Preço: R$" + item.getPreco() +
                    " | Quantidade: " + item.getQuantidade() +
                    " | Fornecedor: " + item.getFornecedor().getNome());
        }
    }

    private void alterarProduto() {
        System.out.print("Digite o código do produto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        ItemFornecedorProduto item = loja.buscarPorCodigo(codigo);
        if (item != null) {
            System.out.print("Novo preço: ");
            item.setPreco(scanner.nextDouble());
            System.out.print("Nova quantidade: ");
            item.setQuantidade(scanner.nextInt());
            scanner.nextLine();
            System.out.println("Produto atualizado.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void excluirProduto() {
        System.out.print("Digite o código do produto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        if (loja.removerItem(codigo)) {
            System.out.println("Produto removido.");
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void buscarPorNome() {
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        List<ItemFornecedorProduto> encontrados = loja.buscarPorNome(nome);
        if (encontrados.isEmpty()) {
            System.out.println("Nenhum produto encontrado.");
        } else {
            for (ItemFornecedorProduto item : encontrados) {
                System.out.println("Código: " + item.getProduto().getCodigo() +
                        " | Preço: R$" + item.getPreco() +
                        " | Quantidade: " + item.getQuantidade() +
                        " | Fornecedor: " + item.getFornecedor().getNome());
            }
        }
    }

    private void buscarPorCodigo() {
        System.out.print("Código do produto: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        ItemFornecedorProduto item = loja.buscarPorCodigo(codigo);
        if (item != null) {
            System.out.println("Nome: " + item.getProduto().getNome());
            System.out.println("Preço: R$" + item.getPreco());
            System.out.println("Quantidade: " + item.getQuantidade());
            System.out.println("Fornecedor: " + item.getFornecedor().getNome());
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    private void listarPorFornecedor() {
        Fornecedor f = selecionarOuCriarFornecedor();
        List<ItemFornecedorProduto> lista = loja.buscarPorFornecedor(f);
        if (lista.isEmpty()) {
            System.out.println("Nenhum produto para este fornecedor.");
        } else {
            for (ItemFornecedorProduto item : lista) {
                System.out.println(item.getProduto().getCodigo() + " - " + item.getProduto().getNome() +
                        " | Preço: R$" + item.getPreco() +
                        " | Quantidade: " + item.getQuantidade());
            }
        }
    }
}
