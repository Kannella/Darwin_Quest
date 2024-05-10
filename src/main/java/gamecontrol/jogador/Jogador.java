package gamecontrol.jogador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gamecontrol.assets.ConexaoBD;

public class Jogador {
    private String nome;
    private String email;
    private String senha;

    public Jogador(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void cadastrar() throws SQLException {
        Connection conexao = null;
        try {
            conexao = ConexaoBD.obterConexao();
            String sql = "INSERT INTO jogadores (nome, email, senha) VALUES (?, ?, ?)";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.executeUpdate();
            System.out.println("Jogador cadastrado com sucesso!");
        } finally {
            if (conexao != null) {
                conexao.close();
            }
        }
    }
}
