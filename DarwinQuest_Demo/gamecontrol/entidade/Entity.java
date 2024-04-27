package gamecontrol.entidade;

//Essa classe é mais para instanciar e armazenar as variaveis do jogador e futuramente inimigos até se necessário
import java.awt.image.BufferedImage;

import gamecontrol.main.GamePanel;

import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Entity {
    GamePanel gp;
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2, right3, right4 ;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNumber = 1;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public Rectangle solidArea = new Rectangle(0,0,48,48); //Esse o tamanho default para todas as entidades
    public boolean collisionOn = false;

    public Entity(GamePanel gp) {
        this.gp = gp;
    }
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
            worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                
                switch (direction) {
                    case "up":
                        if (spriteNumber == 1){
                            image = up1;
                        }
                        if (spriteNumber == 2){
                            image = up2;
                        }
                        if (spriteNumber == 3){
                            image = up3;
                        }    
                        if (spriteNumber == 4){
                            image = up4;
                        }        
                        break;
                    case "down":
                        if (spriteNumber == 1){
                            image = down1;
                        }
                        if (spriteNumber == 2){
                            image = down2;
                        }
                        if (spriteNumber == 3) {
                            image = down3;
                        }
                        if (spriteNumber == 4) {
                            image = down4;
                        }
                        break;
                    case "left":
                        if (spriteNumber == 1) {
                            image = left1;
                        }
                        if (spriteNumber == 2) {
                            image = left2;
                        }
                        if (spriteNumber == 3) {
                            image = left3;
                        }
                        if (spriteNumber == 4) {
                            image = left4;
                        }
                        break;
                    case "right":
                        if (spriteNumber == 1) {
                            image = right1;
                        }
                        if (spriteNumber == 2) {
                            image = right2;
                        }
                        if (spriteNumber == 3) {
                            image = right3;
                        }
                        if (spriteNumber == 4) {
                            image = right4;
                        }
                        break;    
                }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }         
    }
}
