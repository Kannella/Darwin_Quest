package gamecontrol.assets;

import java.sql.Connection;
import java.sql.DriverManager;
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

    public boolean autenticarUsuario(String email, String senha) {
    try (Connection conexao = obterConexao()) {
        if (conexao != null) {
            // Implemente a lógica de autenticação aqui
            return true; // Exemplo de retorno
        } else {
            return false;
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Erro ao autenticar usuário: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
        return false;
    }
}

}
