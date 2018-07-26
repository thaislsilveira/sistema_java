/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crudSistema.model;

/**
 *
 * @author Thais Silveira
 */
public class Fornecedor {
    private int idFornecedor;
    private String nomeFornecedor;
    private String rg;
    private String cpf;
    private String sexo;
    private String endereco;
    private Cidade cidade;

    public Fornecedor() {
    }

    public Fornecedor(int idFornecedor, String nomeFornecedor, String rg, String cpf, String sexo, String endereco, Cidade cidade) {
        this.idFornecedor = idFornecedor;
        this.nomeFornecedor = nomeFornecedor;
        this.rg = rg;
        this.cpf = cpf;
        this.sexo = sexo;
        this.endereco = endereco;
        this.cidade = cidade;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNomeFornecedor() {
        return nomeFornecedor;
    }

    public void setNomeFornecedor(String nomeFornecedor) {
        this.nomeFornecedor = nomeFornecedor;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }    
}
