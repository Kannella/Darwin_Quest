package gamecontrol.entidade;

import java.io.IOException;
import java.util.Random;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import gamecontrol.main.GamePanel;
import gamecontrol.main.UtilityTool;

public class SpriteManager {

    public BufferedImage spriteSorteado1; // variável para armazenar o primeiro sprite sorteado
    public BufferedImage spriteSorteado2; // variável para armazenar o segundo sprite sorteado
    public static int spriteSorteado1Index; // índice do primeiro sprite sorteado
    public static int spriteSorteado2Index; // índice do segundo sprite sorteado
    public int contador = 0; // contador para controlar a troca de sprites

    BufferedImage[] sprites;

    GamePanel gp;

    public SpriteManager(GamePanel gp) {
        this.gp = gp;
        sortearSprites();
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
                sprites[index] = spritesheet.getSubimage(coluna * larguraSprite, linha * alturaSprite, larguraSprite,
                        alturaSprite);
            }
        }

        return sprites;
    }

    public void sortearSprites() {
        BufferedImage[] sprites;
        BufferedImage[] spritesSorteadas;

        if (gp.currentMap == 0) {
            sprites = lerSpritesheet("TodosBesourinhos.png", 46, 48);
                spritesSorteadas = new BufferedImage[] { sprites[0], sprites[3], sprites[6], sprites[9], sprites[12],
                sprites[15],
                sprites[18], sprites[21], sprites[24], sprites[27], sprites[30], sprites[33], sprites[36],
                sprites[37],
                sprites[40], sprites[43],sprites[46], sprites[49], sprites[52]};

            // Sorteia as sprites baseado no animal escolhido
            if (gp.player == null) { //tratamento de iniciação do player
                spritesSorteadas = new BufferedImage[] { sprites[3], sprites[21], sprites[37] };
            }
            else if(gp.player.getPlayerSpriteIndex() == 0){ //caso player for animal base sorteia essas sprites
                spritesSorteadas = new BufferedImage[] { sprites[3], sprites[21], sprites[37] };
            }
            else if (gp.player.getPlayerSpriteIndex()== 3) { 
                spritesSorteadas = new BufferedImage[] { sprites[3], sprites[9], sprites[12] };
            }
            else if (gp.player.getPlayerSpriteIndex() == 12) { // 18 é sprite de objetivo
                spritesSorteadas = new BufferedImage[] { sprites[9], sprites[12], sprites[15], sprites[18] };
            }
            else if(gp.player.getPlayerSpriteIndex() == 21){
                spritesSorteadas = new BufferedImage[] { sprites[21], sprites[24], sprites[27]}; 
            }
            else if(gp.player.getPlayerSpriteIndex() == 27){ // 36 é sprite de objetivo
                spritesSorteadas = new BufferedImage[] { sprites[27], sprites[30], sprites[33], sprites[36]}; 
            }
            else if(gp.player.getPlayerSpriteIndex() == 38){
                spritesSorteadas = new BufferedImage[] { sprites[38], sprites[41], sprites[44] }; 
            }
            else if(gp.player.getPlayerSpriteIndex() == 44){ //53 é sprite de objetivo
                spritesSorteadas = new BufferedImage[] { sprites[44], sprites[47], sprites[50], sprites[53] }; 
            }

        } else if (gp.currentMap == 1) {
            sprites = lerSpritesheet("TodosPassarinhos.png", 46, 48);
            spritesSorteadas = new BufferedImage[] { sprites[1], sprites[4], sprites[7], sprites[10], sprites[13],
                    sprites[16],
                    sprites[19], sprites[22], sprites[25], sprites[28], sprites[31], sprites[34], sprites[37],
                    sprites[40],
                    sprites[43], sprites[46] };
        } else {
            return;
        }

        // Sorteia os sprites uma vez na inicialização do método
        spriteSorteado1 = gachaSprite(spritesSorteadas);
        spriteSorteado1Index = getSpriteIndex(spriteSorteado1, sprites);

        do {
            spriteSorteado2 = gachaSprite(spritesSorteadas);
            spriteSorteado2Index = getSpriteIndex(spriteSorteado2, sprites);
        } while (spriteSorteado2 == spriteSorteado1); // Garante que spriteSorteado2 seja diferente de spriteSorteado1
    }

    public int getSpriteIndex(BufferedImage sprite, BufferedImage[] sprites) {
        for (int i = 0; i < sprites.length; i++) {
            if (sprites[i] == sprite) {
                return i;
            }
        }
        return -1; // Retorna -1 se a sprite não for encontrada no array
    }

    // Método para sortear uma sprite
    public BufferedImage gachaSprite(BufferedImage[] spritesSorteadas) {
        Random random = new Random();
        int indice = random.nextInt(spritesSorteadas.length);
        return spritesSorteadas[indice];
    }

    // Método para configurar escalonamento de uma sprite
    public BufferedImage setup(BufferedImage sprite) {
        return UtilityTool.scaleImage(sprite, gp.tileSize, gp.tileSize);
    }

    // Método para rotacionar uma sprite
    public BufferedImage rotateSprite(BufferedImage sprite, double angle) {
        int width = sprite.getWidth();
        int height = sprite.getHeight();

        BufferedImage rotatedImage = new BufferedImage(width, height, sprite.getType());

        // Rotaciona a imagem
        AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(angle), width / 2, height / 2);
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);
        rotatedImage = op.filter(sprite, rotatedImage);

        return rotatedImage;
    }
}


