package gamecontrol.objeto;

//Essa é uma classe de um dos objetos do jogo
import java.io.IOException;
import javax.imageio.ImageIO;


public class ObjAlga extends SuperObject {
    
    public ObjAlga (){

        name = "Alga";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objetos/Alga.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
