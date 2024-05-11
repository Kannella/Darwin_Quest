package gamecontrol.entidade;

import gamecontrol.main.GamePanel;
import gamecontrol.main.KeyHandler;
import gamecontrol.main.UtilityTool;

//Essa classe é responsavel pelo jogador, ela puxa e funciona com as variaveis criadas na Entidade
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
//import java.awt.Color;


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

    public void getPlayerImage(){
        if (gp.currentMap == 0){    
            up1 = setup("preguica/preguicaCimaBase");
            up2 = setup("preguica/preguicaCima1");
            up3 = setup("preguica/preguicaCimaBase");
            up4 = setup("preguica/preguicaCima2");
            down1 = setup("preguica/preguicaBaixoBase");
            down2 = setup("preguica/preguicaBaixo1");
            down3 = setup("preguica/preguicaBaixoBase");
            down4 = setup("preguica/preguicaBaixo2");
            left1 = setup("preguica/preguicaEsquerdaBase");
            left2 = setup("preguica/preguicaEsquerda1");
            left3 = setup("preguica/preguicaEsquerdaBase");
            left4 = setup("preguica/preguicaEsquerda2");
            right1 = setup("preguica/preguicaDireitaBase");
            right2 = setup("preguica/preguicaDireita1");
            right3 = setup("preguica/preguicaDireitaBase");
            right4 = setup("preguica/preguicaDireita2");
        }
        else if(gp.currentMap == 1) {
            up1 = setup("besouro/besouroCimaBase");
            up2 = setup("besouro/besouroCima1");
            up3 = setup("besouro/besouroCimaBase");
            up4 = setup("besouro/besouroCima2");
            down1 = setup("besouro/besouroBaixoBase");
            down2 = setup("besouro/besouroBaixo1");
            down3 = setup("besouro/besouroBaixoBase");
            down4 = setup("besouro/besouroBaixo2");
            left1 = setup("besouro/besouroEsquerdaBase");
            left2 = setup("besouro/besouroEsquerda1");
            left3 = setup("besouro/besouroEsquerdaBase");
            left4 = setup("besouro/besouroEsquerda2");
            right1 = setup("besouro/besouroDireitaBase");
            right2 = setup("besouro/besouroDireita1");
            right3 = setup("besouro/besouroDireitaBase");
            right4 = setup("besouro/besouroDireita2");
        }
    }

    public BufferedImage setup(String imageName){

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
            int enemyIndex = gp.cChecker.checkEntity(this, gp.enemy);             //Isso aqui checa qual foi a entidade que colidiu com o player
            // // int speednpc = 999;
            interactNPC(enemyIndex);  

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
                invencible=true;
                invencibleCounter=0;
            }
        }
    }
    public void pickUpObject(int i){

        if(i != 999){

        }
    }
    public void interactNPC(int i){
        if(i !=999 && i!=4){
            System.out.println(i);
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
