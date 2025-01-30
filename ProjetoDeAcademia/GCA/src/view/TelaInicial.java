package view;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import java.awt.*;
import java.util.Enumeration;

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

        JFrame frame = new JFrame("Sistema de Academia");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());

        frame.add(criarPainelImagem(), BorderLayout.CENTER);
        frame.add(criarPainelBotoes(), BorderLayout.EAST);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private JPanel criarPainelImagem() {
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

        return painelImagem;
    }

    private JPanel criarPainelBotoes() {
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridBagLayout());
        painelBotoes.setPreferredSize(new Dimension(300, 700));
        painelBotoes.setOpaque(false); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 10, 15, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTH;

        JButton btnCadastroAluno = new JButton("Cadastrar Alunos");
        JButton btnCriacaoTreino = new JButton("Criar Treinos");
        JButton btnExibirAlunos = new JButton("Exibir Alunos");
        JButton btnExibirTreinos = new JButton("Exibir Treinos");
        JButton btnGerenciamento = new JButton("Gerenciamento");
        JButton btnSair = new JButton("Sair");

        JButton[] botoes = {btnCadastroAluno, btnCriacaoTreino, btnExibirAlunos, btnExibirTreinos, btnGerenciamento, btnSair};
        for (JButton botao : botoes) {
            botao.setFont(new Font("Arial", Font.BOLD, 16));
            botao.setBackground(new Color(41, 128, 185));
            botao.setForeground(Color.WHITE);
            botao.setFocusPainted(false);
            botao.setOpaque(true);
            botao.setContentAreaFilled(true);
            botao.setBorderPainted(true);
            botao.setPreferredSize(new Dimension(250, 50));
        }

        btnCadastroAluno.addActionListener(e -> new TelaCadastroAluno(alunoController));
        btnCriacaoTreino.addActionListener(e -> new TelaCriacaoTreino(alunoController));
        btnExibirAlunos.addActionListener(e -> new TelaExibirAlunos(alunoController.consultarAlunos()));
        btnExibirTreinos.addActionListener(e -> new TelaExibirTreinos(alunoController.consultarTreinos()));
        btnGerenciamento.addActionListener(e -> new TelaGerenciamento(alunoController));
        btnSair.addActionListener(e -> System.exit(0));

        gbc.gridy = 0;
        painelBotoes.add(btnCadastroAluno, gbc);
        gbc.gridy++;
        painelBotoes.add(btnCriacaoTreino, gbc);
        gbc.gridy++;
        painelBotoes.add(btnExibirAlunos, gbc);
        gbc.gridy++;
        painelBotoes.add(btnExibirTreinos, gbc);
        gbc.gridy++;
        painelBotoes.add(btnGerenciamento, gbc);
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.SOUTH;
        painelBotoes.add(btnSair, gbc);

        return painelBotoes;
    }

    public static void main(String[] args) {
        setUIFont(new FontUIResource("Arial", Font.PLAIN, 14));

        AlunoController alunoController = new AlunoController();
        new TelaInicial(alunoController);
    }

    public static void setUIFont(FontUIResource f) {
        Enumeration<Object> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }
}