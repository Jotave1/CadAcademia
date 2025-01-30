package view.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.AlunoController;
import view.TelaInicial;

public class TelaGerenciamento {
    private AlunoController alunoController;

    public TelaGerenciamento(AlunoController alunoController) {
        this.alunoController = alunoController;

        JFrame frame = new JFrame("Gerenciamento");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel painelImagem = new JPanel();
        painelImagem.setLayout(new BorderLayout());

        ImageIcon imagemIcone = null;
        try {
            imagemIcone = new ImageIcon(getClass().getResource("/img/pjt-POO-TelaFundo.png"));
        } catch (Exception e) {
            System.err.println("Erro ao carregar a imagem: " + e.getMessage());
        }

        if (imagemIcone != null) {
            JLabel labelImagem = new JLabel();
            Image imagem = imagemIcone.getImage();
            Image imagemRedimensionada = imagem.getScaledInstance(1000, 700, Image.SCALE_SMOOTH);
            imagemIcone = new ImageIcon(imagemRedimensionada);
            labelImagem.setIcon(imagemIcone);
            painelImagem.add(labelImagem, BorderLayout.CENTER);
        } else {
            System.err.println("A imagem não pôde ser carregada.");
        }

        frame.setContentPane(painelImagem);

        JPanel painelBotoes = new JPanel(new GridBagLayout());
        painelBotoes.setOpaque(false); // Tornar o painel dos botões transparente
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;

        JButton btnGerenciarAlunos = new JButton("Gerenciar Alunos");
        painelBotoes.add(btnGerenciarAlunos, gbc);

        gbc.gridy++;
        JButton btnGerenciarTreinos = new JButton("Gerenciar Treinos");
        painelBotoes.add(btnGerenciarTreinos, gbc);

        gbc.gridy++;
        JButton btnConsultarTreinos = new JButton("Consultar Alunos");
        painelBotoes.add(btnConsultarTreinos, gbc);

        gbc.gridy++;
        JButton btnVoltar = new JButton("Voltar");
        painelBotoes.add(btnVoltar, gbc);

        painelImagem.add(painelBotoes, BorderLayout.CENTER);

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

        btnConsultarTreinos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaConsultaAlunos(alunoController);
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

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
