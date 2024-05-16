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
    public Rectangle solidArea = new Rectangle(0,0,30,30); //Esse o tamanho default para todas as entidades
    public boolean collisionOn = false;
    public int actionLockCounter=0;
    public boolean invencible = false;
    public int invencibleCounter = 0;
    public int type;  // 0=player, 1=Partner, 2=Enemy

    //Status do Personagem
    public int maxLife;
    public int life;
    


    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void setAction(){    
    }

    public void update(){
        setAction();
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.enemy);
        boolean contactPlayer= gp.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer==true){
            
        }
        if(collisionOn == false){
                
            switch(direction){
                case "up":
                    worldY = worldY - speed;
                    break;
                case "down":
                    worldY = worldY + speed;
                    break;
                case "left":
                    worldX = worldX - speed;
                    break;
                case "right":
                    worldX = worldX + speed;
                    break;            
            }
        }

        spriteCounter++;
        if (spriteCounter > 10) {
        spriteNumber = (spriteNumber % 4) + 1; // Ciclar entre 1 e 4
        spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2){
        BufferedImage image = null;
        int screenX = worldX - gp.getPlayer().worldX + gp.getPlayer().screenX;
        int screenY = worldY - gp.getPlayer().worldY + gp.getPlayer().screenY;

        if(worldX + gp.tileSize > gp.getPlayer().worldX - gp.getPlayer().screenX && worldX - gp.tileSize < gp.getPlayer().worldX + gp.getPlayer().screenX &&
            worldY + gp.tileSize > gp.getPlayer().worldY - gp.getPlayer().screenY && worldY - gp.tileSize < gp.getPlayer().worldY + gp.getPlayer().screenY){
                
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
