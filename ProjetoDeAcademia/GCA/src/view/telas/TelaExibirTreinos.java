package view.telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import model.Treino;

public class TelaExibirTreinos {
    public TelaExibirTreinos(List<Treino> treinos) {
        JFrame frame = new JFrame("Treinos Cadastrados");
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
        painelConteudo.add(scrollPane, BorderLayout.CENTER);

        // Adicionar o painel de conteúdo ao frame
        painelImagem.add(painelConteudo, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
