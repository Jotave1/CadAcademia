package view.telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.List;

import model.Aluno;

public class TelaExibirAlunos {
    public TelaExibirAlunos(List<Aluno> alunos) {
        JFrame frame = new JFrame("Alunos Cadastrados");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        String[] colunas = {"ID", "Nome", "Idade", "Telefone", "Peso", "Objetivo", "Tipo de Treino"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        
        for (Aluno aluno : alunos) {
            Object[] linha = {
                aluno.getId(), 
                aluno.getNome(), 
                aluno.getIdade(), 
                aluno.getTelefone(), 
                aluno.getPeso(), 
                aluno.getObjetivo(), 
                aluno.getTipoTreino()
            };
            model.addRow(linha);
        }
        
        JTable tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
