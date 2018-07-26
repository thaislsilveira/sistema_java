/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.Sistema.dao;

import br.com.crudSistema.model.Vendedor;
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
public class VendedorDAO implements GenericDAO{

       private Connection conexao;
    
    public VendedorDAO() throws Exception{
        try{
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso");
        }catch(Exception ex){
            throw new Exception("Problemas ao conectar no BD! Erro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object objeto) {
      Vendedor oVendedor = (Vendedor) objeto;
      Boolean retorno = false;
      if(oVendedor.getIdVendedor()==0){
          retorno = this.inserir(oVendedor);
      }
      else{
          retorno = this.alterar(oVendedor);
      }
      return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
      Vendedor oVendedor = (Vendedor) objeto;
      PreparedStatement stmt = null;
      String sql = "Insert into vendedor (nomevendedor, rg, cpf, sexo, endereco, idcidade) values (?,?,?,?,?,?)";
      try{
          stmt = conexao.prepareStatement(sql);
          stmt.setString(1, oVendedor.getNomeVendedor());
          stmt.setString(2, oVendedor.getRg());
          stmt.setString(3,oVendedor.getCpf());
          stmt.setString(4, oVendedor.getSexo());
          stmt.setString(5, oVendedor.getEndereco());
          stmt.setInt(6, oVendedor.getCidade().getIdCidade()); 
          
          stmt.execute();
          return true;
      }
      catch(Exception ex)
      {
          System.out.println("Problemas ao cadastrar vendedor! Erro:" +ex.getMessage());
          return false;
      }finally{
          try{
              ConnectionFactory.closeConnection(conexao, stmt);
          }catch(Exception ex){
              System.out.println("Problemas ao fechar os parâmetros de conexão! Erro" +ex.getMessage());
          }
      }
    }

    @Override
    public Boolean alterar(Object objeto) {
       Vendedor oVendedor = (Vendedor) objeto;
       PreparedStatement stmt = null;
       String sql = "update vendedor set nomevendedor=?, rg=?, cpf=?, sexo=?, endereco=?,idcidade=? where idvendedor=?";
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setString(1,oVendedor.getNomeVendedor());
           stmt.setString(2, oVendedor.getRg());
           stmt.setString(3,oVendedor.getCpf());
           stmt.setString(4, oVendedor.getSexo());
           stmt.setString(5,oVendedor.getEndereco());
           stmt.setInt(6, oVendedor.getCidade().getIdCidade());
           stmt.setInt(7,oVendedor.getIdVendedor());       
           
           stmt.execute();
           return true;
       }
       catch(Exception ex){
           System.out.println("Problemas ao alterar vendedor! Erro: " +ex.getMessage());
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
       int idVendedor = numero;
       PreparedStatement stmt = null;
       String sql = "delete from vendedor where idvendedor=?";
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, idVendedor);
           stmt.execute();
           return true;
       }
       catch(Exception ex){
           System.out.println("Problemas ao excluir vendedor! Erro: " +ex.getMessage());
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
       int idVendedor = numero;
       PreparedStatement stmt = null;
       ResultSet rs = null;
       Vendedor oVendedor = null;
       String sql = "select v.*, c.* from vendedor v inner join cidade c on v.idcidade = c.idcidade where v.idvendedor= ?";
       try{
           stmt=conexao.prepareStatement(sql);
           stmt.setInt(1, idVendedor);
           rs=stmt.executeQuery();
           while(rs.next()){
               oVendedor = new Vendedor();
               oVendedor.setIdVendedor(rs.getInt("idVendedor"));
               oVendedor.setNomeVendedor(rs.getString("nomeVendedor"));
               oVendedor.setRg(rs.getString("rg"));
               oVendedor.setCpf(rs.getString("cpf"));
               oVendedor.setSexo(rs.getString("sexo"));
               oVendedor.setEndereco(rs.getString("endereco"));
               oVendedor.getCidade().setNomeCidade(rs.getString("nomeCidade"));
           }
           return oVendedor;
       }
       catch(SQLException ex){
           System.out.println("Problemas ao carregar vendedor!" + "Erro: " +ex.getMessage());
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
        
        String sql = "select v.*, c.* from vendedor v inner join cidade c on v.idcidade = c.idcidade where v.idvendedor=?";
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
               Vendedor oVendedor = new Vendedor();
               oVendedor.setIdVendedor(rs.getInt("idVendedor"));
               oVendedor.setNomeVendedor(rs.getString("nomeVendedor"));
               oVendedor.setRg(rs.getString("rg"));
               oVendedor.setCpf(rs.getString("cpf"));
               oVendedor.setSexo(rs.getString("sexo"));
               oVendedor.setEndereco(rs.getString("endereco"));
               oVendedor.getCidade().setIdCidade(rs.getInt("idCidade"));
               oVendedor.getCidade().setNomeCidade(rs.getString("nomeCidade"));
               resultado.add(oVendedor);
            }
        }catch(SQLException ex){
            System.out.println("Problemas ao listar vendedor! Erro: " +ex.getMessage());
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
