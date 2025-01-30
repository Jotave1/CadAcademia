package view.telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

import controller.AlunoController;
import model.Aluno;
import view.edicao.TelaEditarAluno;

public class TelaGerenciarAlunos {
    private AlunoController alunoController;

    public TelaGerenciarAlunos(AlunoController alunoController) {
        this.alunoController = alunoController;

        JFrame frame = new JFrame("Gerenciar Alunos");
        frame.setSize(600, 450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        String[] colunas = {"ID", "Nome", "Idade", "Telefone", "Peso", "Objetivo", "Tipo de Treino"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        List<Aluno> alunos = alunoController.consultarAlunos();
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
        scrollPane.setBounds(10, 10, 560, 300);
        frame.add(scrollPane);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(100, 320, 100, 25);
        frame.add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(210, 320, 100, 25);
        frame.add(btnExcluir);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(320, 320, 100, 25);
        frame.add(btnVoltar);

        btnEditar.addActionListener(e -> {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow != -1) {
                int alunoId = (int) model.getValueAt(selectedRow, 0);
                new TelaEditarAluno(alunoController, alunoId);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um aluno para editar.");
            }
        });

        btnExcluir.addActionListener(e -> {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow != -1) {
                int alunoId = (int) model.getValueAt(selectedRow, 0);
                alunoController.excluirAluno(alunoId);
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(frame, "Aluno excluído com sucesso.");
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um aluno para excluir.");
            }
        });

        btnVoltar.addActionListener(e -> {
            new TelaGerenciamento(alunoController);
            frame.dispose();
        });

        frame.setVisible(true);
    }
}
