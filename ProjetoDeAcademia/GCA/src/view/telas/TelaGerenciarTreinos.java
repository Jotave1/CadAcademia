package view.telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controller.AlunoController;
import model.Treino;
import view.edicao.TelaEditarTreino;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaGerenciarTreinos {
    private AlunoController alunoController;

    public TelaGerenciarTreinos(AlunoController alunoController) {
        this.alunoController = alunoController;

        JFrame frame = new JFrame("Gerenciar Treinos");
        frame.setSize(600, 450);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        String[] colunas = {"ID", "Ordem", "Descrição"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        
        List<Treino> treinos = alunoController.consultarTreinos();
        
        for (Treino treino : treinos) {
            Object[] linha = {
                treino.getId(),
                treino.getOrdem(),
                treino.getDescricao()
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
                int treinoId = (int) model.getValueAt(selectedRow, 0);
                new TelaEditarTreino(alunoController, treinoId);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um treino para editar.");
            }
        });

        btnExcluir.addActionListener(e -> {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow != -1) {
                int treinoId = (int) model.getValueAt(selectedRow, 0);
                alunoController.excluirTreino(treinoId);
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(frame, "Treino excluído com sucesso.");
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um treino para excluir.");
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaGerenciamento(alunoController);
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
