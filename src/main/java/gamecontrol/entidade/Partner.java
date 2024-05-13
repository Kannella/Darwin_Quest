package gamecontrol.entidade;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import gamecontrol.main.GamePanel;
import gamecontrol.main.UtilityTool;

public class Partner extends Entity {
    Random random= new Random();
    public Partner(GamePanel gp){
        super(gp);  
        direction = "down";
        speed = 1;
        getNPCImage();
 
    }
    public void getNPCImage(){
         if (gp.currentMap == 0) {
            up1 = setup("preguica/preguicaCimaBase");
            up2 = setup("preguica/preguicaCima1");
            up3 = setup("preguica/preguicaCimaBase");
            up4 = setup("preguica/preguicaCima2");
            down1 = setup("preguica/preguicaBaixoBase");
            down2 = setup("preguica/preguicaBaixo1");
            down3 = setup("preguica/preguicaBaixoBase");
            down4 = setup("preguica/preguicaBaixo2");
            left1 = setup("preguica/preguicaEsquerdaBase");
            left2 = setup("preguica/preguicaEsquerda1");
            left3 = setup("preguica/preguicaEsquerdaBase");
            left4 = setup("preguica/preguicaEsquerda2");
            right1 = setup("preguica/preguicaDireitaBase");
            right2 = setup("preguica/preguicaDireita1");
            right3 = setup("preguica/preguicaDireitaBase");
            right4 = setup("preguica/preguicaDireita2");

        } else if (gp.currentMap == 1) {
            up1 = setup("besouro/besouroCimaBase");
            up2 = setup("besouro/besouroCima1");
            up3 = setup("besouro/besouroCimaBase");
            up4 = setup("besouro/besouroCima2");
            down1 = setup("besouro/besouroBaixoBase");
            down2 = setup("besouro/besouroBaixo1");
            down3 = setup("besouro/besouroBaixoBase");
            down4 = setup("besouro/besouroBaixo2");
            left1 = setup("besouro/besouroEsquerdaBase");
            left2 = setup("besouro/besouroEsquerda1");
            left3 = setup("besouro/besouroEsquerdaBase");
            left4 = setup("besouro/besouroEsquerda2");
            right1 = setup("besouro/besouroDireitaBase");
            right2 = setup("besouro/besouroDireita1");
            right3 = setup("besouro/besouroDireitaBase");
            right4 = setup("besouro/besouroDireita2");
        }
    }

   public BufferedImage setup(String imageName){

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {   
            image = ImageIO.read(getClass().getResourceAsStream("/res/individuos/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return image;
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

