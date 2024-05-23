package gamecontrol.objeto;

//Essa é uma classe de um dos objetos do jogo
import java.io.IOException;
import javax.imageio.ImageIO;


public class Gotinha extends SuperObject {
    
    public Gotinha (){

        name = "Aguinha";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objetos/GotaDAgua.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
