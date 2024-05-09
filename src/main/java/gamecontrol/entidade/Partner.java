package gamecontrol.entidade;

import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import gamecontrol.main.GamePanel;

public class Partner extends Entity {
    Random random= new Random();
    public Partner(GamePanel gp){
        super(gp);  
        direction = "down";
        speed =random.nextInt(3,5);
        getNPCImage();
 
    }
    public void getNPCImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/preguica/preguicaCimaBase.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/preguica/preguicaCima1.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/preguica/preguicaCimaBase.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/preguica/preguicaCima2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/preguica/preguicaBaixoBase.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/preguica/preguicaBaixo1.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/preguica/preguicaBaixoBase.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/preguica/preguicaBaixo2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/preguica/preguicaEsquerdaBase.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/preguica/preguicaEsquerda1.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/preguica/preguicaEsquerdaBase.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/preguica/preguicaEsquerda2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/preguica/preguicaDireitaBase.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/preguica/preguicaDireita1.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/preguica/preguicaDireitaBase.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/preguica/preguicaDireita2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setAction() {
        int i = random.nextInt(125)+1;
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
            }if(i > 100 && i <= 125){
            }

            actionLockCounter = 0;
        }
        
    }
}

