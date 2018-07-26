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
public class Produto {
     private int idProduto;
    private String descricao;
    private float valor_unit;
    private String quantidade;
    private Fornecedor fornecedor;

    public Produto() {
    }

    public Produto(int idProduto, String descricao, float valor_unit, String quantidade, Fornecedor fornecedor) {
        this.idProduto = idProduto;
        this.descricao = descricao;
        this.valor_unit = valor_unit;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getValor_unit() {
        return valor_unit;
    }

    public void setValor_unit(float valor_unit) {
        this.valor_unit = valor_unit;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }    
}
