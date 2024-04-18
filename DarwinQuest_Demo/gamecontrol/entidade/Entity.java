package gamecontrol.entidade;

//Essa classe é mais para instanciar e armazenar as variaveis do jogador e futuramente inimigos até se necessário
import java.awt.image.BufferedImage;
import java.awt.Rectangle;


public class Entity {
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2, right3, right4 ;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public Rectangle solidArea;
    public boolean collisionOn = false;
}
