package gamecontrol.entidade;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import gamecontrol.main.GamePanel;

public class Enemy extends Entity{
    Random random = new Random();
    public Enemy (GamePanel gp){
        super(gp);
        direction = "down";
        speed =random.nextInt(3,5);
        getEnemyImage();

    }
    public void getEnemyImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaParadaCima.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaPasso1Cima.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaParadaCima.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaPasso2Cima.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaParadaBaixo.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaPasso1Baixo.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaParadaBaixo.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaPasso2Baixo.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaAndandoEsquerdaDefault.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaAndandoEsquerdaPasso1.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaAndandoEsquerdaDefault.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaAndandoEsquerdaPasso2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaAndandoDireitaDefault.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaAndandoDireitaPasso1.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaAndandoDireitaDefault.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaAndandoDireitaPasso2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
        public void setAction() {
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
        // public boolean enemyNear(){
        //     if (collisionOn) {
        //         return true;  
        //     }else{
        //         return false;
        //     }
        // }
    }



