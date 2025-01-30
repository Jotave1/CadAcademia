package view.telas;

import javax.swing.*;

import controller.AlunoController;
import view.TelaInicial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaGerenciamento {
    private AlunoController alunoController;

    public TelaGerenciamento(AlunoController alunoController) {
        this.alunoController = alunoController;

        JFrame frame = new JFrame("Gerenciamento");
        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JButton btnGerenciarAlunos = new JButton("Gerenciar Alunos");
        btnGerenciarAlunos.setBounds(100, 30, 200, 30);
        frame.add(btnGerenciarAlunos);

        JButton btnGerenciarTreinos = new JButton("Gerenciar Treinos");
        btnGerenciarTreinos.setBounds(100, 70, 200, 30);
        frame.add(btnGerenciarTreinos);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(100, 110, 200, 30);
        frame.add(btnVoltar);

        btnGerenciarAlunos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaGerenciarAlunos(alunoController);
                frame.dispose();
            }
        });

        btnGerenciarTreinos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaGerenciarTreinos(alunoController);
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