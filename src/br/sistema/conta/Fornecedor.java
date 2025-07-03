package br.sistema.conta;

public class Fornecedor extends Usuario{
    private String cnpj;

    public Fornecedor() {
    }
    
    public Fornecedor(String nome) {
        super(nome);
    }
    
    public Fornecedor(String nome, String senha) {
        super(nome, senha);
    }
    
    /*public Fornecedor(String nome, String cnpj) {
        super(nome);
        this.cnpj = cnpj;
    }*/

    /*public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }*/

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Fornecedor f) {
            return this.cnpj.equals(f.cnpj);
        }
        return false;
    }
}
