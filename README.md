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
create database GerenciadorDeHardware;
use GerenciadorDeHardware;

-- Tabela laboratorios
CREATE TABLE laboratorios (
    id_laboratorio INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    localizacao VARCHAR(255)
);

-- Tabela maquinas
CREATE TABLE maquinas (
    id_maquina INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    id_laboratorio INT,
    cpu VARCHAR(100),
    ram VARCHAR(50),
    armazenamento VARCHAR(50),
    status ENUM('funcionando', 'em manutencao', 'fora de uso') DEFAULT 'funcionando',
    data_aquisicao DATE,
    FOREIGN KEY (id_laboratorio) REFERENCES laboratorios(id_laboratorio)
);

-- Tabela pecas
CREATE TABLE pecas (
    id_peca INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    tipo VARCHAR(50),
    fabricante VARCHAR(100),
    quantidade INT DEFAULT 0
);

-- Tabela manutencoes
CREATE TABLE manutencoes (
    id_manutencao INT PRIMARY KEY AUTO_INCREMENT,
    id_maquina INT,
    tipo ENUM('preventiva', 'corretiva') NOT NULL,
    data_manutencao DATE NOT NULL,
    descricao TEXT,
    status ENUM('agendada', 'concluida', 'cancelada') DEFAULT 'agendada',
    FOREIGN KEY (id_maquina) REFERENCES maquinas(id_maquina)
);

-- Tabela pecas_manutencao (associação entre pecas e manutencoes)
CREATE TABLE pecas_manutencao (
    id_peca_manutencao INT PRIMARY KEY AUTO_INCREMENT,
    id_manutencao INT,
    id_peca INT,
    quantidade INT DEFAULT 1,
    FOREIGN KEY (id_manutencao) REFERENCES manutencoes(id_manutencao),
    FOREIGN KEY (id_peca) REFERENCES pecas(id_peca)
);

-- Tabela usuarios
CREATE TABLE usuarios (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    senha VARCHAR(255) NOT NULL,
    perfil ENUM('tecnico', 'administrador') DEFAULT 'tecnico',
    data_cadastro DATE DEFAULT (NOW())
);

-- Tabela logs_acoes
CREATE TABLE logs_acoes (
    id_log INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT,
    acao VARCHAR(255) NOT NULL,
    data_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario)
);


```
