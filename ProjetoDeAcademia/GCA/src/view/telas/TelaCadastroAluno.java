package view.telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import controller.AlunoController;
import model.Aluno;
import view.TelaInicial;

public class TelaCadastroAluno {
    private AlunoController alunoController;

    public TelaCadastroAluno(AlunoController alunoController) {
        this.alunoController = alunoController;

        JFrame frame = new JFrame("Cadastro de Alunos");
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
        painelFormulario.setOpaque(false);
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        JLabel labelNome = new JLabel("Nome:");
        painelFormulario.add(labelNome, gbc);

        JTextField textNome = new JTextField(20);
        gbc.gridx = 1;
        painelFormulario.add(textNome, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelIdade = new JLabel("Idade:");
        painelFormulario.add(labelIdade, gbc);

        JTextField textIdade = new JTextField(20);
        gbc.gridx = 1;
        painelFormulario.add(textIdade, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelTelefone = new JLabel("Telefone:");
        painelFormulario.add(labelTelefone, gbc);

        JTextField textTelefone = new JTextField(20);
        gbc.gridx = 1;
        painelFormulario.add(textTelefone, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelPeso = new JLabel("Peso:");
        painelFormulario.add(labelPeso, gbc);

        JTextField textPeso = new JTextField(20);
        gbc.gridx = 1;
        painelFormulario.add(textPeso, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelObjetivo = new JLabel("Objetivo:");
        painelFormulario.add(labelObjetivo, gbc);

        String[] opcoesObjetivo = {"Hipertrofia", "Emagrecimento"};
        JComboBox<String> comboBoxObjetivo = new JComboBox<>(opcoesObjetivo);
        gbc.gridx = 1;
        painelFormulario.add(comboBoxObjetivo, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        JLabel labelTipoTreino = new JLabel("Tipo de Treino:");
        painelFormulario.add(labelTipoTreino, gbc);

        List<String> tiposTreino = alunoController.consultarTiposTreino();
        JComboBox<String> comboBoxTipoTreino = new JComboBox<>(tiposTreino.toArray(new String[0]));
        gbc.gridx = 1;
        painelFormulario.add(comboBoxTipoTreino, gbc);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        painelBotoes.setOpaque(false);

        JButton btnCadastrar = new JButton("Cadastrar");
        painelBotoes.add(btnCadastrar);

        JButton btnVoltar = new JButton("Voltar");
        painelBotoes.add(btnVoltar);

        painelImagem.add(painelFormulario, BorderLayout.CENTER);
        painelImagem.add(painelBotoes, BorderLayout.SOUTH);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nome = textNome.getText();
                    int idade = Integer.parseInt(textIdade.getText());
                    String telefone = textTelefone.getText();
                    double peso = Double.parseDouble(textPeso.getText());
                    String objetivo = (String) comboBoxObjetivo.getSelectedItem();
                    String tipoTreino = (String) comboBoxTipoTreino.getSelectedItem();

                    Aluno aluno = new Aluno(alunoController.consultarAlunos().size() + 1, nome, idade, telefone, peso, objetivo, tipoTreino);
                    alunoController.cadastrarAluno(aluno);

                    JOptionPane.showMessageDialog(frame, "Aluno cadastrado com sucesso!");

                    textNome.setText("");
                    textIdade.setText("");
                    textTelefone.setText("");
                    textPeso.setText("");
                    comboBoxObjetivo.setSelectedIndex(0);
                    comboBoxTipoTreino.setSelectedIndex(0);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Por favor, insira valores válidos nos campos.");
                }
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
