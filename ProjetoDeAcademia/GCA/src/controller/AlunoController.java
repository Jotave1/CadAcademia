package controller;

import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Treino;
import java.util.stream.Collectors;

public class AlunoController {
    private List<Aluno> alunos;
    private List<Treino> treinos;
    private static int proximoIdAluno = 1;
    private static int proximoIdTreino = 1;

    public AlunoController() {
        alunos = new ArrayList<>();
        treinos = new ArrayList<>();
    }

    public void cadastrarAluno(Aluno aluno) {
        aluno.setId(proximoIdAluno++);
        alunos.add(aluno);
    }

    public void atualizarAluno(int id, Aluno novoAluno) {
        for (Aluno aluno : alunos) {
            if (aluno.getId() == id) {
                aluno.setNome(novoAluno.getNome());
                aluno.setIdade(novoAluno.getIdade());
                aluno.setTelefone(novoAluno.getTelefone());
                aluno.setPeso(novoAluno.getPeso());
                aluno.setObjetivo(novoAluno.getObjetivo());
                aluno.setTipoTreino(novoAluno.getTipoTreino());
                break;
            }
        }
    }

    public void excluirAluno(int id) {
        alunos.removeIf(aluno -> aluno.getId() == id);
    }

    public List<Aluno> consultarAlunos() {
        return new ArrayList<>(alunos);
    }

    public void cadastrarTreino(Treino treino) {
        treino.setId(proximoIdTreino++);
        treinos.add(treino);
    }

    public void atualizarTreino(int id, Treino novoTreino) {
        for (Treino treino : treinos) {
            if (treino.getId() == id) {
                treino.setOrdem(novoTreino.getOrdem());
                treino.setDescricao(novoTreino.getDescricao());
                break;
            }
        }
    }

    public void excluirTreino(int id) {
        treinos.removeIf(treino -> treino.getId() == id);
    }

    public List<Treino> consultarTreinos() {
        return new ArrayList<>(treinos);
    }

    public List<String> consultarTiposTreino() {
        return treinos.stream()
                .map(Treino::getOrdem)
                .distinct()
                .collect(Collectors.toList());
    }
}