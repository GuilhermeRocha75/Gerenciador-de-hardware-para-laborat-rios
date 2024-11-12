/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.DAO;


import br.com.DTO.MaquinasDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class TelaPrincipalDAO {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    
     
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
    
}
