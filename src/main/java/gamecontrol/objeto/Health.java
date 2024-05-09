package gamecontrol.objeto;
import java.io.IOException;
import javax.imageio.ImageIO;

import gamecontrol.main.GamePanel;

public class Health extends SuperObject {

     GamePanel gp;
    public Health(GamePanel gp) {
        this.gp = gp;
        this.name = "Coração";

        try {
            
        imagem = ImageIO.read(getClass().getResourceAsStream("kisspng-heart-pixel-art-clip-art-pixel-5abd97e2c49a13.6923008715223746268053.jpg"));
        imagem2 = ImageIO.read(getClass().getResourceAsStream("kisspng-heart-pixel-art-clip-art-pixel-5abd97e2c49a13.6923008715223746268053.jpg"));
   
    } catch (IOException e) {
            e.printStackTrace();
        }
    }
}