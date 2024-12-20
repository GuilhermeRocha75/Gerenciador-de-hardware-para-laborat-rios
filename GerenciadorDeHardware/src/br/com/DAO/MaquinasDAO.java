
package br.com.DAO;

import br.com.DTO.MaquinasDTO;
import br.com.VIEWS.TelaMáquinas;
import br.com.VIEWS.TelaUsuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class MaquinasDAO {
    
    
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
         TelaMáquinas.txtRAM.setText(null);
         TelaMáquinas.txtCPU.setText(null);
         TelaMáquinas.txtIdLab.setText(null);
         TelaMáquinas.txtIdMaquina.setText(null);
         TelaMáquinas.txtNome.setText(null);
         TelaMáquinas.txtArmazenamento.setText(null);
         TelaMáquinas.BoxStaus.setSelectedIndex(-1);
    }
    
     
     
     
    //Metodo itens da tabela na JTable
     
     public List<MaquinasDTO> listarMaquinas() {
    List<MaquinasDTO> maquinas = new ArrayList<>();
    String sql = "SELECT * FROM maquinas"; // Altere para o nome correto da sua tabela

    try {
        Connection conexao = ConexaoDAO.conector();
        PreparedStatement pst = conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            MaquinasDTO maquina = new MaquinasDTO();
            maquina.setIdMaquina(rs.getInt("id_maquina")); // Altere conforme o nome das colunas
            maquina.setIdLab(rs.getInt("id_laboratorio"));
            maquina.setNome(rs.getString("nome"));
            maquina.setCpu(rs.getString("cpu"));
            maquina.setRam(rs.getString("ram"));
            maquina.setArmazenamento(rs.getString("armazenamento"));
            maquina.setStatus(rs.getString("status"));
           

            maquinas.add(maquina);
        }

        rs.close();
        pst.close();
        conexao.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar máquinas: " + e);
    }

    return maquinas;
}
//carregar os itens para o JTable
     public void carregarMaquinasNaTabela(JTable tabela) {
    String sql = "SELECT * FROM maquinas";
    Connection conexao = ConexaoDAO.conector();
    DefaultTableModel model = (DefaultTableModel) tabela.getModel();
    model.setRowCount(0); // Limpa a tabela antes de carregar novos dados

    try {
        PreparedStatement pst = conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            // Supondo que sua tabela 'maquinas' tenha colunas como id, nome, status, etc.
            Object[] row = new Object[]{
                rs.getInt("id_maquina"),
                rs.getInt("id_laboratorio"),
                rs.getString("nome"),
                rs.getString("cpu"),
                rs.getString("ram"),
                rs.getString("armazenamento"),
                rs.getString("status")
                // Adicione outras colunas conforme necessário
            };
            model.addRow(row);
        }
        rs.close();
        pst.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao carregar máquinas: " + e);
    } finally {
        try {
            conexao.close(); // Fechar a conexão após uso
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

      //Metodo pesquisar
    public MaquinasDTO pesquisarMaquinas(int idMaquinas) {
        String sql = "SELECT * FROM Maquinas WHERE id_maquina = ?";
        conexao = new ConexaoDAO().conector();
        MaquinasDTO MaquinasDTO = null;

        try {
            pst = conexao.prepareStatement(sql);
            pst.setInt(1, idMaquinas);
            rs = pst.executeQuery();

            // Se encontrar um usuário, cria o objeto UsuarioDTO com os dados
            if (rs.next()) {
                MaquinasDTO = new MaquinasDTO();
                MaquinasDTO.setIdMaquina(rs.getInt("id_Maquina"));
                MaquinasDTO.setIdLab(rs.getInt("id_Laboratorio"));
                MaquinasDTO.setNome(rs.getString("nome"));
                MaquinasDTO.setCpu(rs.getString("cpu"));
                MaquinasDTO.setRam(rs.getString("ram"));
                MaquinasDTO.setArmazenamento(rs.getString("armazenamento"));
                MaquinasDTO.setStatus(rs.getString("status"));
            }

            rs.close();
            pst.close();
            

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao pesquisar máquina: " + e);
        }

        return MaquinasDTO;
       
    }
     
    
    //Metodo inserir/adicionar usuarios
public void inserirUsuario(MaquinasDTO objMaquinasDTO) {
    // Atualizando a consulta SQL para não incluir a data_cadastro
    String sql = "INSERT INTO maquinas (id_laboratorio, nome, cpu, ram,armazenamento,status) VALUES (?, ?, ?, ?, ?, ?)";
    conexao = new ConexaoDAO().conector();
    
    try {
        pst = conexao.prepareStatement(sql);
        
        // Remove o parâmetro id_usuario e data_cadastro
        // pst.setInt(1, objUsuarioDTO.getIdUsuario()); // Não é mais necessário
        pst.setInt(1, objMaquinasDTO.getIdLab());
        pst.setString(2, objMaquinasDTO.getNome());
        pst.setString(3, objMaquinasDTO.getCpu());
        pst.setString(4, objMaquinasDTO.getRam()); 
        pst.setString(5, objMaquinasDTO.getArmazenamento()); 
        pst.setString(6, objMaquinasDTO.getStatus()); 

        // Executa a inserção
        pst.executeUpdate(); // Use executeUpdate() para inserções
        pst.close();

        // Limpa os campos após a inserção, se necessário
        limpar();
        
        JOptionPane.showMessageDialog(null, "Máquina inserida com sucesso!");
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Inserir máquina: " + e);
    }
}

     //Metodo editar
    public void editar(MaquinasDTO objUMaquinasDTO){
        String sql = "update maquinas set id_laboratorio = ?, nome = ?, cpu = ?, ram = ?, armazenamento = ?, status = ? where id_maquina = ?";
         conexao = ConexaoDAO.conector();

         try {
               pst = conexao.prepareStatement(sql);
               pst.setInt(1, objUMaquinasDTO.getIdLab());
               pst.setString(2, objUMaquinasDTO.getNome());
               pst.setString(3, objUMaquinasDTO.getCpu());
               pst.setString(4, objUMaquinasDTO.getRam());
               pst.setString(5, objUMaquinasDTO.getArmazenamento());
               pst.setString(6, objUMaquinasDTO.getStatus());
               pst.setInt(7, objUMaquinasDTO.getIdMaquina());
             
                
               int add = pst.executeUpdate();
               if (add >0){
                   JOptionPane.showMessageDialog(null, "Máquina editada com sucesso!");
                    conexao.close();
                    limpar();
               }
               
            
        } catch (Exception e) {
        JOptionPane.showMessageDialog(null," Método editar " + e);
        
        }
    }
    
        //Metodo Excluir
public void excluir(MaquinasDTO objMaquinasDTO) {
    String sql = "DELETE FROM maquinas WHERE id_maquina = ?";
    conexao = new ConexaoDAO().conector();
    
    try {
        pst = conexao.prepareStatement(sql);
        pst.setInt(1, objMaquinasDTO.getIdMaquina());
        
        int linhasAfetadas = pst.executeUpdate(); // Use executeUpdate para operações de DML

        if (linhasAfetadas > 0) {
            JOptionPane.showMessageDialog(null, "Máquina excluída com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Máquina não encontrada!");
        }

        pst.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao excluir a máquina: " + e);
    }
    limpar();
}
    
     
     
}
