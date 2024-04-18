package gamecontrol.main;

//Essa é a nossa classe main, responsavel pela tela aparecer e tudo
import javax.swing.JFrame;


public class Main {
    public static void main(String [] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("DarwinQuestTest");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

    }
}