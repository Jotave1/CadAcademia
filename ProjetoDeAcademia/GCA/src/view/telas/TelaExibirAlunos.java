package view.telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import model.Aluno;

public class TelaExibirAlunos {
    public TelaExibirAlunos(List<Aluno> alunos) {
        JFrame frame = new JFrame("Alunos Cadastrados");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        JPanel painelConteudo = new JPanel(new BorderLayout());
        painelConteudo.setOpaque(false); // Tornar o painel transparente

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
        painelConteudo.add(scrollPane, BorderLayout.CENTER);

        painelImagem.add(painelConteudo, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
