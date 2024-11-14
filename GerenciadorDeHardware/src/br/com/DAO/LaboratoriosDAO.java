
package br.com.DAO;

import br.com.DTO.LaboratorioDTO;
import br.com.VIEWS.TelaLaboratorios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class LaboratoriosDAO {
       Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
 
      public void limpar(){
          TelaLaboratorios.txtIdLab.setText(null);
          TelaLaboratorios.txtLocalizacaoLab.setText(null);
          TelaLaboratorios.txtNomeLab.setText(null);
        
    }   
      
      
      
        //Metodo itens da tabela na JTable
     
     public List<LaboratorioDTO> listarManutencoes() {
    List<LaboratorioDTO> Lab = new ArrayList<>();
    String sql = "SELECT * FROM laboratorios"; // Altere para o nome correto da sua tabela

    try {
        Connection conexao = ConexaoDAO.conector();
        PreparedStatement pst = conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            LaboratorioDTO objLabDTO = new LaboratorioDTO();
            
            objLabDTO.setIdLab(rs.getInt("id_laboratorio"));
            objLabDTO.setLocalizacao(rs.getString("localizacao"));
            objLabDTO.setNome(rs.getString("nome"));
          
            Lab.add(objLabDTO);
        }

        rs.close();
        pst.close();
        conexao.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar laboratórios: " + e);
    }

    return Lab;
}

//carregar os itens para o JTable
     public void carregarTabela(JTable tableLab) {
    String sql = "SELECT * FROM laboratorios";
    Connection conexao = ConexaoDAO.conector();
    DefaultTableModel model = (DefaultTableModel) tableLab.getModel();
    model.setRowCount(0); // Limpa a tabela antes de carregar novos dados

    try {
        PreparedStatement pst = conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            // Supondo que sua tabela 'maquinas' tenha colunas como id, nome, status, etc.
            Object[] row = new Object[]{
                rs.getInt("id_laboratorio"),
                rs.getString("nome"),
                rs.getString("localizacao"),
          
              
                // Adicione outras colunas conforme necessário
            };
            model.addRow(row);
        }
        rs.close();
        pst.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao carregar laboratórios: " + e);
    } finally {
        try {
            conexao.close(); // Fechar a conexão após uso
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
    
    
     //Metodo pesquisar
    public LaboratorioDTO pesquisarUsuario(int idLabs) {
        String sql = "SELECT * FROM laboratorios WHERE id_laboratorio = ?";
        conexao = new ConexaoDAO().conector();
        LaboratorioDTO LabDTO = null;

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idLabs);
            rs = pst.executeQuery();

            // Se encontrar um usuário, cria o objeto UsuarioDTO com os dados
            if (rs.next()) {
                LabDTO = new LaboratorioDTO();
                
                
                LabDTO.setIdLab(rs.getInt("id_laboratorio"));
                LabDTO.setNome(rs.getString("nome"));
                LabDTO.setLocalizacao(rs.getString("localizacao"));
              
            }

            rs.close();
            pst.close();
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar laboratórios: " + e);
        }

        return LabDTO;
       
    }
    //Metodo inserir/adicionar usuarios
public void inserirUsuario(LaboratorioDTO objLabDTO) {
    // Atualizando a consulta SQL para não incluir a data_cadastro
    String sql = "INSERT INTO laboratorios (id_laboratorio, nome, localizacao) VALUES (?, ?, ?)";
    conexao = new ConexaoDAO().conector();
    
    try {
        pst = conexao.prepareStatement(sql);
        
        // Remove o parâmetro id_usuario e data_cadastro
        // pst.setInt(1, objUsuarioDTO.getIdUsuario()); // Não é mais necessário
        pst.setInt(1, objLabDTO.getIdLab());
        pst.setString(2, objLabDTO.getNome());
        pst.setString(3, objLabDTO.getLocalizacao());

       
       

        // Executa a inserção
        pst.executeUpdate(); // Use executeUpdate() para inserções
        pst.close();

        // Limpa os campos após a inserção, se necessário
        limpar();
        
        JOptionPane.showMessageDialog(null, "Laboratórios inserido com sucesso!");
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Inserir laboratório: " + e);
    }
}

      //Metodo editar
    public void editar(LaboratorioDTO objLabDTO){
        String sql = "update laboratorios set nome = ?,localizacao = ? where id_laboratorio = ?";
         conexao = ConexaoDAO.conector();
         try {
               pst = conexao.prepareStatement(sql);
              
               pst.setString(1, objLabDTO.getNome());
               pst.setString(2, objLabDTO.getLocalizacao());
              
              pst.setInt(3, objLabDTO.getIdLab());
                
               int add = pst.executeUpdate();
               if (add >0){
                   JOptionPane.showMessageDialog(null, "Laboratório editado com sucesso!");
                 
                    conexao.close();
                    limpar();
               }
               
            
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null," Método editar " + e);
        
        }
    }
     
    //Metodo Excluir
public void excluir(LaboratorioDTO objLabDTO) {
    String sql = "DELETE FROM laboratorios WHERE id_laboratorio = ?";
    conexao = new ConexaoDAO().conector();
    
    try {
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, objLabDTO.getIdLab());
        
        int linhasAfetadas = pst.executeUpdate(); // Use executeUpdate para operações de DML

        if (linhasAfetadas > 0) {
            JOptionPane.showMessageDialog(null, "Laboratório excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Laboratórios não encontrado!");
        }

        pst.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao excluir laboratório: " + e);
    }
    limpar();
}
     
 

}
