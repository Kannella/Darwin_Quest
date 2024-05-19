package gamecontrol.objeto;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import gamecontrol.main.GamePanel;
import gamecontrol.main.UtilityTool;


public class ObjHeart extends SuperObject {
    GamePanel gp;

    public ObjHeart(GamePanel gp) {
        this.gp = gp;
        name = "Heart";
        try {
            BufferedImage fullHeartImage = ImageIO.read(getClass().getResourceAsStream("/res/objetos/FullHeart.png"));
            BufferedImage halfHeartImage = ImageIO.read(getClass().getResourceAsStream("/res/objetos/HalfHeart.png"));
            BufferedImage emptyHeartImage = ImageIO.read(getClass().getResourceAsStream("/res/objetos/EmptyHeart--'.png"));

            // Redimensiona as imagens
            BufferedImage scaledFullHeart = UtilityTool.scaleImage(fullHeartImage, gp.tileSize, gp.tileSize);
            BufferedImage scaledHalfHeart = UtilityTool.scaleImage(halfHeartImage, gp.tileSize, gp.tileSize);
            BufferedImage scaledEmptyHeart = UtilityTool.scaleImage(emptyHeartImage, gp.tileSize, gp.tileSize);

            // Atribui as imagens redimensionadas aos campos correspondentes no SuperObject
            image = scaledFullHeart;
            image2 = scaledHalfHeart;
            image3 = scaledEmptyHeart;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

