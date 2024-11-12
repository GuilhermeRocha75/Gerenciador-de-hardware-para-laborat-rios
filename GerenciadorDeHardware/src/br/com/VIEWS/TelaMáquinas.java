/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.VIEWS;

import br.com.DAO.ConexaoDAO;
import br.com.DAO.MaquinasDAO;
import br.com.DAO.UsuarioDAO;
import br.com.DTO.MaquinasDTO;
import br.com.DTO.UsuarioDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Colégio São Lucas Ulbra <Universidade Luterana do brasil>
 */
public class TelaMáquinas extends javax.swing.JInternalFrame {

    Connection conexao = null;
   PreparedStatement pst = null;
   ResultSet rs = null;
    
    /**
     * Creates new form TelaUsuarios2
     */
    public TelaMáquinas() {
        initComponents();
        conexao = ConexaoDAO.conector();
        
        MaquinasDAO maquinasDAO = new MaquinasDAO();
    maquinasDAO.carregarMaquinasNaTabela(TabelaMaquinas); // onde tabelaMaquinas é o seu JTable
        
        
        
    }

     
    
   
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        btnIncluir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();
        btnPesquisar = new javax.swing.JButton();
        txtIdMaquina = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtCPU = new javax.swing.JTextField();
        lblRAM = new javax.swing.JLabel();
        lblArmazenamento = new javax.swing.JLabel();
        btnLimpar = new javax.swing.JButton();
        txtArmazenamento = new javax.swing.JTextField();
        btnExcluir1 = new javax.swing.JButton();
        txtNome = new javax.swing.JTextField();
        lblCPU = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtIdLab = new javax.swing.JTextField();
        lblData = new javax.swing.JLabel();
        BoxStaus = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelaMaquinas = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtRAM = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID máquina", "ID Laboratório", "Nome", "CPU", "RAM", "Armazenamento", "Data de aquisição", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setText("Máquinas Cadastradas:");

        setClosable(true);

        btnIncluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/iconeAdd.png"))); // NOI18N
        btnIncluir.setText("incluir");
        btnIncluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIncluirActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("ID Máquina:");

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/iconeEditar.png"))); // NOI18N
        btnEditar.setText("editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/iconePesquisar.png"))); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        txtIdMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdMaquinaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Nome:");

        lblRAM.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblRAM.setText("RAM:");

        lblArmazenamento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblArmazenamento.setText("Armazenamento:");

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/iconeLimpar.png"))); // NOI18N
        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        txtArmazenamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtArmazenamentoActionPerformed(evt);
            }
        });

        btnExcluir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/IconeExcluir.png"))); // NOI18N
        btnExcluir1.setText("excluir");
        btnExcluir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluir1ActionPerformed(evt);
            }
        });

        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });

        lblCPU.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblCPU.setText("CPU:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("ID Laboratório:");

        txtIdLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdLabActionPerformed(evt);
            }
        });

        lblData.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lblData.setText("Status:");

        BoxStaus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Funcionando", "Em manutenção", "Fora de uso" }));
        BoxStaus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxStausActionPerformed(evt);
            }
        });

        TabelaMaquinas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID máquina", "ID Laboratório", "Nome", "CPU", "RAM", "Armazenamento", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(TabelaMaquinas);

        jLabel4.setText("Máquinas Cadastradas:");

        txtRAM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRAMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblRAM)
                                    .addComponent(lblCPU)
                                    .addComponent(lblArmazenamento))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCPU, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addComponent(txtArmazenamento)
                                    .addComponent(txtRAM)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIdMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtIdLab, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(121, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(207, 207, 207))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnPesquisar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnLimpar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnIncluir))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(btnEditar)
                                .addGap(18, 18, 18)
                                .addComponent(btnExcluir1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblData)
                                .addGap(30, 30, 30)
                                .addComponent(BoxStaus, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(120, 120, 120)))
                        .addGap(45, 45, 45))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtIdMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtIdLab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCPU)
                    .addComponent(txtCPU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblRAM)
                    .addComponent(txtRAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblArmazenamento)
                    .addComponent(txtArmazenamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblData)
                    .addComponent(BoxStaus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIncluir, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExcluir1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIncluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIncluirActionPerformed
                                          
    try {
        if (txtIdMaquina.getText().isEmpty() 
                || txtIdLab.getText().isEmpty()
                ||  txtNome.getText().isEmpty() 
                || txtCPU.getText().isEmpty()
                || txtRAM.getText().isEmpty()
                || txtArmazenamento.getText().isEmpty()
                || BoxStaus.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos obrigatórios!");
            return;
        }

        // Obtém os dados dos campos
        
        /*String id_maquina = txtIdMaquina.getText();
        String id_lab = txtIdLab.getText();*/
        
        String nome_maquina = txtNome.getText();
        String cpu_maquina = txtCPU.getText();
        String ram_maquina = txtRAM.getText();
        String armazenamneto_maquina = txtArmazenamento.getText();
        String status_maquina = BoxStaus.getSelectedItem().toString();

        
        // Criação do objeto UsuarioDTO
       MaquinasDTO objMaquinasDTO = new MaquinasDTO();
        
        /*objMaquin asDTO.setIdMaquina(id_maquina);
        objMaquinasDTO.setIdLab(id_lab);*/
        
        objMaquinasDTO.setNome(nome_maquina);
        objMaquinasDTO.setCpu(cpu_maquina);
        objMaquinasDTO.setRam(ram_maquina);
        objMaquinasDTO.setArmazenamento(armazenamneto_maquina);
        objMaquinasDTO.setStatus(status_maquina);
        
        // Instância do UsuarioDAO para inserir
        MaquinasDAO objMaquinasDAO = new MaquinasDAO();
        objMaquinasDAO.inserirUsuario(objMaquinasDTO);

        // Limpa os campos após a inserção
        objMaquinasDAO.limpar();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao incluir usuário: " + e.getMessage());
    }

        
      

        
    }//GEN-LAST:event_btnIncluirActionPerformed

    private void txtIdMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdMaquinaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdMaquinaActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        //metodo botao limpar
          
       MaquinasDAO objMaquinasDAO = new MaquinasDAO();       
            objMaquinasDAO.limpar();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void txtArmazenamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtArmazenamentoActionPerformed

        // TODO add your handling code here:
    }//GEN-LAST:event_txtArmazenamentoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
          // TODO add your handling code here:
 int id_lab = Integer.parseInt(txtIdLab.getText());
    String nome_maquina = txtNome.getText();
    String cpu_maquina = txtCPU.getText();
    String ram_maquina = txtRAM.getText();
    String armazenamento_maquina = txtArmazenamento.getText();
    String status_maquina = BoxStaus.getSelectedItem().toString();
    int id_maquina = Integer.parseInt(txtIdMaquina.getText()); // ID da máquina para a condição WHERE

    // Criação do objeto MaquinasDTO
    MaquinasDTO objMaquinasDTO = new MaquinasDTO();
    objMaquinasDTO.setIdLab(id_lab);
    objMaquinasDTO.setNome(nome_maquina);
    objMaquinasDTO.setCpu(cpu_maquina);
    objMaquinasDTO.setRam(ram_maquina);
    objMaquinasDTO.setArmazenamento(armazenamento_maquina);
    objMaquinasDTO.setStatus(status_maquina);
    objMaquinasDTO.setIdMaquina(id_maquina); // Setar o id_maquina para o WHERE

    // Instância do MaquinasDAO e chamada ao método editar
    MaquinasDAO objMaquinasDAO = new MaquinasDAO();
    objMaquinasDAO.editar(objMaquinasDTO);
        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluir1ActionPerformed
    // TODO add your handling code here:
