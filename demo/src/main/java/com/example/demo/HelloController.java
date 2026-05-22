package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

    private final String jdbcUrl = "jdbc:mysql://localhost:3306/escola";
    private final String dbUser = "root";
    private final String dbPassword = "admin";

    public static class AlunoRequest {
        public String matricula;
        public String nome;
    }

    public static class AtualizarAlunoRequest {
        public String nome;
    }

    @PostMapping("/insert")
    public String insert(@RequestBody AlunoRequest dados) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String insertSql = "INSERT INTO alunos (matricula, nome) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertSql);
            preparedStatement.setString(1, dados.matricula);
            preparedStatement.setString(2, dados.nome);

            preparedStatement.executeUpdate();
            return "Aluno " + dados.nome + " inserido com sucesso!";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao inserir no banco: " + e.getMessage();
        }
    }

    @GetMapping("/listar")
    public List<AlunoRequest> listar() {
        List<AlunoRequest> listaAlunos = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String selectSql = "SELECT matricula, nome FROM alunos";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AlunoRequest aluno = new AlunoRequest();
                aluno.matricula = resultSet.getString("matricula");
                aluno.nome = resultSet.getString("nome");
                listaAlunos.add(aluno);
            }
            return listaAlunos;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/buscar/{matricula}")
    public AlunoRequest buscar(@PathVariable String matricula) {

        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String selectSql = "SELECT matricula, nome FROM alunos WHERE matricula = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, matricula);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                AlunoRequest aluno = new AlunoRequest();
                aluno.matricula = resultSet.getString("matricula");
                aluno.nome = resultSet.getString("nome");

                return aluno;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/atualizar/{matricula}")
    public String atualizar(@PathVariable String matricula, @RequestBody AtualizarAlunoRequest dadosAtualizados) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String updateSql = "UPDATE alunos SET nome = ? WHERE matricula = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, dadosAtualizados.nome);
            preparedStatement.setString(2, matricula);

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas > 0) {
                return "Aluno atualizado com sucesso!";
            } else {
                return "Matrícula não encontrada.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao atualizar: " + e.getMessage();
        }
    }

    @DeleteMapping("/deletar/{matricula}")
    public String deletar(@PathVariable String matricula) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword)) {
            String deleteSql = "DELETE FROM alunos WHERE matricula = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setString(1, matricula);

            int linhasAfetadas = preparedStatement.executeUpdate();

            if (linhasAfetadas > 0) {
                return "Aluno deletado com sucesso!";
            } else {
                return "Matrícula não encontrada.";
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro ao deletar: " + e.getMessage();
        }
    }
}