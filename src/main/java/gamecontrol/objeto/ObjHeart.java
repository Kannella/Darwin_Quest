package gamecontrol.objeto;

import java.io.IOException;
import javax.imageio.ImageIO;


public class ObjHeart extends SuperObject{
        public ObjHeart(){

        name = "Heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objetos/Alga.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/res/objetos/Alga.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

