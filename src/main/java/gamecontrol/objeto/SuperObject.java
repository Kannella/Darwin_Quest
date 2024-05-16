package gamecontrol.objeto;

//SUPEEEEER essa classe seta variaveis dos objetos, inclusive o retangulo invisivel de colisão deles
import gamecontrol.main.GamePanel;
import gamecontrol.main.UtilityTool;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;


public class SuperObject {
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp){

        int screenX = worldX - gp.getPlayer().worldX + gp.getPlayer().screenX;
        int screenY = worldY - gp.getPlayer().worldY + gp.getPlayer().screenY;

        if(worldX + gp.tileSize > gp.getPlayer().worldX - gp.getPlayer().screenX && worldX - gp.tileSize < gp.getPlayer().worldX + gp.getPlayer().screenX &&
            worldY + gp.tileSize > gp.getPlayer().worldY - gp.getPlayer().screenY && worldY - gp.tileSize < gp.getPlayer().worldY + gp.getPlayer().screenY){
                
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }         
    }
}
