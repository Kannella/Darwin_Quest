package gamecontrol.entidade;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import gamecontrol.main.GamePanel;

public class Enemy extends Entity{
    private static final int FOLLOW_DISTANCE =200;
    Random random = new Random();
    public Enemy (GamePanel gp){
        super(gp);

        type=2;
        direction = "down";
        speed =random.nextInt(3,5);
        getEnemyImage();

    }
    
    public void update() {

        // Verifica se o inimigo deve seguir o jogador
        if (shouldFollowPlayer()) {
            followPlayer();
        } else {
            // Se não estiver seguindo o jogador, realiza ação padrão
            setAction();
        }
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.enemy);
        boolean contactPlayer= gp.cChecker.checkPlayer(this);

        if(this.type == 2 && contactPlayer==true){
            //codigo de interação com o jogador
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

    // Método para verificar se o inimigo deve seguir o jogador
    private boolean shouldFollowPlayer() {
        double distance = Math.sqrt(Math.pow(worldX - gp.player.worldX, 2) + Math.pow(worldY - gp.player.worldY, 2));
        return distance < FOLLOW_DISTANCE;
    }

    // Método para fazer o inimigo seguir o jogador
    private void followPlayer() {
        int playerX = gp.player.worldX;
        int playerY = gp.player.worldY;
    
        // Calcula a diferença horizontal e vertical entre o inimigo e o jogador
        int dx = playerX - worldX;
        int dy = playerY - worldY;
    
        // Define a direção com base nas diferenças calculadas
        if (Math.abs(dx) > Math.abs(dy)) {
            // Movimento horizontal é maior do que o movimento vertical
            if (dx > 0) {
                direction = "right";
            } else {
                direction = "left";
            }
        } else {
            // Movimento vertical é maior do que o movimento horizontal
            if (dy > 0) {
                direction = "down";
            } else {
                direction = "up";
            }
        }
    }
    public void getEnemyImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaParadaCima.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaPasso1Cima.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaParadaCima.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaPasso2Cima.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaParadaBaixo.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaPasso1Baixo.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaParadaBaixo.png"));
            down4 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaPasso2Baixo.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaAndandoEsquerdaDefault.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaAndandoEsquerdaPasso1.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaAndandoEsquerdaDefault.png"));
            left4 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaAndandoEsquerdaPasso2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaAndandoDireitaDefault.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaAndandoDireitaPasso1.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaAndandoDireitaDefault.png"));
            right4 = ImageIO.read(getClass().getResourceAsStream("/res/ameba/AmebinhaAndandoDireitaPasso2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
        public void setAction() {
            System.out.println();
            int i = random.nextInt(125)+1;
            actionLockCounter ++;
            if (actionLockCounter==50){
                if(i <= 25){
                    direction = "up";
                }
                if(i > 25 && i <= 50){
                    direction = "down";
                }
                if(i > 50 && i <= 75){
                    direction = "left";
                }
                if(i > 75 && i <= 100){
                    direction = "right";
                }
                if(i > 100 && i <= 125){
                }
                actionLockCounter = 0;
            }

            
        }
    }



