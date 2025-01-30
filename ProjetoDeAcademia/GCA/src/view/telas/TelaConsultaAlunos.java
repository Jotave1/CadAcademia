package view.telas;

import javax.swing.*;
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
        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel labelFiltroNome = new JLabel("Filtro por Nome:");
        labelFiltroNome.setBounds(10, 10, 150, 25);
        frame.add(labelFiltroNome);

        JTextField textNome = new JTextField();
        textNome.setBounds(160, 10, 150, 25);
        frame.add(textNome);

        JButton btnBuscarNome = new JButton("Buscar");
        btnBuscarNome.setBounds(320, 10, 100, 25);
        frame.add(btnBuscarNome);

        JLabel labelTipoTreino = new JLabel("Filtro por Tipo de Treino:");
        labelTipoTreino.setBounds(10, 50, 150, 25);
        frame.add(labelTipoTreino);

        JTextField textTipoTreino = new JTextField();
        textTipoTreino.setBounds(160, 50, 150, 25);
        frame.add(textTipoTreino);

        JButton btnBuscarTipoTreino = new JButton("Buscar");
        btnBuscarTipoTreino.setBounds(320, 50, 100, 25);
        frame.add(btnBuscarTipoTreino);

        JLabel labelIntervaloId = new JLabel("Filtro por Intervalo de ID:");
        labelIntervaloId.setBounds(10, 90, 150, 25);
        frame.add(labelIntervaloId);

        JTextField textIdInicio = new JTextField();
        textIdInicio.setBounds(160, 90, 50, 25);
        frame.add(textIdInicio);

        JLabel labelAte = new JLabel("até");
        labelAte.setBounds(220, 90, 30, 25);
        frame.add(labelAte);

        JTextField textIdFim = new JTextField();
        textIdFim.setBounds(250, 90, 50, 25);
        frame.add(textIdFim);

        JButton btnBuscarIntervalo = new JButton("Buscar");
        btnBuscarIntervalo.setBounds(320, 90, 100, 25);
        frame.add(btnBuscarIntervalo);

        JLabel labelObjetivo = new JLabel("Filtro por Objetivo:");
        labelObjetivo.setBounds(10, 130, 150, 25);
        frame.add(labelObjetivo);

        JTextField textObjetivo = new JTextField();
        textObjetivo.setBounds(160, 130, 150, 25);
        frame.add(textObjetivo);

        JButton btnBuscarObjetivo = new JButton("Buscar");
        btnBuscarObjetivo.setBounds(320, 130, 100, 25);
        frame.add(btnBuscarObjetivo);

        JTextArea textAreaResultados = new JTextArea();
        textAreaResultados.setBounds(10, 170, 660, 300);
        textAreaResultados.setEditable(false);
        frame.add(textAreaResultados);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(300, 480, 100, 25);
        frame.add(btnVoltar);

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