package gamecontrol.entidade;

import gamecontrol.main.GamePanel;
import gamecontrol.entidade.SpriteManager;
import gamecontrol.main.UI;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

import gamecontrol.entidade.Player;

public class CurrentSprite {
    
    GamePanel gp;
    BufferedImage[] spriteRecebida = new BufferedImage[52]; //recebe a sprite escolhida
    int spriteNumber; //indice da sprite escolhida


    //construtor da classe
    public CurrentSprite(GamePanel gp) {
        
        this.gp = gp;
        // BufferedImage sprites[];
        // sprites = gp.sManager.lerSpritesheet("TodosBesourinhos.png", 46, 48);
        // // Configure as sprites com base na sprite inicial
        // gp.player.down1 = gp.sManager.setup(sprites[0]);
        // gp.player.down2 = gp.sManager.setup(sprites[1]); // Use a mesma sprite para down2
        // gp.player.down3 = gp.sManager.setup(sprites[0]);
        // gp.player.down4 = gp.sManager.setup(sprites[2]); // Use a mesma sprite para down4
    }


    //metodo que recebe sprite baseado se foi spriteSorteada1 ou spriteSorteada2
    public void getSpriteEscolhida1 (int spriteIndex){

        BufferedImage sprites[];
        sprites = gp.sManager.lerSpritesheet("TodosBesourinhos.png", 46, 48);
        spriteRecebida[spriteIndex] = gp.sManager.spriteSorteado1;
        spriteNumber = spriteIndex;
        setCurrentSprite(gp.player);
    }

    public void getSpriteEscolhida2 (int spriteIndex){

        BufferedImage sprites[];
        sprites = gp.sManager.lerSpritesheet("TodosBesourinhos.png", 46, 48);
        spriteRecebida[spriteIndex] = gp.sManager.spriteSorteado2;
        spriteNumber = spriteIndex;
        setCurrentSprite(gp.player);
    }

    // retorna o sprite atual do jogador
    public BufferedImage getCurrentSprite() {
        gp.player.getPlayerImage();
        return gp.player.down1;
    }

    // Define o sprite atual do jogador com base no estado atual do jogador
    public void setCurrentSprite(Player player) {
        // Obtenha o vetor de sprites do SpriteManager
        BufferedImage[] sprites = spriteRecebida;

        // Defina os sprites apropriados para cada estado do jogador
        player.setDown1(sprites[spriteNumber]);
        player.setDown2(sprites[spriteNumber + 1]);
        player.setDown3(sprites[spriteNumber]);
        player.setDown4(sprites[spriteNumber + 2]);
    }

    // // Calcula o índice do sprite com base no estado atual do jogador
    // private int calcularIndiceSprite(CurrentSpriteEnum currentSprite) {
    //     // Lógica para calcular o índice do sprite com base no estado atual do jogador
    //     // Retorne o índice correspondente ao estado atual do jogador
    //     return 0; // Substitua isso com sua lógica real
    // }

    // // Enumeração para representar os diferentes estados do jogador
    // public enum CurrentSpriteEnum {
    //     DOWN1,
    //     DOWN2,
    //     DOWN3,
    //     DOWN4
    // }

  
}
