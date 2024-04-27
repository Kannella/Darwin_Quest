package gamecontrol.main;

//Essa é a nossa classe main, responsavel pela tela aparecer e tudo(como por exemplo lidar com o movimento na tela, rezize, fullscreen e tal)
import javax.swing.JFrame;


public class Main {
    public static void main(String [] args){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //fecha de forma certa o programa
        window.setResizable(false);              //Permite a mudança de tamanho da janela, deixem em false se não da pra ver renderizando os tiles
        window.setTitle("Darwin Quest V0.2");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();   //Chama o metodo na classe gamePanel que prepara o jogo
        gamePanel.startGameThread(); //Chama o metodo na classe gamePanel que Inicia o jogo

    }
}