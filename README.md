# Sistema de Cadastro de Alunos e Treinos

Este projeto implementa um sistema CRUD (Create, Read, Update, Delete) para gerenciar cadastros de alunos e treinos em uma academia

## Funcionalidades

- **Gerenciamento de Alunos**: Adicionar, visualizar, atualizar e remover Alunos.
- **Gerenciamento de Treinos**: Adicionar, visualizar, atualizar e remover Treinos.

## Algumas interfaces:
**Tela Inicial**:
  ![Tela Inicial](telaInicial.png) 
  
**Cadastro de Alunos**:
  ![Tela de Cadastro de Alunos](cadAlunos.png)
  
**Criação de Treinos**:
  ![Tela de Criação de Treinos](cadTreinos.png)

**Tabela de Alunos Cadastrados**:
  ![Tela da Tabela de Alunos](tabelaAlunos.png)

**Tabela de Treinos Criados**:
  ![Tela da Tabela de Treinos](tabelaTreinos.png)

**Opções de Gerenciamento**:
  ![Tela de Opções de Gerenciamento](gerenciamento.png)

**Gerenciamento de Alunos**:
  ![Tela de Gerenciamento de Alunos](gerAlunos.png)

**Gerenciamento de Treinos**:
  ![Tela de Gerenciamento de Treinos](gerTreinos.png)

**Tela de Busca de Alunos**:
  ![Tela de Busca de Alunos](consultaAlunos.png)
  

## Diagrama ER
![Diagrama ER](modelo-ER-BD.png)

## Dicionário de Dados

### Tabela: `alunos`
| Campo | Tipo | Tamanho | Descrição |
|-------------------|--------------|---------|------------|
| id                | INT          |         | Identificador único do aluno (Primary Key). |
| nome              | VARCHAR      | 255     | Nome completo do aluno. |
| idade             | INT          |         | Idade do aluno. |
| telefone          | VARCHAR      | 255     | Telefone para contado do aluno. |
| peso              | DOUBLE       |         | Peso atual do aluno em quilogramas. |
| objetivo          | VARCHAR      | 255     | Objetivo do aluno (Hipertrofia, Emagrecimento). |
| tipo_treino       | VARCHAR      | 255     | Tipo de trino escolhido pelo aluno. |

### Tabela: `treinos`
| Campo | Tipo | Tamanho | Descrição |
|-------------------|--------------|---------|------------|
| id                | INT          |         | Identificador único do treino (Primary Key) |
| oredom            | VARCHAR      | 255     | Ordem do treino (ABC, ABCD, ABCDE, PPL, UL) |
| descricao         | VARCHAR      | 255     | Descrição detalhada do treino |


## Diagrama de classes
![Diagrama de Classes](DiagramaClasses.png)
