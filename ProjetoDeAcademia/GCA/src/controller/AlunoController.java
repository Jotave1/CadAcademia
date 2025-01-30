package controller;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import model.Aluno;
import model.ConexaoBancoDados;
import model.Treino;

public class AlunoController {
    private List<Aluno> alunos;
    private List<Treino> treinos;

    private static final String SELECT_ALUNOS_POR_NOME = "SELECT * FROM alunos WHERE nome LIKE ?";
    private static final String SELECT_ALUNOS_POR_TIPO_TREINO = "SELECT * FROM alunos WHERE tipo_treino = ?";
    private static final String SELECT_ALUNOS_POR_INTERVALO_ID = "SELECT * FROM alunos WHERE id BETWEEN ? AND ?";
    private static final String SELECT_ALUNOS_POR_OBJETIVO = "SELECT * FROM alunos WHERE objetivo LIKE ?";

    private static final String INSERT_ALUNO_SQL = "INSERT INTO alunos (nome, idade, telefone, peso, objetivo, tipo_treino) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_ALUNOS_SQL = "SELECT * FROM alunos";
    private static final String DELETE_ALUNO_SQL = "DELETE FROM alunos WHERE id = ?";
    private static final String UPDATE_ALUNO_SQL = "UPDATE alunos SET nome = ?, idade = ?, telefone = ?, peso = ?, objetivo = ?, tipo_treino = ? WHERE id = ?";

    private static final String INSERT_TREINO_SQL = "INSERT INTO treinos (ordem, descricao) VALUES (?, ?)";
    private static final String SELECT_ALL_TREINOS_SQL = "SELECT * FROM treinos";
    private static final String DELETE_TREINO_SQL = "DELETE FROM treinos WHERE id = ?";
    private static final String UPDATE_TREINO_SQL = "UPDATE treinos SET ordem = ?, descricao = ? WHERE id = ?";

    public AlunoController() {
        alunos = new ArrayList<>();
        treinos = new ArrayList<>();
    }

    public void cadastrarAluno(Aluno aluno) {
        try (Connection conn = ConexaoBancoDados.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(INSERT_ALUNO_SQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, aluno.getNome());
            pstmt.setInt(2, aluno.getIdade());
            pstmt.setString(3, aluno.getTelefone());
            pstmt.setDouble(4, aluno.getPeso());
            pstmt.setString(5, aluno.getObjetivo());
            pstmt.setString(6, aluno.getTipoTreino());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        aluno.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarAluno(int id, Aluno novoAluno) {
        try (Connection conn = ConexaoBancoDados.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_ALUNO_SQL)) {

            pstmt.setString(1, novoAluno.getNome());
            pstmt.setInt(2, novoAluno.getIdade());
            pstmt.setString(3, novoAluno.getTelefone());
            pstmt.setDouble(4, novoAluno.getPeso());
            pstmt.setString(5, novoAluno.getObjetivo());
            pstmt.setString(6, novoAluno.getTipoTreino());
            pstmt.setInt(7, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirAluno(int id) {
        try (Connection conn = ConexaoBancoDados.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(DELETE_ALUNO_SQL)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Aluno> consultarAlunos() {
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = ConexaoBancoDados.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_ALUNOS_SQL);
        ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                String telefone = rs.getString("telefone");
                double peso = rs.getDouble("peso");
                String objetivo = rs.getString("objetivo");
                String tipoTreino = rs.getString("tipo_treino");

                Aluno aluno = new Aluno(id, nome, idade, telefone, peso, objetivo, tipoTreino);
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public void cadastrarTreino(Treino treino) {
        try (Connection conn = ConexaoBancoDados.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(INSERT_TREINO_SQL, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, treino.getOrdem());
            pstmt.setString(2, treino.getDescricao());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        treino.setId(generatedKeys.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarTreino(int id, Treino novoTreino) {
        try (Connection conn = ConexaoBancoDados.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(UPDATE_TREINO_SQL)) {

            pstmt.setString(1, novoTreino.getOrdem());
            pstmt.setString(2, novoTreino.getDescricao());
            pstmt.setInt(3, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirTreino(int id) {
        try (Connection conn = ConexaoBancoDados.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(DELETE_TREINO_SQL)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Treino> consultarTreinos() {
        List<Treino> treinos = new ArrayList<>();
        try (Connection conn = ConexaoBancoDados.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_TREINOS_SQL);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String ordem = rs.getString("ordem");
                String descricao = rs.getString("descricao");

                Treino treino = new Treino(id, ordem, descricao);
                treinos.add(treino);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return treinos;
    }

    public List<String> consultarTiposTreino() {
        List<String> tipos = new ArrayList<>();
        try (Connection conn = ConexaoBancoDados.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT DISTINCT ordem FROM treinos");
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                tipos.add(rs.getString("ordem"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipos;
    }

    public List<Aluno> consultarAlunosPorNome(String nome) {
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = ConexaoBancoDados.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SELECT_ALUNOS_POR_NOME)) {

            pstmt.setString(1, "%" + nome + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nomeAluno = rs.getString("nome");
                int idade = rs.getInt("idade");
                String telefone = rs.getString("telefone");
                double peso = rs.getDouble("peso");
                String objetivo = rs.getString("objetivo");
                String tipoTreino = rs.getString("tipo_treino");

                Aluno aluno = new Aluno(id, nomeAluno, idade, telefone, peso, objetivo, tipoTreino);
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public List<Aluno> consultarAlunosPorTipoTreino(String tipoTreino) {
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = ConexaoBancoDados.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SELECT_ALUNOS_POR_TIPO_TREINO)) {

            pstmt.setString(1, tipoTreino);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                String telefone = rs.getString("telefone");
                double peso = rs.getDouble("peso");
                String objetivo = rs.getString("objetivo");

                Aluno aluno = new Aluno(id, nome, idade, telefone, peso, objetivo, tipoTreino);
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public List<Aluno> consultarAlunosPorIntervaloId(int idInicio, int idFim) {
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = ConexaoBancoDados.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SELECT_ALUNOS_POR_INTERVALO_ID)) {

            pstmt.setInt(1, idInicio);
            pstmt.setInt(2, idFim);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                String telefone = rs.getString("telefone");
                double peso = rs.getDouble("peso");
                String objetivo = rs.getString("objetivo");
                String tipoTreino = rs.getString("tipo_treino");

                Aluno aluno = new Aluno(id, nome, idade, telefone, peso, objetivo, tipoTreino);
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }

    public List<Aluno> consultarAlunosPorObjetivo(String objetivo) {
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = ConexaoBancoDados.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(SELECT_ALUNOS_POR_OBJETIVO)) {

            pstmt.setString(1, "%" + objetivo + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                String telefone = rs.getString("telefone");
                double peso = rs.getDouble("peso");
                String obj = rs.getString("objetivo");
                String tipoTreino = rs.getString("tipo_treino");

                Aluno aluno = new Aluno(id, nome, idade, telefone, peso, obj, tipoTreino);
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }
}
