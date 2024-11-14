
package br.com.DAO;

import br.com.DTO.UsuarioDTO;
import br.com.VIEWS.TelaPrincipal;
import br.com.VIEWS.TelaMáquinas;
import br.com.VIEWS.TelaUsuarios;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.text.SimpleDateFormat;
import java.util.Date;


public class UsuarioDAO {
   
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    // Método para verificar login
    public boolean logar(String login, String senha) {
        String sql = "SELECT * FROM usuarios WHERE nome = ? AND senha = ?";
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
         TelaUsuarios.boxPerfil.setSelectedIndex(-1);
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
public void inserirUsuario(UsuarioDTO objUsuarioDTO) {
    // Atualizando a consulta SQL para não incluir a data_cadastro
    String sql = "INSERT INTO usuarios (nome, email, senha, perfil) VALUES (?, ?, ?, ?)";
    conexao = new ConexaoDAO().conector();
    
    try {
        pst = conexao.prepareStatement(sql);
        
        // Remove o parâmetro id_usuario e data_cadastro
        // pst.setInt(1, objUsuarioDTO.getIdUsuario()); // Não é mais necessário
        pst.setString(1, objUsuarioDTO.getNomeUsuario());
        pst.setString(2, objUsuarioDTO.getEmailUsuario());
        pst.setString(3, objUsuarioDTO.getSenhaUsuario());
        pst.setString(4, objUsuarioDTO.getPerfilUsuario()); 

        // Executa a inserção
        pst.executeUpdate(); // Use executeUpdate() para inserções
        pst.close();

        // Limpa os campos após a inserção, se necessário
        limpar();
        
        JOptionPane.showMessageDialog(null, "Usuário inserido com sucesso!");
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Inserir Usuário: " + e);
    }
}

    
    //Metodo editar
    public void editar(UsuarioDTO objUsarioDTO){
        String sql = "update usuarios set nome = ?, perfil = ?, email = ?, senha = ?, data_cadastro = ? where id_usuario = ?";
         conexao = ConexaoDAO.conector();
         try {
               pst = conexao.prepareStatement(sql);
               pst.setString(1, objUsarioDTO.getNomeUsuario());
               pst.setString(2, objUsarioDTO.getPerfilUsuario());
               pst.setString(3, objUsarioDTO.getEmailUsuario());
               pst.setString(4, objUsarioDTO.getSenhaUsuario());
               pst.setString(5, objUsarioDTO.getDataCadastro());
                pst.setInt(6, objUsarioDTO.getIdUsuario());
                
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
public void excluir(UsuarioDTO objUsuarioDTO) {
    String sql = "DELETE FROM usuarios WHERE id_usuario = ?";
    conexao = new ConexaoDAO().conector();
    
    try {
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, objUsuarioDTO.getIdUsuario());
        
        int linhasAfetadas = pst.executeUpdate(); // Use executeUpdate para operações de DML

        if (linhasAfetadas > 0) {
            JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
        }

        pst.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao excluir usuário: " + e);
    }
    limpar();
}

    
    //Método para configurar JComboBox
  

    public List<String> obterPerfis() {
        List<String> perfis = new ArrayList<>();
        String sql = "SELECT DISTINCT Perfil FROM Usuarios";
        
        try (Connection conn = ConexaoDAO.conector();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                perfis.add(rs.getString("Perfil"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar perfis: " + e.getMessage());
        }
        return perfis;
    }

    //Método para formatar data para Dia-MÊs-Ano
    public String formatarData(String dataString) {
    try {
        // Cria um formato de data que corresponde ao formato da string de entrada
        SimpleDateFormat formatoEntrada = new SimpleDateFormat("dd/MM/yyyy");
        // Cria um formato de data que corresponde ao formato desejado
        SimpleDateFormat formatoSaida = new SimpleDateFormat("yyyy-MM-dd");
        
        // Converte a string para uma data
        Date data = formatoEntrada.parse(dataString);
        // Retorna a data formatada como uma string no formato correto
        return formatoSaida.format(data);
    } catch (Exception e) {
        e.printStackTrace();
        return null; // Ou trate o erro de acordo
    }
}
    
     // Método para obter o perfil de um usuário a partir do banco de dados
    public String obterPerfilUsuario(int idUsuario) {
        String perfil = "";
        String sql = "SELECT Perfil FROM usuarios WHERE id_usuario = ?";
        
        try (Connection con = ConexaoDAO.conector();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                perfil = rs.getString("perfil");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return perfil;
    }
    
    
}
