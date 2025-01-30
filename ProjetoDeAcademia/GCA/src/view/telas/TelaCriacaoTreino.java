package view.telas;

import javax.swing.*;
import java.awt.*;
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

        JPanel painelFormulario = new JPanel(new GridBagLayout());
        painelFormulario.setOpaque(false); // Tornar o painel do formulário transparente
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel labelOrdem = new JLabel("Ordem:");
        painelFormulario.add(labelOrdem, gbc);

        String[] ordens = {"ABC", "ABCD", "ABCDE", "PPL", "UL"};
        JComboBox<String> comboBoxOrdem = new JComboBox<>(ordens);
        gbc.gridx = 1;
        painelFormulario.add(comboBoxOrdem, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelDescricao = new JLabel("Descrição:");
        painelFormulario.add(labelDescricao, gbc);

        JTextField textDescricao = new JTextField(20);
        gbc.gridx = 1;
        painelFormulario.add(textDescricao, gbc);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        painelBotoes.setOpaque(false); // Tornar o painel dos botões transparente

        JButton btnSalvar = new JButton("Salvar");
        painelBotoes.add(btnSalvar);

        JButton btnVoltar = new JButton("Voltar");
        painelBotoes.add(btnVoltar);

        painelImagem.add(painelFormulario, BorderLayout.CENTER);
        painelImagem.add(painelBotoes, BorderLayout.SOUTH);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ordem = (String) comboBoxOrdem.getSelectedItem();
                String descricao = textDescricao.getText();

                Treino treino = new Treino(alunoController.consultarTreinos().size() + 1, ordem, descricao);
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

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
