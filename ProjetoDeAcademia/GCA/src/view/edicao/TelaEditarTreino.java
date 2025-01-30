package view.edicao;

import javax.swing.*;

import controller.AlunoController;
import model.Treino;
import view.telas.TelaGerenciarTreinos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEditarTreino {
    private AlunoController alunoController;
    private int treinoId;

    public TelaEditarTreino(AlunoController alunoController, int treinoId) {
        this.alunoController = alunoController;
        this.treinoId = treinoId;

        Treino treino = alunoController.consultarTreinos().stream()
            .filter(t -> t.getId() == treinoId)
            .findFirst()
            .orElse(null);

        if (treino == null) {
            JOptionPane.showMessageDialog(null, "Treino não encontrado.");
            return;
        }

        JFrame frame = new JFrame("Editar Treino");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel labelOrdem = new JLabel("Ordem:");
        labelOrdem.setBounds(10, 10, 80, 25);
        frame.add(labelOrdem);

        JTextField textOrdem = new JTextField(treino.getOrdem());
        textOrdem.setBounds(100, 10, 200, 25);
        frame.add(textOrdem);

        JLabel labelDescricao = new JLabel("Descrição:");
        labelDescricao.setBounds(10, 50, 80, 25);
        frame.add(labelDescricao);

        JTextField textDescricao = new JTextField(treino.getDescricao());
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
                String ordem = textOrdem.getText();
                String descricao = textDescricao.getText();

                Treino treinoAtualizado = new Treino(treinoId, treino.getAluno(), ordem, descricao);
                alunoController.atualizarTreino(treinoId, treinoAtualizado);

                JOptionPane.showMessageDialog(frame, "Treino atualizado com sucesso!");

                new TelaGerenciarTreinos(alunoController);
                frame.dispose();
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaGerenciarTreinos(alunoController);
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}
