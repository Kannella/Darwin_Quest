package gamecontrol.main;

//Classe feita para detectar a input do jogador, no caso, somente o teclado
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    
    GamePanel gp;
    //Declaração de variáveis
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    //Processamento
    public void keyTyped(KeyEvent e){

    }
    @Override
    public void keyPressed(KeyEvent e){          //Essa sessão checa se uma tecla de movimento esta sendo pressionada

        int code = e.getKeyCode();

        // chooseState
        if (gp.currentState.getGameState() == gp.currentState.chooseState) {
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 1;
                }
            }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) { // selecionando opção 1

                    gp.player.contador = 1;
                    gp.playMusic(0);
                    UI.tempoDecorrido = 0;
                    UI.iniciarTimer(gp);
                    gp.currentState.setPlayState();
                    gp.sManager.spriteSorteado1 = null;
                    gp.sManager.spriteSorteado2 = null;
                }
                if (gp.ui.commandNum == 1) { // selecionando opção 2

                    gp.player.contador = 2;
                    gp.playMusic(0);
                    UI.tempoDecorrido = 0;
                    UI.iniciarTimer(gp);
                    gp.currentState.setPlayState();
                    gp.sManager.spriteSorteado1 = null;
                    gp.sManager.spriteSorteado2 = null;
                }
            }
        }
         //playState
         if (gp.currentState.getGameState() == gp.currentState.playState || gp.currentState
                 .getGameState() == gp.currentState.pauseState) {
            if (code == KeyEvent.VK_W || code ==KeyEvent.VK_UP){
                upPressed = true;
            }
            if (code == KeyEvent.VK_A || code ==KeyEvent.VK_LEFT) {
            leftPressed = true;
            }
            if (code == KeyEvent.VK_S || code ==KeyEvent.VK_DOWN) {
                downPressed = true;
            }
            if (code == KeyEvent.VK_D || code ==KeyEvent.VK_RIGHT) {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_P) {
                if(gp.currentState.getGameState() == gp.currentState.playState){
                    gp.currentState.setPauseState();
                }
                else if(gp.currentState.getGameState() == gp.currentState.pauseState){
                    gp.currentState.setPlayState();
                }
            }if (code == KeyEvent.VK_M) {
                if(Sound.isPlaying == true){
                    Sound.isPlaying = false;
                    gp.stopMusic();
                }
                else if(Sound.isPlaying == false){
                    Sound.isPlaying = true;
                    gp.playMusic(0);
                }
           
            }if (code == KeyEvent.VK_K) {   //TIRAR ISSO NA VERSÃO FINAL
                if(gp.currentState.getGameState() == gp.currentState.playState){
                    gp.currentState.setGameOverState();
                }
            }
        }
    }
    public void keyReleased(KeyEvent e){ //Essa sessão checa se a tecla do evento anterior (de movimento) foi soltada
       
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W|| code ==KeyEvent.VK_UP){
            upPressed = false;
        }
        if (code == KeyEvent.VK_A || code ==KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_S || code ==KeyEvent.VK_DOWN) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_D || code ==KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }
    }
}
