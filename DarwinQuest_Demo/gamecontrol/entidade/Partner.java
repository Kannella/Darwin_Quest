package gamecontrol.entidade;

import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import gamecontrol.main.GamePanel;

public class Partner extends Entity {
    public Partner(GamePanel gp){
        super(gp);  
        direction = "down";
        speed = 4;
        getNPCImage();
 
    }
    public void getNPCImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet1Cima.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet2Cima.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet3Cima.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet4Cima.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet1Baixo.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet2Baixo.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet3Baixo.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet4Baixo.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet1Esquerda.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet2Esquerda.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet3Esquerda.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet4Esquerda.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet1Direita.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet2Direita.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet3Direita.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet4Direita.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setAction() {
        Random random= new Random();
        int i = random.nextInt(100)+1;
        actionLockCounter ++;
        if (actionLockCounter==50){
            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }

            actionLockCounter = 0;
        }
        
    }
}

