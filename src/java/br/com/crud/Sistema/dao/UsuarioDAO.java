/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.Sistema.dao;

import br.com.crudSistema.model.Usuario;
import br.com.crudSistema.utils.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thais Silveira
 */
public class UsuarioDAO implements GenericDAO {

   private Connection conexao;
    
    public UsuarioDAO() throws Exception{
        try{
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso");
        }catch(Exception ex){
            throw new Exception("Problemas ao conecar no BD! Erro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object objeto) {
       Usuario oUsuario = (Usuario) objeto;
       Boolean retorno = false;
       if(oUsuario.getIdUsuario()==0){
           retorno = this.inserir(oUsuario);
       }
       else{
           retorno = this.alterar(oUsuario);
       }
       return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Usuario oUsuario = (Usuario) objeto;
        PreparedStatement stmt = null;
        String sql = "Insert into usuario (nomeusuario, rg , cpf, sexo, endereco, idcidade ) values (?,?,?,?,?,?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oUsuario.getNomeUsuario());
            stmt.setString(2, oUsuario.getRg());
            stmt.setString(3,oUsuario.getCpf());
            stmt.setString(4, oUsuario.getSexo());
            stmt.setString(5, oUsuario.getEndereco());
            stmt.setInt(6, oUsuario.getCidade().getIdCidade());
        
            stmt.execute();
            return true;
        }
        catch(Exception ex)
        {
            System.out.println("Problemas ao cadastrar usuario!Erro:" +ex.getMessage());
            return false;
        }
        finally
        {
            try
            {
                ConnectionFactory.closeConnection(conexao, stmt);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar os parâmetros de conexão! Erro: " + ex.getMessage());
            }
        }       
    }

    @Override
    public Boolean alterar(Object objeto) {
       Usuario oUsuario = (Usuario) objeto;
       PreparedStatement stmt = null;
       String sql = "update usuario set nomeusuario=?, rg=?, cpf=?, sexo=?, endereco=?,idcidade=? where idusuario=?";
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setString(1,oUsuario.getNomeUsuario());
           stmt.setString(2, oUsuario.getRg());
           stmt.setString(3,oUsuario.getCpf());
           stmt.setString(4, oUsuario.getSexo());
           stmt.setString(5,oUsuario.getEndereco());
           stmt.setInt(6, oUsuario.getCidade().getIdCidade());
           stmt.setInt(7,oUsuario.getIdUsuario());       
           
           stmt.execute();
           return true;
       }
       catch(Exception ex){
           System.out.println("Problemas ao alterar usuário! Erro: " +ex.getMessage());
           return false;
       }
       finally
       {
           try{
               ConnectionFactory.closeConnection(conexao, stmt);
           }catch(Exception ex){
               System.out.println("Problemas ao fechar os parâmetros de conexão! Erro" + ex.getMessage());
           }
       }
    }

    @Override
    public Boolean excluir(int numero) {
       int idUsuario = numero;
       PreparedStatement stmt = null;
       String sql = "delete from usuario where idusuario=?";
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, idUsuario);
           stmt.execute();
           return true;
       }
       catch(Exception ex){
           System.out.println("Problemas ao excluir usuário! Erro: " +ex.getMessage());
           return false;
       }
       finally{
           try{
               ConnectionFactory.closeConnection(conexao, stmt);
           }catch(Exception ex){
               System.out.println("Problemas ao fechar os parametros de conexão! Erro: " +ex.getMessage());
           }
       }
    }

    @Override
    public Object carregar(int numero) {
       int idUsuario = numero;
       PreparedStatement stmt = null;
       ResultSet rs = null;
       Usuario oUsuario = null;
       String sql = "select u.*, c.* from usuario u inner join cidade c on u.idcidade = c.idcidade where u.idusuario = ?";
       try{
           stmt=conexao.prepareStatement(sql);
           stmt.setInt(1, idUsuario);
           rs=stmt.executeQuery();
           while(rs.next()){
               oUsuario = new Usuario();
               oUsuario.setIdUsuario(rs.getInt("idUsuario"));
               oUsuario.setNomeUsuario(rs.getString("nomeUsuario"));
               oUsuario.setRg(rs.getString("rg"));
               oUsuario.setCpf(rs.getString("cpf"));
               oUsuario.setSexo(rs.getString("sexo"));
               oUsuario.setEndereco(rs.getString("endereco"));
               oUsuario.getCidade().setNomeCidade(rs.getString("nomeCidade"));
           }
           return oUsuario;
       }
       catch(SQLException ex){
           System.out.println("Problemas ao carregar usuário!" + "Erro: " +ex.getMessage());
           return null;
       }finally{
           try{
               
           }catch(Exception ex){
               System.out.println("Problemas ao fechar os parâmetros de conexão! Erro: " +ex.getMessage());
           }
       }
       
    }

    @Override
    public List<Object> Listar() {
        List <Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        String sql = "select u.*, c.* from usuario u inner join cidade c on u.idcidade = c.idcidade where u.idusuario = ?";
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
               Usuario oUsuario = new Usuario();
               oUsuario.setIdUsuario(rs.getInt("idUsuario"));
               oUsuario.setNomeUsuario(rs.getString("nomeUsuario"));
               oUsuario.setRg(rs.getString("rg"));
               oUsuario.setCpf(rs.getString("cpf"));
               oUsuario.setSexo(rs.getString("sexo"));
               oUsuario.setEndereco(rs.getString("endereco"));
               oUsuario.getCidade().setIdCidade(rs.getInt("idCidade"));
               oUsuario.getCidade().setNomeCidade(rs.getString("nomeCidade"));
               resultado.add(oUsuario);
            }
        }catch(SQLException ex){
            System.out.println("Problemas ao listar usuário! Erro: " +ex.getMessage());
        }finally{
            try{
                ConnectionFactory.closeConnection(conexao, stmt,rs);
            }catch(Exception ex){
                System.out.println("Problemas ao fechar os "
                + "parâmetros de conexão! Erro: " + ex.getMessage());
            }
        }
        return resultado;
    }    
}
