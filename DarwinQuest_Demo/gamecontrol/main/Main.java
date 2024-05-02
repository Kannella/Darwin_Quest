package gamecontrol.main;

import javax.swing.*;
import gamecontrol.login.TelaLogin;
import gamecontrol.login.TelaLogin.LoginSuccessListener;

public class Main {
    public static void main(String[] args) {
        //SwingUtilities.invokeLater é um método estático da classe SwingUtilities que é usado para executar o código na thread de despacho de eventos do Swing. Isso é necessário porque o Swing é uma biblioteca gráfica baseada em eventos, e todas as operações de interface do usuário devem ser feitas na thread de despacho de eventos para garantir a segurança e a consistência do aplicativo.

        SwingUtilities.invokeLater(() -> { 

            TelaLogin telaLogin = new TelaLogin(); // Cria uma nova instância da tela de login
            telaLogin.setVisible(true); // Exibe a tela de login
            telaLogin.setLocationRelativeTo(null); // Centraliza a tela de login na tela do computador

            /*
            Em seguida, definimos um ouvinte para o evento de sucesso de login usando o método setLoginSuccessListener(). Quando o login for bem-sucedido, o método onLoginSuccess() será chamado, e a parte do código dentro deste método será executada.
            Na implementação do método onLoginSuccess(), imprimimos uma mensagem de confirmação e depois chamamos o método abrirJanelaDoJogo() para abrir a janela do jogo. 
            */

            // Define um ouvinte para o evento de sucesso de login
            telaLogin.setLoginSuccessListener(
                new LoginSuccessListener() {
                    @Override // Sobrescreve o método onLoginSuccess() da interface LoginSuccessListener

                    public void onLoginSuccess() {
                        // Quando o login for bem-sucedido, esta parte do código será executada
                        System.out.println("Login bem-sucedido! Abrindo a janela do jogo...");
                        
                        // Método para abrir a janela do jogo
                        abrirJanelaDoJogo();
                    }
                }
            );

        });
    }

    // Método para abrir a janela do jogo
    private static void abrirJanelaDoJogo() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Darwin Quest V0.2");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
}
