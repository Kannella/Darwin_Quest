package gamecontrol.entidade;

import gamecontrol.main.GamePanel;
import gamecontrol.main.KeyHandler;
import gamecontrol.main.UI;

//Essa classe é responsavel pelo jogador, ela puxa e funciona com as variaveis criadas na Entidade
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;



public class Player extends Entity{

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int playerSpriteIndex = 0; //tentativa de armazenar o index do jogador
    //public BufferedImage[] playerSprites; //tentativa de armazenar as sprites do jogador

    public Player(GamePanel gp, KeyHandler keyH){
        
        super(gp);
        this.keyH = keyH;
        BufferedImage[] sprites = gp.sManager.lerSpritesheet("TodosBesourinhos.png", 46, 48);
        
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

        BufferedImage[] sprites;
        int spriteIndex;

        while (gp.dControler.cruzamentos == 0) {
            spriteIndex = 0; // Defina o índice da sprite conforme necessário
            playerSpriteIndex = spriteIndex; // Retorna um valor para playerSpriteIndex para ser usado em outras partes do código
            // Obtenha a sprite atual do jogador com base na escolha
            sprites = gp.sManager.lerSpritesheet("TodosBesourinhos.png", 46, 48);
            // Defina o índice da sprite conforme necessário
            setPlayerSprites(sprites, spriteIndex);
            // Sai do loop
            break;
        }
        if (gp.dControler.cruzamentos != 0) {
            spriteIndex = playerSpriteIndex;
            // Se cruzamentos não for zero, verifique o contador
            sprites = gp.sManager.lerSpritesheet("TodosBesourinhos.png", 46, 48); 
            if (gp.sManager.contador == 1) {
                spriteIndex = SpriteManager.spriteSorteado1Index;
            } else if (gp.sManager.contador == 2) {
                spriteIndex = SpriteManager.spriteSorteado2Index;
            }
            // Retorna um valor para playerSpriteIndex para ser usado em outras partes do código
            playerSpriteIndex = spriteIndex; 
            // Atribui sprite ao jogador
            setPlayerSprites(sprites, spriteIndex);
            }
        }

    private void setPlayerSprites(BufferedImage[] sprites, int spriteIndex) {
        down1 = gp.sManager.setup(sprites[spriteIndex]);
        down2 = gp.sManager.setup(sprites[spriteIndex + 1]);
        down3 = gp.sManager.setup(sprites[spriteIndex]);
        down4 = gp.sManager.setup(sprites[spriteIndex + 2]);

        // Faça as rotações apropriadas para as outras direções
        up1 = gp.sManager.rotateSprite(down1, 180);
        up2 = gp.sManager.rotateSprite(down2, 180);
        up3 = gp.sManager.rotateSprite(down3, 180);
        up4 = gp.sManager.rotateSprite(down4, 180);

        left1 = gp.sManager.rotateSprite(up1, -90);
        left2 = gp.sManager.rotateSprite(up2, -90);
        left3 = gp.sManager.rotateSprite(up3, -90);
        left4 = gp.sManager.rotateSprite(up4, -90);

        right1 = gp.sManager.rotateSprite(up1, 90);
        right2 = gp.sManager.rotateSprite(up2, 90);
        right3 = gp.sManager.rotateSprite(up3, 90);
        right4 = gp.sManager.rotateSprite(up4, 90);
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
            // // int speednpc = 999;                                                       //LEO: NÃO COMENTA ISSO, A COLISÃO COM O INIMIGO SÓ NÃO FUNCIONA SE ISSO NÃO EXISTIR E EU NÃO SEI PQ

            
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
                gp.currentState.setGameOverState();
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
                //gp.npc[i] = null;
                UI.canReproduce = false;
                UI.pararTimer();
                gp.currentState.setChooseState();
                gp.dControler.cruzamentos++;
                System.err.println("Cruzamentos: " + gp.dControler.cruzamentos);
                gp.dControler.darwinInteference();
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
    // Métodos para definir as sprites de movimento do jogador
    public void setDown1(BufferedImage sprite) {
        this.down1 = sprite;
    }

    public void setDown2(BufferedImage sprite) {
        this.down2 = sprite;
    }

    public void setDown3(BufferedImage sprite) {
        this.down3 = sprite;
    }

    public void setDown4(BufferedImage sprite) {
        this.down4 = sprite;
    }
}
