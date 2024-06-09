package gamecontrol.main;

import br.maua.teste.TelaInicial;
import gamecontrol.assets.ConexaoBD;
import gamecontrol.login.Login;

import java.sql.Connection;

import javax.swing.*;


public class Main {
    JFrame window = new JFrame();
    public static void main(String[] args) {

        ConexaoBD conexaoBD = new ConexaoBD();
        
        Connection conexao = null;
        
            conexao = conexaoBD.obterConexao();
            
            if (conexao != null) {
                System.out.println("Conexão com o banco de dados estabelecida!");
                // Faça operações com o banco de dados aqui...
            } else {
                System.out.println("Não foi possível estabelecer a conexão com o banco de dados!");
            }
        
            ConexaoBD.fecharConexao(conexao);
        

        SwingUtilities.invokeLater(() -> { 

            Login login = new Login(); // Cria uma nova instância da tela de login
            login.setVisible(true); // Exibe a tela de login
            login.setLocationRelativeTo(null); // Centraliza a tela de login na tela do computador

            /*
            Em seguida, definimos um ouvinte para o evento de sucesso de login usando o método setLoginSuccessListener(). Quando o login for bem-sucedido, o método onLoginSuccess() será chamado, e a parte do código dentro deste método será executada.
            Na implementação do método onLoginSuccess(), imprimimos uma mensagem de confirmação e depois chamamos o método abrirJanelaDoJogo() para abrir a janela do jogo. 
            */

            // Define um ouvinte para o evento de sucesso de login
            login.setLoginSuccessListener(
                new Login.LoginSuccessListener() {
                    @Override // Sobrescreve o método onLoginSuccess() da interface LoginSuccessListener

                    public void onLoginSuccess() {
                        // Quando o login for bem-sucedido, esta parte do código será executada
                        System.out.println("Login bem-sucedido! Abrindo a janela do jogo...");

                        abrirTelaInical();
                        
                    }
                }
                );
                
            });
        }
        
        private static int GameStage;   //Estagio inicial
        
        public static int getGameStage() {
            return GameStage;
        }
        public static void setGameStage(int gameStage) {
            GameStage = gameStage;
        }
        
        
        public static void abrirTelaInical(){
        // Método para abrir a janela do jogo
        TelaInicial telaInicial = new TelaInicial();
        // Método para abrir a janela de login
        telaInicial.setVisible(true);
        telaInicial.setLocationRelativeTo(null);

    }

    // Método para abrir a janela do jogo
    public static void abrirJanelaDoJogo() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Darwin Quest V0.8");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();


        
    }

}
