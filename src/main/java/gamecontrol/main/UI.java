package gamecontrol.main;

//Classe feita para gerenciar UI no jogo, como textos
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import gamecontrol.objeto.ObjHeart;
import gamecontrol.objeto.SuperObject;


public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
    BufferedImage fullHeart, emptyHeart;
    double playTime; //possivel contagem de tempo


    public UI (GamePanel gp){
        this.gp = gp;

        arial_40 = new Font("verdana", Font.PLAIN, 40);
        
        //criando objeto HUD
        SuperObject heart = new ObjHeart();
        fullHeart = heart.image;
        emptyHeart = heart.image2;

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
}
