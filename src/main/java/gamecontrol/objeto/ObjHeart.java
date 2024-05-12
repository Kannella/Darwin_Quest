package gamecontrol.objeto;

import java.io.IOException;
import javax.imageio.ImageIO;

import gamecontrol.main.GamePanel;


public class ObjHeart extends SuperObject{
    GamePanel gp;
        public ObjHeart(GamePanel gp){
            this.gp = gp;
            name = "Heart";
            try{
                image = ImageIO.read(getClass().getResourceAsStream("/res/objetos/FullHeart.png"));
                image2 = ImageIO.read(getClass().getResourceAsStream("/res/objetos/HalfHeart.png"));
                image3 = ImageIO.read(getClass().getResourceAsStream("/res/objetos/EmptyHeart--'.png")); //D: sad reactions only

                image=uTool.scaleImage(image, gp.tileSize, gp.tileSize);
                image2=uTool.scaleImage(image2, gp.tileSize, gp.tileSize);
                image3=uTool.scaleImage(image3, gp.tileSize, gp.tileSize);
            }catch(IOException e){
                e.printStackTrace();
            }
    }
}

