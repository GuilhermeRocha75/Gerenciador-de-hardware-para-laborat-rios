
package br.com.DAO;



import br.com.DTO.PecasManutencaoDTO;
import br.com.VIEWS.TelaPecasManutencao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class PecasManutencaoDAO {
  
      
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
 
      public void limpar(){
          TelaPecasManutencao.txtIdPecaManutencao.setText(null);
          TelaPecasManutencao.txtIdManutencao.setText(null);
          TelaPecasManutencao.txtIdpeca.setText(null);
          TelaPecasManutencao.txtQuantidade.setText(null);  
    }   
      
      
      
        //Metodo itens da tabela na JTable
     
     public List<PecasManutencaoDTO> listarManutencoes() {
    List<PecasManutencaoDTO> Manutencoes = new ArrayList<>();
    String sql = "SELECT * FROM pecas_manutencao"; // Altere para o nome correto da sua tabela

    try {
        Connection conexao = ConexaoDAO.conector();
        PreparedStatement pst = conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            PecasManutencaoDTO objManutencoesDTO = new PecasManutencaoDTO();
            
            objManutencoesDTO.setIdPecaManutencao(rs.getInt("id_peca_manutencao"));
            objManutencoesDTO.setIdManutencao(rs.getInt("id_manutencao")); 
            objManutencoesDTO.setIdPeca(rs.getInt("id_peca"));
            objManutencoesDTO.setQuantidade(rs.getInt("quantidade"));
           
          
         

            Manutencoes.add(objManutencoesDTO);
        }

        rs.close();
        pst.close();
        conexao.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar peças de manutenções: " + e);
    }

    return Manutencoes;
}
//carregar os itens para o JTable
     public void carregarTabela(JTable tablePecasDeManutancao) {
    String sql = "SELECT * FROM pecas_manutencao";
    Connection conexao = ConexaoDAO.conector();
    DefaultTableModel model = (DefaultTableModel) tablePecasDeManutancao.getModel();
    model.setRowCount(0); // Limpa a tabela antes de carregar novos dados

    try {
        PreparedStatement pst = conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            // Supondo que sua tabela 'maquinas' tenha colunas como id, nome, status, etc.
            Object[] row = new Object[]{
                rs.getInt("id_peca_manutencao"),
                rs.getInt("id_manutencao"),
                rs.getInt("id_peca"),
                rs.getInt("quantidade"),
              
                // Adicione outras colunas conforme necessário
            };
            model.addRow(row);
        }
        rs.close();
        pst.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao carregar as peças de manutenção: " + e);
    } finally {
        try {
            conexao.close(); // Fechar a conexão após uso
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    
    
     //Metodo pesquisar
    public PecasManutencaoDTO pesquisarUsuario(int idPecasManu) {
        String sql = "SELECT * FROM pecas_manutencao WHERE id_peca_manutencao = ?";
        conexao = new ConexaoDAO().conector();
        PecasManutencaoDTO PecasDTO = null;

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idPecasManu);
            rs = pst.executeQuery();

            // Se encontrar um usuário, cria o objeto UsuarioDTO com os dados
            if (rs.next()) {
                PecasDTO = new PecasManutencaoDTO();
                PecasDTO.setIdPecaManutencao(rs.getInt("id_peca_manutencao"));
                PecasDTO.setIdManutencao(rs.getInt("id_manutencao"));
                PecasDTO.setIdPeca(rs.getInt("id_peca"));
                PecasDTO.setQuantidade(rs.getInt("quantidade"));
               
            }

            rs.close();
            pst.close();
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar peça de manutencão: " + e);
        }

        return PecasDTO;
       
    }
    //Metodo inserir/adicionar usuarios
public void inserirUsuario(PecasManutencaoDTO objPecasDTO) {
    // Atualizando a consulta SQL para não incluir a data_cadastro
    String sql = "INSERT INTO pecas_manutencao (id_manutencao, id_peca, quantidade) VALUES (?, ?, ?)";
    conexao = new ConexaoDAO().conector();
    
    try {
        pst = conexao.prepareStatement(sql);
        
        // Remove o parâmetro id_usuario e data_cadastro
        // pst.setInt(1, objUsuarioDTO.getIdUsuario()); // Não é mais necessário
        pst.setInt(1, objPecasDTO.getIdManutencao());
        pst.setInt(2, objPecasDTO.getIdPeca());
        pst.setInt(3, objPecasDTO.getQuantidade());
       
       
       

        // Executa a inserção
        pst.executeUpdate(); // Use executeUpdate() para inserções
        pst.close();

        // Limpa os campos após a inserção, se necessário
        limpar();
        
        JOptionPane.showMessageDialog(null, "Peça de manutenção inserida com sucesso!");
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Inserir peça de manutenção: " + e);
    }
}

      //Metodo editar
    public void editar(PecasManutencaoDTO objPecasDTO){
        String sql = "update pecas_manutencao set id_manutencao = ?, id_peca = ?, quantidade = ? where id_peca_manutencao = ?";
         conexao = ConexaoDAO.conector();
         try {
               pst = conexao.prepareStatement(sql);
               pst.setInt(1, objPecasDTO.getIdManutencao());
               pst.setInt(2, objPecasDTO.getIdPeca());
               pst.setInt(3, objPecasDTO.getQuantidade());
              
                pst.setInt(4, objPecasDTO.getIdPecaManutencao());
                
               int add = pst.executeUpdate();
               if (add >0){
                   JOptionPane.showMessageDialog(null, "Peça de manutenção editada com sucesso!");
                 
                    conexao.close();
                    limpar();
               }
               
            
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null," Método editar " + e);
        
        }
    }
     
    //Metodo Excluir
public void excluir(PecasManutencaoDTO objPecasDTO) {
    String sql = "DELETE FROM pecas_manutencao WHERE id_peca_manutencao = ?";
    conexao = new ConexaoDAO().conector();
    
    try {
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, objPecasDTO.getIdPecaManutencao());
        
        int linhasAfetadas = pst.executeUpdate(); // Use executeUpdate para operações de DML

        if (linhasAfetadas > 0) {
            JOptionPane.showMessageDialog(null, "Peça de manutenção excluída com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Peça de manutenção não encontrada!");
        }

        pst.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao excluir peça de manutenção: " + e);
    }
    limpar();
}
     
    
}
