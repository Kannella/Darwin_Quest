package gamecontrol.objeto;

import java.io.IOException;
import javax.imageio.ImageIO;


public class ObjHeart extends SuperObject{
        public ObjHeart(){

        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objetos/FullHeart.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/res/objetos/HalfHeart.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/res/objetos/EmptyHeart--'.png")); //D: sad reactions only
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

