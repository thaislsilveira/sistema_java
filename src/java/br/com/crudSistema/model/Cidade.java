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
public class Cidade {
    private int idCidade;
    private String nomeCidade;
    private String cep;
    private Estado uf;

    public Cidade() {
    }

    public Cidade(int idCidade, String nomeCidade, String cep, Estado uf) {
        this.idCidade = idCidade;
        this.nomeCidade = nomeCidade;
        this.cep = cep;
        this.uf = uf;
    }

    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Estado getUf() {
        return uf;
    }

    public void setUf(Estado uf) {
        this.uf = uf;
    }    
}
