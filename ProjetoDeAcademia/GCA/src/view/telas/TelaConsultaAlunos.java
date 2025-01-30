package view.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import controller.AlunoController;
import model.Aluno;
import view.TelaInicial;

public class TelaConsultaAlunos {
    private AlunoController alunoController;

    public TelaConsultaAlunos(AlunoController alunoController) {
        this.alunoController = alunoController;

        JFrame frame = new JFrame("Consulta de Alunos");
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

        JPanel painelConsulta = new JPanel(new GridBagLayout());
        painelConsulta.setOpaque(false); // Tornar o painel da consulta transparente
        painelConsulta.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel labelFiltroNome = new JLabel("Filtro por Nome:");
        painelConsulta.add(labelFiltroNome, gbc);

        JTextField textNome = new JTextField(20);
        gbc.gridx = 1;
        painelConsulta.add(textNome, gbc);

        gbc.gridx = 2;
        JButton btnBuscarNome = new JButton("Buscar");
        painelConsulta.add(btnBuscarNome, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelTipoTreino = new JLabel("Filtro por Tipo de Treino:");
        painelConsulta.add(labelTipoTreino, gbc);

        JTextField textTipoTreino = new JTextField(20);
        gbc.gridx = 1;
        painelConsulta.add(textTipoTreino, gbc);

        gbc.gridx = 2;
        JButton btnBuscarTipoTreino = new JButton("Buscar");
        painelConsulta.add(btnBuscarTipoTreino, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelIntervaloId = new JLabel("Filtro por Intervalo de ID:");
        painelConsulta.add(labelIntervaloId, gbc);

        JTextField textIdInicio = new JTextField(5);
        gbc.gridx = 1;
        painelConsulta.add(textIdInicio, gbc);

        JLabel labelAte = new JLabel("até");
        gbc.gridx = 2;
        painelConsulta.add(labelAte, gbc);

        JTextField textIdFim = new JTextField(5);
        gbc.gridx = 3;
        painelConsulta.add(textIdFim, gbc);

        JButton btnBuscarIntervalo = new JButton("Buscar");
        gbc.gridx = 4;
        painelConsulta.add(btnBuscarIntervalo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelObjetivo = new JLabel("Filtro por Objetivo:");
        painelConsulta.add(labelObjetivo, gbc);

        JTextField textObjetivo = new JTextField(20);
        gbc.gridx = 1;
        painelConsulta.add(textObjetivo, gbc);

        gbc.gridx = 2;
        JButton btnBuscarObjetivo = new JButton("Buscar");
        painelConsulta.add(btnBuscarObjetivo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.BOTH;
        JTextArea textAreaResultados = new JTextArea(10, 50);
        textAreaResultados.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textAreaResultados);
        painelConsulta.add(scrollPane, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        JButton btnVoltar = new JButton("Voltar");
        painelConsulta.add(btnVoltar, gbc);

        painelImagem.add(painelConsulta, BorderLayout.CENTER);

        btnBuscarNome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = textNome.getText();
                List<Aluno> alunos = alunoController.consultarAlunosPorNome(nome);
                exibirResultados(textAreaResultados, alunos);
            }
        });

        btnBuscarTipoTreino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoTreino = textTipoTreino.getText();
                List<Aluno> alunos = alunoController.consultarAlunosPorTipoTreino(tipoTreino);
                exibirResultados(textAreaResultados, alunos);
            }
        });

        btnBuscarIntervalo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int idInicio = Integer.parseInt(textIdInicio.getText());
                int idFim = Integer.parseInt(textIdFim.getText());
                List<Aluno> alunos = alunoController.consultarAlunosPorIntervaloId(idInicio, idFim);
                exibirResultados(textAreaResultados, alunos);
            }
        });

        btnBuscarObjetivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String objetivo = textObjetivo.getText();
                List<Aluno> alunos = alunoController.consultarAlunosPorObjetivo(objetivo);
                exibirResultados(textAreaResultados, alunos);
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

    private void exibirResultados(JTextArea textArea, List<Aluno> alunos) {
        textArea.setText("");
        for (Aluno aluno : alunos) {
            textArea.append("ID: " + aluno.getId() + ", Nome: " + aluno.getNome() + ", Idade: " + aluno.getIdade() +
                            ", Telefone: " + aluno.getTelefone() + ", Peso: " + aluno.getPeso() + ", Objetivo: " + aluno.getObjetivo() +
                            ", Tipo de Treino: " + aluno.getTipoTreino() + "\n");
        }
    }
}
