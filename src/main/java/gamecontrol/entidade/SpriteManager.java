package gamecontrol.entidade;

import java.io.IOException;
import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import gamecontrol.main.GamePanel;

public class SpriteManager {
   
    

    GamePanel gp;

    //método que define qual pool de sprites usar em cada mapa
    public void currentAnimal(){
        if (gp.currentMap == 0){
            //gp.player.sprite = gp.aSetter.playerSprites;
            //gp.partner.sprite = gp.aSetter.partnerSprites;
            //gp.npc[0][0].sprite = gp.aSetter.npcSprites;
            //gp.enemy[0][0].sprite = gp.aSetter.enemySprites;
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
        // Número de colunas e linhas no spritesheet
        int numColunas = 7;
        int numLinhas = 8;

        // Calcula o número total de sprites
        int numSprites = numColunas * numLinhas;

        // Cria um array para armazenar as sprites individuais
        BufferedImage[] sprites = new BufferedImage[numSprites];

        // Divide o spritesheet em sprites individuais
        for (int linha = 0; linha < numLinhas; linha++) {
            for (int coluna = 0; coluna < numColunas; coluna++) {
                int index = linha * numColunas + coluna;
                sprites[index] = spritesheet.getSubimage(coluna * larguraSprite, linha * alturaSprite, larguraSprite, alturaSprite);
            }
        }

        return sprites;
    }

}
