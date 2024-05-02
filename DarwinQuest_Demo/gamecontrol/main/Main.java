package gamecontrol.main;

import gamecontrol.login.TelaLogin;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Cria e exibe a tela de login
            TelaLogin telaLogin = new TelaLogin();
            telaLogin.setVisible(true);
            telaLogin.setLocationRelativeTo(null);

            // Configura um listener para detectar quando o login for bem-sucedido
            telaLogin.setLoginSuccessListener(() -> {
                // Fecha a tela de login
                telaLogin.dispose();
                
                // Cria e exibe a janela do jogo
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
            });
        });
    }
}
