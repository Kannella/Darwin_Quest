package gamecontrol.entidade;

import java.io.IOException;

import javax.imageio.ImageIO;

import gamecontrol.main.GamePanel;

public class NPC extends Entity {
    public NPC(GamePanel gp){
        super(gp);  
        direction = "down";
        speed = 4;
 
    }
    public void getNPCImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet1Cima.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet2Cima.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet3Cima.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet4Cima.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet1Baixo.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet2Baixo.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet3Baixo.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet4Baixo.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet1Esquerda.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet2Esquerda.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet3Esquerda.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet4Esquerda.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet1Direita.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet2Direita.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet3Direita.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/res/individuos/Lancelet/Lancelet4Direita.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