String idMaquina = txtIdMaquina.getText();

if (idMaquina.trim().isEmpty()) {
    JOptionPane.showMessageDialog(null, "O campo ID não pode estar vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
} else {
    MaquinasDTO objMaquinasDTO = new MaquinasDTO();
    objMaquinasDTO.setIdMaquina(Integer.parseInt(idMaquina)); // Altere para setIdUsuario se necessário

    MaquinasDAO objMaquinasDAO = new MaquinasDAO();
    objMaquinasDAO.excluir(objMaquinasDTO); // Chama o método de exclusão

}
    }//GEN-LAST:event_btnExcluir1ActionPerformed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        // Chamada do metodo Pesquisar

        // Obtém o ID do campo de texto
        int idMaquinas = Integer.parseInt(txtIdMaquina.getText());

        // Cria uma instância do DAO
        MaquinasDAO MaquinasDAO = new MaquinasDAO();
        

        // Chama o método pesquisar e obtém o resultado
        MaquinasDTO MaquinasDTO = MaquinasDAO.pesquisarMaquinas(idMaquinas);

        if (MaquinasDTO != null) {
            // Preenche os campos com os dados do usuário encontrado
           
             txtIdLab.setText(String.valueOf(MaquinasDTO.getIdLab())); // Converte o ID para String
            txtNome.setText(MaquinasDTO.getNome());
            txtCPU.setText(MaquinasDTO.getCpu());
            txtRAM.setText(MaquinasDTO.getRam());
            txtArmazenamento.setText(MaquinasDTO.getArmazenamento());
              BoxStaus.setSelectedItem(MaquinasDTO.getStatus());
        } else {
            // Mostra uma mensagem de erro se o usuário não for encontrado
            JOptionPane.showMessageDialog(null, "Usuário não cadastrado!");

            //metodo para apagar os campos
            MaquinasDAO objMaquinasDAO = new MaquinasDAO();
            objMaquinasDAO.limpar();
        }
    }//GEN-LAST:event_btnPesquisarActionPerformed

    private void txtIdLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdLabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdLabActionPerformed

    private void BoxStausActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxStausActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BoxStausActionPerformed

    private void txtRAMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRAMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRAMActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox<String> BoxStaus;
    private javax.swing.JTable TabelaMaquinas;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir1;
    private javax.swing.JButton btnIncluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblArmazenamento;
    private javax.swing.JLabel lblCPU;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblRAM;
    public static javax.swing.JTextField txtArmazenamento;
    public static javax.swing.JTextField txtCPU;
    public static javax.swing.JTextField txtIdLab;
    public static javax.swing.JTextField txtIdMaquina;
    public static javax.swing.JTextField txtNome;
    public static javax.swing.JTextField txtRAM;
    // End of variables declaration//GEN-END:variables
}
