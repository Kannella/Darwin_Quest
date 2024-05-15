package gamecontrol.entidade;

import java.io.IOException;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import gamecontrol.main.GamePanel;

public class Sprite {

    GamePanel gp;

    //método que define qual pool de sprites usar em cada mapa
    public void currentAnimal(){
        if (gp.currentMap == 0){

        }
        if(gp.currentMap == 1){

        }
    }
    
    public BufferedImage[] lerSpritesheet(String nomeArquivo, int larguraSprite, int alturaSprite) {
        BufferedImage spritesheet = null;
        try {
            spritesheet = ImageIO.read(getClass().getResourceAsStream("/res/Animais/spriteSheet" + nomeArquivo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Calcula o número de sprites horizontalmente
        int numSpritesHorizontal = spritesheet.getWidth() / larguraSprite;

        // Cria um array para armazenar as sprites individuais
        BufferedImage[] sprites = new BufferedImage[numSpritesHorizontal];

        // Divide o spritesheet em sprites individuais
        for (int i = 0; i < numSpritesHorizontal; i++) {
            sprites[i] = spritesheet.getSubimage(i * larguraSprite, 0, larguraSprite, alturaSprite);
        }

        return sprites;
    }

}
