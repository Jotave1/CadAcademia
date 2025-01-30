package view.edicao;

import javax.swing.*;

import controller.AlunoController;
import model.Aluno;
import view.telas.TelaGerenciarAlunos;

import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaEditarAluno {
    private AlunoController alunoController;
    private int alunoId;

    public TelaEditarAluno(AlunoController alunoController, int alunoId) {
        this.alunoController = alunoController;
        this.alunoId = alunoId;

        Aluno aluno = alunoController.consultarAlunos().stream()
            .filter(a -> a.getId() == alunoId)
            .findFirst()
            .orElse(null);

        if (aluno == null) {
            JOptionPane.showMessageDialog(null, "Aluno não encontrado.");
            return;
        }

        JFrame frame = new JFrame("Editar Aluno");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel labelNome = new JLabel("Nome:");
        labelNome.setBounds(10, 10, 80, 25);
        frame.add(labelNome);

        JTextField textNome = new JTextField(aluno.getNome());
        textNome.setBounds(100, 10, 200, 25);
        frame.add(textNome);

        JLabel labelIdade = new JLabel("Idade:");
        labelIdade.setBounds(10, 40, 80, 25);
        frame.add(labelIdade);

        JTextField textIdade = new JTextField(String.valueOf(aluno.getIdade()));
        textIdade.setBounds(100, 40, 200, 25);
        frame.add(textIdade);

        JLabel labelTelefone = new JLabel("Telefone:");
        labelTelefone.setBounds(10, 70, 80, 25);
        frame.add(labelTelefone);

        JTextField textTelefone = new JTextField(aluno.getTelefone());
        textTelefone.setBounds(100, 70, 200, 25);
        frame.add(textTelefone);

        JLabel labelPeso = new JLabel("Peso:");
        labelPeso.setBounds(10, 100, 80, 25);
        frame.add(labelPeso);

        JTextField textPeso = new JTextField(String.valueOf(aluno.getPeso()));
        textPeso.setBounds(100, 100, 200, 25);
        frame.add(textPeso);

        JLabel labelObjetivo = new JLabel("Objetivo:");
        labelObjetivo.setBounds(10, 130, 80, 25);
        frame.add(labelObjetivo);

        String[] opcoesObjetivo = {"Hipertrofia", "Emagrecimento", "Manutenção"};
        JComboBox<String> comboBoxObjetivo = new JComboBox<>(opcoesObjetivo);
        comboBoxObjetivo.setSelectedItem(aluno.getObjetivo());
        comboBoxObjetivo.setBounds(100, 130, 200, 25);
        frame.add(comboBoxObjetivo);

        JLabel labelTipoTreino = new JLabel("Tipo de Treino:");
        labelTipoTreino.setBounds(10, 160, 80, 25);
        frame.add(labelTipoTreino);

        List<String> tiposTreino = alunoController.consultarTiposTreino();
        JComboBox<String> comboBoxTipoTreino = new JComboBox<>(tiposTreino.toArray(new String[0]));
        comboBoxTipoTreino.setSelectedItem(aluno.getTipoTreino());
        comboBoxTipoTreino.setBounds(100, 160, 200, 25);
        frame.add(comboBoxTipoTreino);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(100, 190, 100, 25);
        frame.add(btnSalvar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(210, 190, 100, 25);
        frame.add(btnVoltar);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = textNome.getText();
                int idade = Integer.parseInt(textIdade.getText());
                String telefone = textTelefone.getText();
                double peso = Double.parseDouble(textPeso.getText());
                String objetivo = (String) comboBoxObjetivo.getSelectedItem();
                String tipoTreino = (String) comboBoxTipoTreino.getSelectedItem();

                Aluno alunoAtualizado = new Aluno(alunoId, nome, idade, telefone, peso, objetivo, tipoTreino);
                alunoController.atualizarAluno(alunoId, alunoAtualizado);

                JOptionPane.showMessageDialog(frame, "Aluno atualizado com sucesso!");

                new TelaGerenciarAlunos(alunoController);
                frame.dispose();
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TelaGerenciarAlunos(alunoController);
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}