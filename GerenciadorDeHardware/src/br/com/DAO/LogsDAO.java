/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.DAO;
import br.com.DTO.LogsDTO;

import com.mysql.cj.log.Log;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LogsDAO {

    
    
    
    // Método para carregar os logs de ações do banco de dados
    public List<LogsDTO> carregarLogs() {  // Método para carregar logs
        List<LogsDTO> logs = new ArrayList<>();  // Usando LogsDTO como tipo da lista
        String sql = "SELECT l.id_log, u.nome AS usuario, l.acao, l.data_hora " +
                     "FROM logs_acoes l " +
                     "JOIN usuarios u ON l.id_usuario = u.id_usuario";
        
        try (Connection con = ConexaoDAO.conector();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                LogsDTO log = new LogsDTO();  // Criando um objeto LogsDTO
                log.setIdLog(rs.getInt("id_log"));
                log.setIdUsuario(rs.getInt("id_usuario"));
                log.setAcao(rs.getString("acao"));
                log.setDataHora(rs.getTimestamp("data_hora"));
                logs.add(log);  // Adicionando o objeto LogsDTO à lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return logs;  // Retorna a lista de LogsDTO
    }
    
   // Método para obter os logs de ações
    public List<LogsDTO> obterLogs() {  // Altere de Log para LogsDTO
        List<LogsDTO> logs = new ArrayList<>();  // Usando LogsDTO como tipo da lista
        String sql = "SELECT l.id_log, u.nome AS usuario, l.acao, l.data_hora " +
                     "FROM logs_acoes l " +
                     "JOIN usuarios u ON l.id_usuario = u.id_usuario";
        
        try (Connection con = ConexaoDAO.conector();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            
            while (rs.next()) {
                LogsDTO log = new LogsDTO();  // Usando LogsDTO para o objeto
                log.setIdLog(rs.getInt("id_log"));
                log.setIdUsuario(rs.getInt("id_usuario"));
                log.setAcao(rs.getString("acao"));
                log.setDataHora(rs.getTimestamp("data_hora"));
                logs.add(log);  // Adicionando o objeto LogsDTO à lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return logs;  // Retorna a lista de LogsDTO
    }


 //Metodo itens da tabela na JTable
     
     public List<LogsDTO> listarManutencoes() {
    List<LogsDTO> Manutencoes = new ArrayList<>();
    String sql = "SELECT * FROM logs_acoes"; // Altere para o nome correto da sua tabela

    try {
        Connection conexao = ConexaoDAO.conector();
        PreparedStatement pst = conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            LogsDTO objManutencoesDTO = new LogsDTO();
            
            objManutencoesDTO.setIdLog(rs.getInt("id_log"));
            objManutencoesDTO.setIdUsuario(rs.getInt("id_usuario")); 
            objManutencoesDTO.setAcao(rs.getString("acao"));
            objManutencoesDTO.setDataHora(rs.getTimestamp("data_hora"));
           
          
         

            Manutencoes.add(objManutencoesDTO);
        }

        rs.close();
        pst.close();
        conexao.close();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar relatorios: " + e);
    }

    return Manutencoes;
}
//carregar os itens para o JTable
     public void carregarTabela(JTable tableRelatorios) {
    String sql = "SELECT * FROM logs_acoes";
    Connection conexao = ConexaoDAO.conector();
    DefaultTableModel model = (DefaultTableModel) tableRelatorios.getModel();
    model.setRowCount(0); // Limpa a tabela antes de carregar novos dados

    try {
        PreparedStatement pst = conexao.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            // Supondo que sua tabela 'maquinas' tenha colunas como id, nome, status, etc.
            Object[] row = new Object[]{
                rs.getInt("id_log"),
                rs.getInt("id_usuario"),
                rs.getString("acao"),
                rs.getTimestamp("data_hora"),
              
                // Adicione outras colunas conforme necessário
            };
            model.addRow(row);
        }
        rs.close();
        pst.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao carregar as Relatorios: " + e);
    } finally {
        try {
            conexao.close(); // Fechar a conexão após uso
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



}


