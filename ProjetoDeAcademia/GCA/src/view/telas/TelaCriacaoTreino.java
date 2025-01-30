package view.telas;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.AlunoController;
import model.Treino;
import view.TelaInicial;

public class TelaCriacaoTreino {
    private AlunoController alunoController;

    public TelaCriacaoTreino(AlunoController alunoController) {
        this.alunoController = alunoController;

        JFrame frame = new JFrame("Criação de Treinos");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel labelOrdem = new JLabel("Ordem:");
        labelOrdem.setBounds(10, 10, 80, 25);
        frame.add(labelOrdem);

        String[] ordens = {"ABC", "ABCD", "ABCDE", "PPL", "UL"};
        JComboBox<String> comboBoxOrdem = new JComboBox<>(ordens);
        comboBoxOrdem.setBounds(100, 10, 200, 25);
        frame.add(comboBoxOrdem);

        JLabel labelDescricao = new JLabel("Descrição:");
        labelDescricao.setBounds(10, 50, 80, 25);
        frame.add(labelDescricao);

        JTextField textDescricao = new JTextField();
        textDescricao.setBounds(100, 50, 200, 25);
        frame.add(textDescricao);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(100, 90, 100, 25);
        frame.add(btnSalvar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(210, 90, 100, 25);
        frame.add(btnVoltar);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ordem = (String) comboBoxOrdem.getSelectedItem();
                String descricao = textDescricao.getText();

                Treino treino = new Treino(alunoController.consultarTreinos().size() + 1, null, ordem, descricao);
                alunoController.cadastrarTreino(treino);

                JOptionPane.showMessageDialog(frame, "Treino cadastrado com sucesso!");

                new TelaInicial(alunoController);
                frame.dispose();
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaInicial(alunoController);
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
