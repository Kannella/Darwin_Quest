package gamecontrol.entidade;

import gamecontrol.main.GamePanel;
import gamecontrol.main.KeyHandler;
import gamecontrol.main.UtilityTool;
import gamecontrol.main.UI;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;


//Essa classe é responsavel pelo jogador, ela puxa e funciona com as variaveis criadas na Entidade
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Rectangle;
import javax.imageio.ImageIO;


public class Player extends Entity{

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){

        super(gp);
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2); 
        screenY = gp.screenHeight/2 - (gp.tileSize/2) ;
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 12;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 26;
        solidArea.height = 26;
        
        
        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues(){
        
        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 15;
        speed = 5;
        direction = "down";
        
        //Status do jogador
        maxLife = 6;
        life = maxLife;
    }
    
    public void getPlayerImage() {
        if (gp.currentMap == 0) {
            up1 = setup("preguica/preguicaCimaBase");
            up2 = setup("preguica/preguicaCima1");
            up3 = setup("preguica/preguicaCimaBase");
            up4 = setup("preguica/preguicaCima2");
            
            left1 = rotateSprite(up1, -90);
            left2 = rotateSprite(up2, -90);
            left3 = rotateSprite(up3, -90);
            left4 = rotateSprite(up4, -90);
            
            down1 = rotateSprite(up1, 180);
            down2 = rotateSprite(up2, 180);
            down3 = rotateSprite(up3, 180);
            down4 = rotateSprite(up4, 180);
            
            right1 = rotateSprite(up1, 90);
            right2 = rotateSprite(up2, 90);
            right3 = rotateSprite(up3, 90);
            right4 = rotateSprite(up4, 90);
            
        } else if (gp.currentMap == 1) {
            up1 = setup("besouro/besouroCimaBase");
            up2 = setup("besouro/besouroCima1");
            up3 = setup("besouro/besouroCimaBase");
            up4 = setup("besouro/besouroCima2");
            
            left1 = rotateSprite(up1, -90);
            left2 = rotateSprite(up2, -90);
            left3 = rotateSprite(up3, -90);
            left4 = rotateSprite(up4, -90);
            
            down1 = rotateSprite(up1, 180);
            down2 = rotateSprite(up2, 180);
            down3 = rotateSprite(up3, 180);
            down4 = rotateSprite(up4, 180);
            
            right1 = rotateSprite(up1, 90);
            right2 = rotateSprite(up2, 90);
            right3 = rotateSprite(up3, 90);
            right4 = rotateSprite(up4, 90);
        }
    }
    
    
    public BufferedImage setup(String imageName) {
        
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/individuos/" + imageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return image;
    }
    
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
    
    public void update(){
        //esse if true com varios "ou -> ||" é o que segura a sprite de não ficar sendo atualizada o tempo todo 
        if (keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true){
            if (keyH.upPressed == true) {
                direction = "up";                
            } else if (keyH.downPressed == true) {
                direction = "down";                
            } else if (keyH.leftPressed == true) {
                direction = "left";                
            } else if (keyH.rightPressed == true) {
                direction = "right";
            }
            
            //checa colisão do player com os tiles
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            //checa colisão com objeto
            int objIndex = gp.cChecker.checkObject (this, true);
            pickUpObject(objIndex);
            
            //checa colisão com npc
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);             //Isso aqui checa qual foi a entidade que colidiu com o player
            // int speednpc = 999;
            interactNPC(npcIndex);              //e isso tudo é incrivel pois podemos rodar codigo ao interagir com o jogador, caso queira mecher com velocidade ou outra coisa só mudar a variavel
            
            //colisão de inimigo
            // int enemyIndex = gp.cChecker.checkEntity(this, gp.enemy);             //Isso aqui checa qual foi a entidade que colidiu com o player
            // // int speednpc = 999;
            
            
            //Se colisor for false, o player pode se mover
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
        if (invencible==true){
            invencibleCounter++;
            if(invencibleCounter>60){
                invencible=false;
                invencibleCounter=0;
            }
            if(life == 0){
                gp.gameState = gp.gameOverState;
            }
        }
    }
    public void pickUpObject(int i){
        
        if(i != 999){
            
        }
    }
    public void interactNPC(int i){
        if(i !=999 && i!=4){
            if(UI.canReproduce==true){
                gp.npc[i] = null;
                UI.canReproduce = false;
                // UI.pararTimer();
                gp.nextStage();
            }
        }
    }


    public void draw(Graphics2D g2){
        //g2.setColor(Color.white);
        //g2.fillRect(x, y, gp.tileSize, gp.tileSize);   

        BufferedImage image = null;

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
        //Houve uma mudança para adaptar a camera do jogo e poder ver o mundo todo, então esses parametros ficaram velhos
        //Mudando o x, y para screenX and screenY para manter o personagem no centro
        //g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        g2.drawImage(image, screenX, screenY, null);
    }
    
}
