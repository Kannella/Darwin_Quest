package gamecontrol.main;

import java.awt.Window;
//Classe feita para detectar a input do jogador, no caso, somente o teclado
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import br.maua.teste.DialogoExplicacao2;
import br.maua.teste.DialogoExplicacao3;
import br.maua.teste.DialogoExplicacao4;
import gamecontrol.main.DarwinControler;

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
    public void keyPressed(KeyEvent e){  //Essa sessão checa se uma tecla de movimento esta sendo pressionada

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
                    //preciso que ele pegue o index do sprite sorteado e se for igual a uma sprite especifica, de index 53 ele chama um setFinalState
                    if(gp.sManager.spriteSorteado1Index == 53 || gp.sManager.spriteSorteado1Index == 18 || gp.sManager.spriteSorteado1Index == 36){
                        gp.currentState.setFinalState();
                    }
                    else{
                        //sequencia de fatores de quando o player escolhe a opção 1:
                        //atribui ao contador que vai chamar no novo player
                        gp.sManager.contador = 1;
                        sequenciaDoChoose();
                    }
                }
                if (gp.ui.commandNum == 1) { // selecionando opção 2
                    if(gp.sManager.spriteSorteado2Index == 53 || gp.sManager.spriteSorteado2Index == 18 || gp.sManager.spriteSorteado2Index == 36){
                        gp.currentState.setFinalState();
                    }
                    else{
                        // sequencia de fatores de quando o player escolhe a opção 2:
                        // atribui ao contador do player o valor 2
                        gp.sManager.contador = 2;
                        sequenciaDoChoose();
                    }
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
                    UtilityTool.settocaMusica(false);
                }
                else if(Sound.isPlaying == false){
                    Sound.isPlaying = true;
                    UtilityTool.settocaMusica(true);
                    gp.playMusic(0);
                }
           
            }if (code == KeyEvent.VK_K) {   //TIRAR ISSO NA VERSÃO FINAL
                if(gp.currentState.getGameState() == gp.currentState.playState){
                    gp.currentState.setGameOverState();
                }
            }if(code == KeyEvent.VK_SHIFT){
                gp.player.dash();
            }
        }
    }
    private void sequenciaDoChoose(){
        // toca a música                                                
        gp.stopMusic();
        gp.playMusic(0);
        // Reseta e inicia o timer
        UI.tempoDecorrido = 0;
        UI.iniciarTimer(gp);
        // Destrói player
        gp.disposePlayer();
        //Faz outro player
        gp.createPlayer();
        //Chama a explicacao e ela chama o play state
        if(gp.dControler.getCruzamentos()==1){
            // PrimeiroDialogoExplicacaoJogo primeiro=new PrimeiroDialogoExplicacaoJogo();
            // primeiro.setVisible(true);
            // primeiro.setLocationRelativeTo(null);
            DialogoExplicacao2 d2= new DialogoExplicacao2(gp);
            d2.setVisible(true);
            d2.setLocationRelativeTo(null);
        }
        if(gp.dControler.getCruzamentos()==2){
            DialogoExplicacao3 d3= new DialogoExplicacao3(gp);
            d3.setVisible(true);
            d3.setLocationRelativeTo(null);
        }
        if(gp.dControler.getCruzamentos()==3){
            DialogoExplicacao4 d4 = new DialogoExplicacao4(gp);
            d4.setVisible(true);
            d4.setLocationRelativeTo(null);
        }
        else{
            System.out.println("Valor acima ou abaixo de esperado");
        }
        // Sorteia os sprites de novo para o player escolher

        // Muda o estado do jogo para playState
        // gp.currentState.setPlayState();
        Window w = javax.swing.SwingUtilities.getWindowAncestor(gp);
        w.setVisible(false);
        gp.sManager.spriteSorteado1 = null;
        gp.sManager.spriteSorteado2 = null;
        gp.sManager.sortearSprites();
        UtilityTool.setAcabouLer(false);
        // UtilityTool.setAcabouDeLer(false);

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
