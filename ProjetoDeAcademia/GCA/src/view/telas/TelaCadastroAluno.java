package view.telas;

import javax.swing.*;
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
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(10, 10, 80, 25);
        frame.add(labelNome);

        JTextField textNome = new JTextField();
        textNome.setBounds(100, 10, 200, 25);
        frame.add(textNome);

        JLabel labelIdade = new JLabel("Idade:");
        labelIdade.setBounds(10, 40, 80, 25);
        frame.add(labelIdade);

        JTextField textIdade = new JTextField();
        textIdade.setBounds(100, 40, 200, 25);
        frame.add(textIdade);

        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setBounds(10, 70, 80, 25);
        frame.add(labelTelefone);

        JTextField textTelefone = new JTextField();
        textTelefone.setBounds(100, 70, 200, 25);
        frame.add(textTelefone);

        JLabel labelPeso = new JLabel("Peso:");
        labelPeso.setBounds(10, 100, 80, 25);
        frame.add(labelPeso);

        JTextField textPeso = new JTextField();
        textPeso.setBounds(100, 100, 200, 25);
        frame.add(textPeso);

        JLabel labelObjetivo = new JLabel("Objetivo:");
        labelObjetivo.setBounds(10, 130, 80, 25);
        frame.add(labelObjetivo);

        String[] opcoesObjetivo = {"Hipertrofia", "Emagrecimento", "Manutenção"};
        JComboBox<String> comboBoxObjetivo = new JComboBox<>(opcoesObjetivo);
        comboBoxObjetivo.setBounds(100, 130, 200, 25);
        frame.add(comboBoxObjetivo);

        JLabel labelTipoTreino = new JLabel("Tipo de Treino:");
        labelTipoTreino.setBounds(10, 160, 80, 25);
        frame.add(labelTipoTreino);

        List<String> tiposTreino = alunoController.consultarTiposTreino();
        JComboBox<String> comboBoxTipoTreino = new JComboBox<>(tiposTreino.toArray(new String[0]));
        comboBoxTipoTreino.setBounds(100, 160, 200, 25);
        frame.add(comboBoxTipoTreino);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(100, 190, 100, 25);
        frame.add(btnCadastrar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(210, 190, 100, 25);
        frame.add(btnVoltar);

        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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