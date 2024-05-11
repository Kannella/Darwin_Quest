package gamecontrol.main;

//Classe feita para gerenciar UI no jogo, como textos
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gamecontrol.objeto.ObjHeart;
import gamecontrol.objeto.SuperObject;

import java.util.Timer;
import java.util.TimerTask;


public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
    BufferedImage fullHeart, halfHeart, emptyHeart;
    
    private Timer timer; //esse objeto cria um "relogio" que fica de fundo
    private TimerTask timerTask;  //esse objeto pode ser chamada pra executar uma comando ou varios comandos repitidos em função de um timer
    private boolean running;   //variavel que verifica se o timer esta ativo
    private long tempoDecorrido; //variavel que vai segurar o tempo que o jogador jogou o nivel



    public UI (GamePanel gp){
        this.gp = gp;

        arial_40 = new Font("verdana", Font.PLAIN, 40);
        
        //criando objeto HUD
        SuperObject heart = new ObjHeart();
        fullHeart = heart.image;
        fullHeart = heart.image2;
        emptyHeart = heart.image3;

        //criando timer
        timer = new Timer();
        running = false;
        tempoDecorrido = 0;

        iniciarTimer(gp);

    }

    public void draw (Graphics2D g2){

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        if(gp.gameState == gp.gameOverState){
            drawGameOverScreen();
        }
    }

    public void drawPlayerLife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        while(i < gp.player.maxLife/2){
            g2.drawImage(emptyHeart, x, y, null);
            i++;
            x += gp.tileSize;
        }
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "Pausado";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }
    public int getXforCenteredText(String text){ //método pra ser chamado toda vez que centralização necessária
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public void iniciarTimer(GamePanel gp) {
        if (!running) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (gp.gameState == gp.pauseState){
                        System.out.println("pausado");
                    }else if(gp.gameState == gp.gameOverState){
                        pararTimer();
                    }
                    
                    else{
                        tempoDecorrido += 1000; //A CADA MIL É 1 SEGUNDO
                        System.out.println("Tempo decorrido: " + tempoDecorrido / 1000 + " segundos");
                    }
                    if (tempoDecorrido >= 60 * 1000) {
                        System.out.println("Possivel reproduzir");
                    }
                    if (tempoDecorrido >= 3 * 60 * 1000) {
                        pararTimer();
                        System.out.println("tempo esgotado");
                        gp.gameState = gp.gameOverState;
                    }
                }
            };

            timer.scheduleAtFixedRate(timerTask, 0, 1000);
            running = true;
        } else {
            System.out.println("O timer já está em execução.");
        }
    }

    public void pararTimer() {
        if (running) {
            timerTask.cancel();
            running = false;
            System.out.println("Timer parado.");
        } else {
            System.out.println("O timer já está parado.");
        }
    }

    public void drawGameOverScreen(){


        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 100F));

        text = "Você Morreu";
        g2.setColor(Color.black);
        x = getXforCenteredText(text);
        y = gp.tileSize*4;
        g2.drawString(text, x, y);

        g2.setColor(Color.white);
        g2.drawString(text, x-4, y-4);

        //Tentar novamente

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
        text = "Tentar novamente";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);

        //Voltar para a tela de titulo

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
        text = "Menu";
        x = getXforCenteredText(text);
        y += 55;
        g2.drawString(text, x, y);
        
    }
}
