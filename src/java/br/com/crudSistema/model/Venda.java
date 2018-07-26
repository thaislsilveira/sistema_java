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
public class Venda {
    private int idVenda;
    private Usuario usuario;
    private Produto produto;
    private float valorQuantidade;
    private Vendedor vendedor;
    private float valorVenda;

    public Venda() {
    }

    public Venda(int idVenda, Usuario usuario, Produto produto, float valorQuantidade, Vendedor vendedor, float valorVenda) {
        this.idVenda = idVenda;
        this.usuario = usuario;
        this.produto = produto;
        this.valorQuantidade = valorQuantidade;
        this.vendedor = vendedor;
        this.valorVenda = valorVenda;
    }

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public float getValorQuantidade() {
        return valorQuantidade;
    }

    public void setValorQuantidade(float valorQuantidade) {
        this.valorQuantidade = valorQuantidade;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public float getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(float valorVenda) {
        this.valorVenda = valorVenda;
    }
}
