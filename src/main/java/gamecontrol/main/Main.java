package gamecontrol.main;

import br.maua.teste.TelaInicial;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
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
