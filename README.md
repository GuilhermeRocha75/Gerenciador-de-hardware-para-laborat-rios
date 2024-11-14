# Passos para o desenvolvimento do projeto



## **Documento de Levantamento de Requisitos**

**Projeto**: Gerenciador de Hardware para Laboratórios

**Prof:** Éder Oliveira de Rosso

**Data**: 24/10/2024

**Responsáveis:**: 

<div align="center">
  <table>
    <tr>
      <td align="center">
        <a href="https://github.com/GuilhermeRocha75">
          <img src="https://avatars.githubusercontent.com/u/132854835?v=4" width="75px;" alt="Foto de Guilherme Rocha"/>
          <br />
          <sub><b>Guilherme Rocha</b></sub>
        </a>
      </td>
      <td align="center">
        <a href="https://github.com/RenanIsidoro08">
          <img src="https://avatars.githubusercontent.com/u/170661990?v=4" width="75px;" alt="Foto de Renan Isidoro"/>
          <br />
          <sub><b>Renan Isidoro</b></sub>
        </a>
      </td>
    </tr>
  </table>
</div>

---

### **1. Introdução**

#### **1.1. Objetivo**

O objetivo deste documento é detalhar os requisitos funcionais e não funcionais do sistema "Gerenciador de Hardware para Laboratórios", que permitirá o controle e monitoramento de máquinas, consertos, peças trocadas e inventário de componentes em um ou mais laboratórios.

#### **1.2. Escopo**

O sistema gerenciará:

- Cadastro e controle de máquinas de diversos laboratórios.
- Registro de manutenções, consertos e peças trocadas.

#### **1.3. Definições, Acrônimos e Abreviações**

- **Máquina**: 
- **Peça**: 
- **Conserto**: 
- **Inventário**: 

---

### **2. Requisitos Funcionais**

#### **2.1. Cadastro de Máquinas**

- **RF-01**: O sistema deve permitir o cadastro de máquinas, incluindo suas especificações de hardware (CPU, RAM, armazenamento).
- **RF-02**: O sistema deve permitir a associação de cada máquina a um laboratório específico.
- **RF-03**: O sistema deve exibir o status atual da máquina (funcionando, em manutenção, fora de uso).

#### **2.2. Controle de Consertos e Manutenções**

- **RF-04**: O sistema deve permitir registrar manutenções corretivas e preventivas realizadas em uma máquina.
- **RF-05**: O sistema deve permitir associar peças utilizadas no conserto à respectiva manutenção.
- **RF-06**: O sistema deve armazenar um histórico completo de todas as manutenções realizadas em cada máquina.
- **RF-07**: O sistema deve permitir a criação de agendamentos de manutenções preventivas para as máquinas.

#### **2.3. Controle de Peças e Inventário**

- **RF-08**: O sistema deve permitir o cadastro de peças de hardware no inventário, incluindo informações como tipo, fabricante e quantidade em estoque.
- **RF-09**: O sistema deve registrar as peças retiradas do estoque para serem utilizadas em consertos.
- **RF-10**: O sistema deve emitir alertas quando uma peça estiver com baixa quantidade no estoque.

#### **2.4. Gerenciamento de Laboratórios**

- **RF-11**: O sistema deve permitir gerenciar múltiplos laboratórios, associando máquinas e peças a seus respectivos locais.
- **RF-12**: O sistema deve exibir um painel geral que mostre o status das máquinas em todos os laboratórios.

#### **2.5. Relatórios e Auditorias**

- **RF-13**: O sistema deve gerar relatórios de consertos e manutenções realizados em um período definido.
- **RF-14**: O sistema deve gerar relatórios sobre o uso e troca de peças ao longo do tempo.
- **RF-15**: O sistema deve permitir a exportação de relatórios em formatos como PDF ou Excel.

#### **2.6. Controle de Acesso e Usuários**

- **RF-16**: O sistema deve permitir a criação de perfis de usuário com diferentes níveis de acesso (técnicos, administradores).
- **RF-17**: O sistema deve manter logs de todas as ações realizadas pelos usuários.

---

### **3. Requisitos Não Funcionais**

#### **3.1. Desempenho**

- **RNF-01**: O sistema deve ser capaz de processar e exibir informações de até 1000 máquinas e peças de hardware sem perda significativa de desempenho.

#### **3.2. Usabilidade**

- **RNF-02**: O sistema deve possuir uma interface intuitiva, facilitando o registro de manutenções e o gerenciamento de inventário por técnicos.

#### **3.3. Confiabilidade**

- **RNF-03**: O sistema deve manter backup automático dos dados cadastrados a cada 24 horas.

#### **3.4. Portabilidade**

- **RNF-04**: O sistema deve ser multiplataforma, funcionando em ambientes Windows, Linux e macOS.

#### **3.5. Segurança**

- **RNF-05**: O sistema deve garantir o controle de acesso por meio de autenticação de usuário.
- **RNF-06**: Todas as ações dos usuários devem ser registradas para auditoria futura.

---

### **4. Requisitos de Interface**

#### **4.1. Interface Gráfica**

- **RI-01**: O sistema deve ter uma interface gráfica baseada em JavaFX ou Swing.
- **RI-02**: O sistema deve exibir gráficos de desempenho de máquinas e peças através de bibliotecas como JFreeChart.

#### **4.2. Relatórios**

- **RI-03**: O sistema deve permitir a geração de relatórios em tempo real, com filtros de data, laboratório ou máquina específica.

---

### **5. Considerações Finais**

O sistema "Gerenciador de Hardware para Laboratórios" deve facilitar o controle e o monitoramento das máquinas e peças de hardware, proporcionando maior eficiência na gestão de manutenção e inventário dos laboratórios. Este documento deverá ser revisado periodicamente conforme novas funcionalidades e necessidades sejam identificadas.

---

## CODIGO MYSQL:

```sql

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


```
