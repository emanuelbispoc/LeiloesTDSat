/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn = new conectaDAO().connectDB();
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){     
        try{
            prep = conn.prepareStatement("INSERT INTO produtos (nome, valor, status) values (?,?,?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3,produto.getStatus());
            
            prep.executeUpdate();
            
            conn.close();
        }catch(SQLException e) {
            System.out.println("ERRO: "+e.getMessage());
        }    
    }
    
    public ArrayList<ProdutosDTO> listarProdutos() {
        try {
            resultset = conn.prepareStatement("SELECT * FROM produtos")
                    .executeQuery();
            
            gerarResultado(resultset);
        } catch (Exception e) {
        }
        
        return listagem;
    }
    
    private void gerarResultado(ResultSet rs) {        
        try {            
            while (rs.next()) {
                listagem.add(
                        new ProdutosDTO(
                                rs.getInt("id"),
                                rs.getString("nome"),
                                rs.getInt("valor"),
                                rs.getString("status")
                        )
                ); 
            }
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
        }     
    }
         
}

