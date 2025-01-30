package view.telas;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import controller.AlunoController;
import model.Aluno;
import view.edicao.TelaEditarAluno;

public class TelaGerenciarAlunos {
    private AlunoController alunoController;

    public TelaGerenciarAlunos(AlunoController alunoController) {
        this.alunoController = alunoController;

        JFrame frame = new JFrame("Gerenciar Alunos");
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

        List<Aluno> alunos = alunoController.consultarAlunos();
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

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        painelBotoes.setOpaque(false); // Tornar o painel dos botões transparente

        JButton btnEditar = new JButton("Editar");
        painelBotoes.add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        painelBotoes.add(btnExcluir);

        JButton btnVoltar = new JButton("Voltar");
        painelBotoes.add(btnVoltar);

        painelConteudo.add(painelBotoes, BorderLayout.SOUTH);
        painelImagem.add(painelConteudo, BorderLayout.CENTER);

        btnEditar.addActionListener(e -> {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow != -1) {
                int alunoId = (int) model.getValueAt(selectedRow, 0);
                new TelaEditarAluno(alunoController, alunoId);
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um aluno para editar.");
            }
        });

        btnExcluir.addActionListener(e -> {
            int selectedRow = tabela.getSelectedRow();
            if (selectedRow != -1) {
                int alunoId = (int) model.getValueAt(selectedRow, 0);
                alunoController.excluirAluno(alunoId);
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(frame, "Aluno excluído com sucesso.");
            } else {
                JOptionPane.showMessageDialog(frame, "Selecione um aluno para excluir.");
            }
        });

        btnVoltar.addActionListener(e -> {
            new TelaGerenciamento(alunoController);
            frame.dispose();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
