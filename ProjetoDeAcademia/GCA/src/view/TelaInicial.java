package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.AlunoController;
import view.telas.TelaCadastroAluno;
import view.telas.TelaCriacaoTreino;
import view.telas.TelaExibirAlunos;
import view.telas.TelaExibirTreinos;
import view.telas.TelaGerenciamento;

public class TelaInicial {
    private AlunoController alunoController;

    public TelaInicial(AlunoController alunoController) {
        this.alunoController = alunoController;

        JFrame frame = new JFrame("Sistema de Gerenciamento de Academia");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JButton btnCadastroAluno = new JButton("Cadastro de Alunos");
        btnCadastroAluno.setBounds(100, 30, 200, 30);
        frame.add(btnCadastroAluno);

        JButton btnCriacaoTreino = new JButton("Criação de Treinos");
        btnCriacaoTreino.setBounds(100, 70, 200, 30);
        frame.add(btnCriacaoTreino);

        JButton btnExibirAlunos = new JButton("Exibir Alunos Cadastrados");
        btnExibirAlunos.setBounds(100, 110, 200, 30);
        frame.add(btnExibirAlunos);

        JButton btnExibirTreinos = new JButton("Exibir Treinos Cadastrados");
        btnExibirTreinos.setBounds(100, 150, 200, 30);
        frame.add(btnExibirTreinos);

        JButton btnGerenciamento = new JButton("Gerenciamento");
        btnGerenciamento.setBounds(100, 190, 200, 30);
        frame.add(btnGerenciamento);

        btnCadastroAluno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCadastroAluno(alunoController);
                frame.dispose();
            }
        });

        btnCriacaoTreino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaCriacaoTreino(alunoController);
                frame.dispose();
            }
        });

        btnExibirAlunos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaExibirAlunos(alunoController.consultarAlunos());
            }
        });

        btnExibirTreinos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaExibirTreinos(alunoController.consultarTreinos());
            }
        });

        btnGerenciamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaGerenciamento(alunoController);
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }


    public static void main(String[] args) {
        AlunoController alunoController = new AlunoController();
        new TelaInicial(alunoController);
    }
}
