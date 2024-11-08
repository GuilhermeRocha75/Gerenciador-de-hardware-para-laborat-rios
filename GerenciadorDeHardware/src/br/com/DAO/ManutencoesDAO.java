
package br.com.DAO;

import br.com.DTO.ManutencoesDTO;
import br.com.VIEWS.TelaMaquinasManutencoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ManutencoesDAO {
    
     
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
 
      public void limpar(){
          TelaMaquinasManutencoes.txtIdManutencao.setText(null);
          TelaMaquinasManutencoes.txtIdMaquina.setText(null);
          TelaMaquinasManutencoes.txtResponsavel.setText(null);
          TelaMaquinasManutencoes.BoxTipo.setSelectedIndex(-1);
          TelaMaquinasManutencoes.txtData.setText(null);
          TelaMaquinasManutencoes.txtDescricao.setText(null);
          TelaMaquinasManutencoes.BoxStatus.setSelectedIndex(-1);;
       
       
    }   
  
      
        //Metodo itens da tabela na JTable
     
     public List<ManutencoesDTO> listarManutencoes() {
    List<ManutencoesDTO> Manutencoes = new ArrayList<>();
    String sql = "SELECT * FROM manutencoes"; // Altere para o nome correto da sua tabela

    try {
        Connection conexao = ConexaoDAO.conector();
        PreparedStatement pst = conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            ManutencoesDTO objManutencoesDTO = new ManutencoesDTO();
            objManutencoesDTO.setIdManutencao(rs.getInt("id_manutencao")); // Altere conforme o nome das colunas
            objManutencoesDTO.setIdMaquina(rs.getInt("id_maquina"));
            objManutencoesDTO.setTipo(rs.getString("tipo"));
            objManutencoesDTO.setData(rs.getString("data_manutencao"));
            objManutencoesDTO.setDescricao(rs.getString("descricao"));
            objManutencoesDTO.setStatus(rs.getString("status"));
            objManutencoesDTO.setResponsavel(rs.getString("responsavel"));
          
         

            Manutencoes.add(objManutencoesDTO);
        }

        rs.close();
        pst.close();
        conexao.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar manutenções: " + e);
    }

    return Manutencoes;
}
//carregar os itens para o JTable
     public void carregarTabela(JTable tableManutencao) {
    String sql = "SELECT * FROM manutencoes";
    Connection conexao = ConexaoDAO.conector();
    DefaultTableModel model = (DefaultTableModel) tableManutencao.getModel();
    model.setRowCount(0); // Limpa a tabela antes de carregar novos dados

    try {
        PreparedStatement pst = conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            // Supondo que sua tabela 'maquinas' tenha colunas como id, nome, status, etc.
            Object[] row = new Object[]{
                rs.getInt("id_manutencao"),
                rs.getInt("id_maquina"),
                rs.getString("tipo"),
                rs.getString("data_manutencao"),
                rs.getString("descricao"),
                rs.getString("status"),
                rs.getString("responsavel")
                // Adicione outras colunas conforme necessário
            };
            model.addRow(row);
        }
        rs.close();
        pst.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao carregar manutenções: " + e);
    } finally {
        try {
            conexao.close(); // Fechar a conexão após uso
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
      
      
     //Metodo pesquisar
    public ManutencoesDTO pesquisarUsuario(int idManutencoes) {
        String sql = "SELECT * FROM manutencoes WHERE id_manutencao = ?";
        conexao = new ConexaoDAO().conector();
        ManutencoesDTO ManutencoesDTO = null;

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idManutencoes);
            rs = pst.executeQuery();

            // Se encontrar um usuário, cria o objeto UsuarioDTO com os dados
            if (rs.next()) {
                ManutencoesDTO = new ManutencoesDTO();
                ManutencoesDTO.setIdManutencao(rs.getInt("id_manutencao"));
                ManutencoesDTO.setIdMaquina(rs.getInt("id_maquina"));
                ManutencoesDTO.setTipo(rs.getString("tipo"));
                ManutencoesDTO.setData(rs.getString("data_manutencao"));
                ManutencoesDTO.setDescricao(rs.getString("descricao"));
                ManutencoesDTO.setStatus(rs.getString("status"));
                ManutencoesDTO.setResponsavel(rs.getString("Responsavel"));
            }

            rs.close();
            pst.close();
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar manutenção: " + e);
        }

        return ManutencoesDTO;
       
    }
    
    
        //Metodo inserir/adicionar usuarios
public void inserirUsuario(ManutencoesDTO objManutencoesDTO) {
    // Atualizando a consulta SQL para não incluir a data_cadastro
    String sql = "INSERT INTO manutencoes (id_maquina, tipo, data_manutencao, descricao, status, responsavel) VALUES (?, ?, ?, ?, ?, ?)";

    conexao = new ConexaoDAO().conector();
    
    try {
        pst = conexao.prepareStatement(sql);
        
        // Remove o parâmetro id_usuario e data_cadastro
        // pst.setInt(1, objUsuarioDTO.getIdUsuario()); // Não é mais necessário
        pst.setInt(1, objManutencoesDTO.getIdMaquina());
        pst.setString(2, objManutencoesDTO.getTipo());
        pst.setString(3, objManutencoesDTO.getData());
        pst.setString(4, objManutencoesDTO.getDescricao());
        pst.setString(5, objManutencoesDTO.getStatus());
        pst.setString(6, objManutencoesDTO.getResponsavel());
        

        // Executa a inserção
        pst.executeUpdate(); // Use executeUpdate() para inserções
        pst.close();

        // Limpa os campos após a inserção, se necessário
        limpar();
        
        JOptionPane.showMessageDialog(null, "manutenção inserida com sucesso!");
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Inserir manutenção: " + e);
    }
}
    


      //Metodo editar
    public void editar(ManutencoesDTO objManutencoesDTO){
        String sql = "update manutencoes set id_manutencao = ?, tipo = ?, data_manutencao = ?, descricao = ?, status = ?, responsavel = ? where id_manutencao = ?";
         conexao = ConexaoDAO.conector();
         try {
               pst = conexao.prepareStatement(sql);
               pst.setInt(1, objManutencoesDTO.getIdManutencao());
               pst.setString(2, objManutencoesDTO.getTipo());
               pst.setString(3, objManutencoesDTO.getData());
               pst.setString(4, objManutencoesDTO.getDescricao());
               pst.setString(5, objManutencoesDTO.getStatus());
               pst.setString(6, objManutencoesDTO.getResponsavel());
               
                     pst.setInt(7, objManutencoesDTO.getIdManutencao());
               
                
               int add = pst.executeUpdate();
               if (add >0){
                   
                   JOptionPane.showMessageDialog(null, "Manutenção editada com sucesso!");
                    //pesquisaAuto();
                    conexao.close();
                    limpar();
               }
               
            
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null," Método editar " + e);
        
        }
    }
    
    
     //Metodo Excluir
public void excluir(ManutencoesDTO objManutencoesDTO) {
    String sql = "DELETE FROM manutencoes WHERE id_manutencao = ?";
    conexao = new ConexaoDAO().conector();
    
    try {
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, objManutencoesDTO.getIdManutencao());
        
        int linhasAfetadas = pst.executeUpdate(); // Use executeUpdate para operações de DML

        if (linhasAfetadas > 0) {
            JOptionPane.showMessageDialog(null, "Peça manutenção com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Manutenção não encontrada!");
        }

        pst.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao excluir Manutenção: " + e);
    }
    limpar();
}
    
    
    
}
