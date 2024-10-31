
package br.com.DAO;

import br.com.DTO.UsuarioDTO;
import br.com.VIEWS.TelaPrincipal;
import br.com.VIEWS.TelaMáquinas;
import br.com.VIEWS.TelaUsuarios;
import java.awt.Color;
import java.sql.*;
import javax.swing.JOptionPane;


public class UsuarioDAO {
   
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    // Método para verificar login
    public boolean logar(String login, String senha) {
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";
        conexao = new ConexaoDAO().conector(); // Conecta ao banco de dados

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, login);
            pst.setString(2, senha);

            rs = pst.executeQuery();

            if (rs.next()) {
                // Login e senha corretos
                return true;
            }

            rs.close();
            pst.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro no login: " + e);
        }

        return false; // Login ou senha inválidos
    }
   
   
   
     public void limpar(){
         TelaUsuarios.txtIdUsuario.setText(null);
         TelaUsuarios.txtNomeUsuario.setText(null);
         TelaUsuarios.txtPerfil.setText(null);
         TelaUsuarios.txtSenha.setText(null);
         TelaUsuarios.txtEmail.setText(null);
         TelaUsuarios.txtData.setText(null);
    }
    
    
     //Metodo pesquisar
    public UsuarioDTO pesquisarUsuario(int idUsuario) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        conexao = new ConexaoDAO().conector();
        UsuarioDTO usuarioDTO = null;

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            rs = pst.executeQuery();

            // Se encontrar um usuário, cria o objeto UsuarioDTO com os dados
            if (rs.next()) {
                usuarioDTO = new UsuarioDTO();
                usuarioDTO.setIdUsuario(rs.getInt("id_usuario"));
                usuarioDTO.setNomeUsuario(rs.getString("nome"));
                usuarioDTO.setPerfilUsuario(rs.getString("perfil"));
                usuarioDTO.setEmailUsuario(rs.getString("email"));
                usuarioDTO.setSenhaUsuario(rs.getString("senha"));
                usuarioDTO.setDataCadastro(rs.getString("data_cadastro"));
            }

            rs.close();
            pst.close();
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar usuário: " + e);
        }

        return usuarioDTO;
       
    }
    
    //Metodo inserir/adicionar usuarios
    public void inserirUsuario(UsuarioDTO objUsuarioDTO){
        String sql = "insert into usuarios(id_usuario, nome, email, senha, perfil, data_cadastro) values(?, ?, ?, ?, ?, ?, ?)";
        conexao = new ConexaoDAO().conector();
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, objUsuarioDTO.getIdUsuario());
            pst.setString(2, objUsuarioDTO.getNomeUsuario());
            pst.setString(3, objUsuarioDTO.getPerfilUsuario());
            pst.setString(4, objUsuarioDTO.getEmailUsuario());
            pst.setString(5, objUsuarioDTO.getSenhaUsuario());
            pst.setString(6, objUsuarioDTO.getDataCadastro());
          
            
            pst.execute();
            pst.close();
             limpar();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Inserir Usuario:" + e);
        }

    }   
      //Metodo para virificar se usuario ou id ja existem
    public boolean verificarUsuarioExistente(int idUsuario) {
        String sql = "SELECT * FROM usuarios WHERE id_usuario = ?";
        conexao = new ConexaoDAO().conector();
        boolean existe = false;

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idUsuario);
            
            rs = pst.executeQuery();

            if (rs.next()) {
                existe = true;  // Se houver resultado, o usuário já existe
            }
            
            rs.close();
            pst.close();
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao verificar usuário existente: " + e);
        }

        return existe;
    }
    
    
    //Metodo editar
    public void editar(UsuarioDTO objUsarioDTO){
        String sql = "update usuarios set nome = ?, perfil = ?, email = ?, senha = ?, data = ? where id_usuario = ?";
         conexao = ConexaoDAO.conector();
         try {
               pst = conexao.prepareStatement(sql);
               pst.setString(1, objUsarioDTO.getNomeUsuario());
               pst.setString(2, objUsarioDTO.getPerfilUsuario());
               pst.setString(3, objUsarioDTO.getEmailUsuario());
               pst.setString(4, objUsarioDTO.getSenhaUsuario());
               pst.setString(4, objUsarioDTO.getDataCadastro());
                pst.setInt(5, objUsarioDTO.getIdUsuario());
                
               int add = pst.executeUpdate();
               if (add >0){
                   JOptionPane.showMessageDialog(null, "Usuário editado com sucesso!");
                    //pesquisaAuto();
                    conexao.close();
                    limpar();
               }
               
            
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null," Método editar " + e);
        
        }
    }
    
    //Metodo Excluir
    public void excluir (UsuarioDTO objUsuarioDTO){
        String sql = "delete from usuarios where id_usuario = ?";
        conexao = ConexaoDAO.conector();
        
        try {
              pst = conexao.prepareStatement(sql);
              pst.setInt(1, objUsuarioDTO.getIdUsuario());
            
                    int add = pst.executeUpdate();
                    if (add >0){
                    JOptionPane.showMessageDialog(null, "Usuário excluido com sucesso!");
                    //pesquisaAuto();
                    conexao.close();
                   limpar();
               }
              
                      } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Método apagar" +e);
        }
        
    }
    
    
    
    
    
    
}
