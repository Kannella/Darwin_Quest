package gamecontrol.objeto;

//Essa é uma classe de um dos objetos do jogo
import java.io.IOException;
import javax.imageio.ImageIO;


public class ObjAmebinha extends SuperObject{
   
    public ObjAmebinha (){

        name = "Amebinha";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/amebinhaBaixoTeste.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
