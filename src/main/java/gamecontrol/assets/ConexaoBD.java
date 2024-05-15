package gamecontrol.assets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoBD {

    private String host = "mysql-145c4b49-gi-3b93.b.aivencloud.com";
    private String port = "11568";
    private String db = "darwin_quest";
    private String user = "avnadmin";
    private String password = "AVNS_CRH9ORIJ8fU0l0m07wv";

    public Connection obterConexao() {
        try {
            var stringConexao = String.format("jdbc:mysql://%s:%s/%s", host, port, db);
            Connection conexao = DriverManager.getConnection(stringConexao, user, password);
            return conexao;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falhou. Tente novamente mais tarde!");
            e.printStackTrace();
            return null;
        }
    }

    public static void fecharConexao(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar a conexão com o banco de dados!");
                e.printStackTrace();
            }
        }
    }

    public String autenticarUsuario(String email, String senha) {
        String queryEmail = "SELECT email FROM jogador WHERE email = ?";
        String querySenha = "SELECT * FROM jogador WHERE email = ? AND senha = ?";
        
        try (Connection conexao = obterConexao();
             PreparedStatement stmtEmail = conexao.prepareStatement(queryEmail);
             PreparedStatement stmtSenha = conexao.prepareStatement(querySenha)) {
            
            // Verifica se o email existe
            stmtEmail.setString(1, email);
            try (ResultSet rsEmail = stmtEmail.executeQuery()) {
                if (!rsEmail.next()) {
                    return "Email não existe!";
                }
            }
            
            // Verifica se a senha está correta
            stmtSenha.setString(1, email);
            stmtSenha.setString(2, senha);
            try (ResultSet rsSenha = stmtSenha.executeQuery()) {
                if (rsSenha.next()) {
                    return "Autenticado com sucesso!"; // Usuário autenticado com sucesso
                } else {
                    return "Senha incorreta!";
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao autenticar usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return "Erro de autenticação";
        }
    }
}


