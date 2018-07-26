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
public class Vendedor {
    private int idVendedor;
    private String nomeVendedor;
    private String rg;
    private String cpf;
    private String sexo;
    private String endereco;
    private Cidade cidade;

    public Vendedor() {
    }

    public Vendedor(int idVendedor, String nomeVendedor, String rg, String cpf, String sexo, String endereco, Cidade cidade) {
        this.idVendedor = idVendedor;
        this.nomeVendedor = nomeVendedor;
        this.rg = rg;
        this.cpf = cpf;
        this.sexo = sexo;
        this.endereco = endereco;
        this.cidade = cidade;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNomeVendedor() {
        return nomeVendedor;
    }

    public void setNomeVendedor(String nomeVendedor) {
        this.nomeVendedor = nomeVendedor;
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
