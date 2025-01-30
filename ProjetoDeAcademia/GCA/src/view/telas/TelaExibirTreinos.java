package view.telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.util.List;

import model.Treino;

public class TelaExibirTreinos {
    public TelaExibirTreinos(List<Treino> treinos) {
        JFrame frame = new JFrame("Treinos Cadastrados");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        String[] colunas = {"ID", "Ordem", "Descrição"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

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
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}