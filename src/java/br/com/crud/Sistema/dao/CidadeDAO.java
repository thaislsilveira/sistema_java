/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.Sistema.dao;

import br.com.crudSistema.model.Cidade;
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
public class CidadeDAO implements GenericDAO{

    private Connection conexao;
    
    public CidadeDAO() throws Exception{
         try{
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso");
        }catch(Exception ex){
            throw new Exception("Problemas ao conectar no BD! Erro: " + ex.getMessage());
        }
    }
    
    @Override
    public Boolean cadastrar(Object objeto) {
        
       Cidade oCidade = (Cidade) objeto;
       Boolean retorno = false;
       if(oCidade.getIdCidade()==0){
           retorno = this.inserir(oCidade);
       }
       else{
           retorno = this.alterar(oCidade);
       }
       return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Cidade oCidade = (Cidade) objeto;
        PreparedStatement stmt = null;
        String sql = "Insert into cidade (nomecidade, cep, idestado ) values (?,?,?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oCidade.getNomeCidade());
            stmt.setString(2, oCidade.getCep());           
            stmt.setInt(3, oCidade.getUf().getIdEstado());
        
            stmt.execute();
            return true;
        }
        catch(Exception ex)
        {
            System.out.println("Problemas ao cadastrar cidade!Erro:" +ex.getMessage());
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
       Cidade oCidade = (Cidade) objeto;
       PreparedStatement stmt = null;
       String sql = "update usuario set nomecidade=?, cep=?,idestado=? where idcidade=?";
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setString(1,oCidade.getNomeCidade());
           stmt.setString(2, oCidade.getCep());         
           stmt.setInt(3, oCidade.getUf().getIdEstado());
           stmt.setInt(4,oCidade.getIdCidade());       
           
           stmt.execute();
           return true;
       }
       catch(Exception ex){
           System.out.println("Problemas ao alterar Cidade! Erro: " +ex.getMessage());
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
        int idCidade = numero;
       PreparedStatement stmt = null;
       String sql = "delete from cidade where idcidade=?";
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, idCidade);
           stmt.execute();
           return true;
       }
       catch(Exception ex){
           System.out.println("Problemas ao excluir cidade! Erro: " +ex.getMessage());
           return false;
       }
       finally{
           try{
               ConnectionFactory.closeConnection(conexao, stmt);
           }catch(Exception ex){
               System.out.println("Problemas ao fechar os parâmetros de conexão! Erro: " +ex.getMessage());
           }
       }
    }

    @Override
    public Object carregar(int numero) {
       int idCidade = numero;
       PreparedStatement stmt = null;
       ResultSet rs = null;
       Cidade oCidade = null;
       String sql = "select c.*, e.* from cidade c inner join estado e on c.idestado = e.idestado where u.idcidade = ?";
       try{
           stmt=conexao.prepareStatement(sql);
           stmt.setInt(1, idCidade);
           rs=stmt.executeQuery();
           while(rs.next()){
               oCidade = new Cidade();
               oCidade.setIdCidade(rs.getInt("idCidade"));
               oCidade.setNomeCidade(rs.getString("nomeCidade"));
               oCidade.setCep(rs.getString("cep"));
               oCidade.getUf().setNomeEstado(rs.getString("nomeEstado"));
           }
           return oCidade;
       }
       catch(SQLException ex){
           System.out.println("Problemas ao carregar cidade!" + "Erro: " +ex.getMessage());
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
        
        String sql = "select c.*, e.* from cidade c inner join estado e on c.idestado = e.idestado where u.idcidade = ?";
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
               Cidade oCidade = new Cidade();
               oCidade.setIdCidade(rs.getInt("idCidade"));
               oCidade.setNomeCidade(rs.getString("nomeCidade"));
               oCidade.setCep(rs.getString("cep"));
               oCidade.getUf().setIdEstado(rs.getInt("idEstado"));
               oCidade.getUf().setNomeEstado(rs.getString("nomeEstado"));
               resultado.add(oCidade);
            }
        }catch(SQLException ex){
            System.out.println("Problemas ao listar Cidade! Erro: " +ex.getMessage());
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
