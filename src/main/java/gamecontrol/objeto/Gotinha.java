package gamecontrol.objeto;

//Essa é uma classe de um dos objetos do jogo
import java.io.IOException;
import javax.imageio.ImageIO;

import gamecontrol.main.GamePanel;


public class Gotinha extends SuperObject {
    
    GamePanel gp;

    public Gotinha (GamePanel gp){
        this.gp = gp;
        name = "Aguinha";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objetos/FolinhaHMM.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
