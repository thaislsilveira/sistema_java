/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.crud.Sistema.dao;

import br.com.crudSistema.model.Fornecedor;
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
public class FornecedorDAO implements GenericDAO{

     private Connection conexao;
    
    public FornecedorDAO() throws Exception{
        try{
            this.conexao = ConnectionFactory.getConnection();
            System.out.println("Conectado com sucesso");
        }catch(Exception ex){
            throw new Exception("Problemas ao conectar no BD! Erro: " + ex.getMessage());
        }
    }

    @Override
    public Boolean cadastrar(Object objeto) {
      Fornecedor oFornecedor = (Fornecedor) objeto;
      Boolean retorno = false;
      if(oFornecedor.getIdFornecedor()==0){
          retorno = this.inserir(oFornecedor);
      }
      else{
          retorno = this.alterar(oFornecedor);
      }
      return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
      Fornecedor oFornecedor = (Fornecedor) objeto;
      PreparedStatement stmt = null;
      String sql = "Insert into fornecedor (nomefornecedor, rg, cpf, sexo, endereco, idcidade) values (?,?,?,?,?,?)";
      try{
          stmt = conexao.prepareStatement(sql);
          stmt.setString(1, oFornecedor.getNomeFornecedor());
          stmt.setString(2, oFornecedor.getRg());
          stmt.setString(3,oFornecedor.getCpf());
          stmt.setString(4, oFornecedor.getSexo());
          stmt.setString(5, oFornecedor.getEndereco());
          stmt.setInt(6, oFornecedor.getCidade().getIdCidade()); 
          
          stmt.execute();
          return true;
      }
      catch(Exception ex)
      {
          System.out.println("Problemas ao cadastrar fornecedor! Erro:" +ex.getMessage());
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
        Fornecedor oFornecedor = (Fornecedor) objeto;
       PreparedStatement stmt = null;
       String sql = "update fornecedor set nomefornecedor=?, rg=?, cpf=?, sexo=?, endereco=?,idcidade=? where idfornecedor=?";
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setString(1,oFornecedor.getNomeFornecedor());
           stmt.setString(2, oFornecedor.getRg());
           stmt.setString(3,oFornecedor.getCpf());
           stmt.setString(4, oFornecedor.getSexo());
           stmt.setString(5,oFornecedor.getEndereco());
           stmt.setInt(6, oFornecedor.getCidade().getIdCidade());
           stmt.setInt(7,oFornecedor.getIdFornecedor());       
           
           stmt.execute();
           return true;
       }
       catch(Exception ex){
           System.out.println("Problemas ao alterar fornecedor! Erro: " +ex.getMessage());
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
       int idFornecedor = numero;
       PreparedStatement stmt = null;
       String sql = "delete from fornecedor where idfornecedor=?";
       try{
           stmt = conexao.prepareStatement(sql);
           stmt.setInt(1, idFornecedor);
           stmt.execute();
           return true;
       }
       catch(Exception ex){
           System.out.println("Problemas ao excluir fornecedor! Erro: " +ex.getMessage());
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
        int idFornecedor = numero;
       PreparedStatement stmt = null;
       ResultSet rs = null;
       Fornecedor oFornecedor = null;
       String sql = "select f.*, c.* from fornecedor f inner join cidade c on f.idcidade = c.idcidade where f.idfornecedor = ?";
       try{
           stmt=conexao.prepareStatement(sql);
           stmt.setInt(1, idFornecedor);
           rs=stmt.executeQuery();
           while(rs.next()){
               oFornecedor = new Fornecedor();
               oFornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
               oFornecedor.setNomeFornecedor(rs.getString("nomeFornecedor"));
               oFornecedor.setRg(rs.getString("rg"));
               oFornecedor.setCpf(rs.getString("cpf"));
               oFornecedor.setSexo(rs.getString("sexo"));
               oFornecedor.setEndereco(rs.getString("endereco"));
               oFornecedor.getCidade().setNomeCidade(rs.getString("nomeCidade"));
           }
           return oFornecedor;
       }
       catch(SQLException ex){
           System.out.println("Problemas ao carregar fornecedor!" + "Erro: " +ex.getMessage());
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
        
        String sql = "select f.*, c.* from fornecedor f inner join cidade c on f.idcidade = c.idcidade where f.idfornecedor = ?";
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()){
               Fornecedor oFornecedor = new Fornecedor();
               oFornecedor.setIdFornecedor(rs.getInt("idFornecedor"));
               oFornecedor.setNomeFornecedor(rs.getString("nomeFornecedor"));
               oFornecedor.setRg(rs.getString("rg"));
               oFornecedor.setCpf(rs.getString("cpf"));
               oFornecedor.setSexo(rs.getString("sexo"));
               oFornecedor.setEndereco(rs.getString("endereco"));
               oFornecedor.getCidade().setIdCidade(rs.getInt("idCidade"));
               oFornecedor.getCidade().setNomeCidade(rs.getString("nomeCidade"));
               resultado.add(oFornecedor);
            }
        }catch(SQLException ex){
            System.out.println("Problemas ao listar fornecedor! Erro: " +ex.getMessage());
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
