
package br.com.DAO;


import br.com.DTO.PecasDTO;
import br.com.VIEWS.Telapecas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class PecasDAO {
    
     
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
         Telapecas.txtIdPecas.setText(null);
         Telapecas.txtFabricantePeca.setText(null);
         Telapecas.txtNomePeca.setText(null);
         Telapecas.txtQuantidadadePeca.setText(null);
         Telapecas.txtTipoPeca.setText(null);
        
    }
    
    //Metodo itens da tabela na JTable
     
     public List<PecasDTO> listarPecas() {
    List<PecasDTO> Pecas = new ArrayList<>();
    String sql = "SELECT * FROM pecas"; // Altere para o nome correto da sua tabela

    try {
        Connection conexao = ConexaoDAO.conector();
        PreparedStatement pst = conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            PecasDTO objPecasDTO = new PecasDTO();
            objPecasDTO.setIdPeca(rs.getInt("id_peca")); // Altere conforme o nome das colunas
            objPecasDTO.setNome(rs.getString("nome"));
            objPecasDTO.setTipo(rs.getString("tipo"));
            objPecasDTO.setFabricante(rs.getString("fabricante"));
            objPecasDTO.setQuantidade(rs.getString("quantidade"));
            
           
          
           

            Pecas.add(objPecasDTO);
        }

        rs.close();
        pst.close();
        conexao.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar máquinas: " + e);
    }

    return Pecas;
}
//carregar os itens para o JTable
     public void carregarTabela(JTable tabelaPecas) {
    String sql = "SELECT * FROM pecas";
    Connection conexao = ConexaoDAO.conector();
    DefaultTableModel model = (DefaultTableModel) tabelaPecas.getModel();
    model.setRowCount(0); // Limpa a tabela antes de carregar novos dados

    try {
        PreparedStatement pst = conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            // Supondo que sua tabela 'maquinas' tenha colunas como id, nome, status, etc.
            Object[] row = new Object[]{
                rs.getInt("id_peca"),
                rs.getString("nome"),
                rs.getString("tipo"),
                rs.getString("fabricante"),
                rs.getInt("quantidade"),
             
                // Adicione outras colunas conforme necessário
            };
            model.addRow(row);
        }
        rs.close();
        pst.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao carregar peças: " + e);
    } finally {
        try {
            conexao.close(); // Fechar a conexão após uso
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
     
     
     
     
     //Metodo pesquisar
    public PecasDTO pesquisarUsuario(int idPecas) {
        String sql = "SELECT * FROM pecas WHERE id_peca = ?";
        conexao = new ConexaoDAO().conector();
        PecasDTO PecasDTO = null;

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idPecas);
            rs = pst.executeQuery();

            // Se encontrar um usuário, cria o objeto UsuarioDTO com os dados
            if (rs.next()) {
                PecasDTO = new PecasDTO();
                PecasDTO.setIdPeca(rs.getInt("id_peca"));
                PecasDTO.setNome(rs.getString("nome"));
                PecasDTO.setTipo(rs.getString("tipo"));
                PecasDTO.setFabricante(rs.getString("fabricante"));
                PecasDTO.setQuantidade(rs.getString("quantidade"));
       
            }

            rs.close();
            pst.close();
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar usuário: " + e);
        }

        return PecasDTO;
       
    }
    
        //Metodo inserir/adicionar usuarios
public void inserirUsuario(PecasDTO objPecasDTO) {
    // Atualizando a consulta SQL para não incluir a data_cadastro
    String sql = "INSERT INTO pecas (nome, tipo, fabricante, quantidade) VALUES (?, ?, ?, ?)";
    conexao = new ConexaoDAO().conector();
    
    try {
        pst = conexao.prepareStatement(sql);
        
        // Remove o parâmetro id_usuario e data_cadastro
        // pst.setInt(1, objUsuarioDTO.getIdUsuario()); // Não é mais necessário
        pst.setString(1, objPecasDTO.getNome());
        pst.setString(2, objPecasDTO.getTipo());
        pst.setString(3, objPecasDTO.getFabricante());
        pst.setString(4, objPecasDTO.getQuantidade());
       

        // Executa a inserção
        pst.executeUpdate(); // Use executeUpdate() para inserções
        pst.close();

        // Limpa os campos após a inserção, se necessário
        limpar();
        
        JOptionPane.showMessageDialog(null, "Peça inserida com sucesso!");
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Inserir peça: " + e);
    }
}

      //Metodo editar
    public void editar(PecasDTO objPecasDTO){
        String sql = "update pecas set nome = ?, tipo = ?, fabricante = ?, quantidade = ? where id_peca = ?";
         conexao = ConexaoDAO.conector();
         try {
               pst = conexao.prepareStatement(sql);
               pst.setString(1, objPecasDTO.getNome());
               pst.setString(2, objPecasDTO.getTipo());
               pst.setString(3, objPecasDTO.getFabricante());
               pst.setString(4, objPecasDTO.getQuantidade());
  
                pst.setInt(5, objPecasDTO.getIdPeca());
                
               int add = pst.executeUpdate();
               if (add >0){
                   JOptionPane.showMessageDialog(null, "Peça editada com sucesso!");
                    //pesquisaAuto();
                    conexao.close();
                    limpar();
               }
               
            
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null," Método editar " + e);
        
        }
    }
    
 
     //Metodo Excluir
public void excluir(PecasDTO objPecasDTO) {
    String sql = "DELETE FROM pecas WHERE id_peca = ?";
    conexao = new ConexaoDAO().conector();
    
    try {
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, objPecasDTO.getIdPeca());
        
        int linhasAfetadas = pst.executeUpdate(); // Use executeUpdate para operações de DML

        if (linhasAfetadas > 0) {
            JOptionPane.showMessageDialog(null, "Peça excluída com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Peça não encontrada!");
        }

        pst.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao excluir peça: " + e);
    }
    limpar();
}
    
    
    
    
}
