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
public class Usuario {
    private int idUsuario;
    private String nomeUsuario;
    private String rg;
    private String cpf;
    private String sexo;
    private String endereco;
    private Cidade cidade;

    public Usuario() {
    }

    public Usuario(int idUsuario, String nomeUsuario, String rg, String cpf, String sexo, String endereco, Cidade cidade) {
        this.idUsuario = idUsuario;
        this.nomeUsuario = nomeUsuario;
        this.rg = rg;
        this.cpf = cpf;
        this.sexo = sexo;
        this.endereco = endereco;
        this.cidade = cidade;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
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
