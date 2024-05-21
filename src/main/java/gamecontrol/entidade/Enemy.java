package gamecontrol.entidade;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import gamecontrol.main.GamePanel;
import gamecontrol.main.UtilityTool;

public class Enemy extends Entity {
    private static final int FOLLOW_DISTANCE = 300;
    Random random = new Random();

    public Enemy(GamePanel gp) {
        super(gp);

        type = 2;
        direction = "down";
        speed = random.nextInt(3, 5);
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
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer == true) {
            if (gp.getPlayer().invencible == false) {
                gp.getPlayer().life -= 1;
                gp.getPlayer().invencible = true;
            }
        }
        if (collisionOn == false) {

            switch (direction) {
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
        double distance = Math
                .sqrt(Math.pow(worldX - gp.getPlayer().worldX, 2) + Math.pow(worldY - gp.getPlayer().worldY, 2));
        return distance < FOLLOW_DISTANCE;
    }

    // Método para fazer o inimigo seguir o jogador
    private void followPlayer() {
        int playerX = gp.getPlayer().worldX;
        int playerY = gp.getPlayer().worldY;

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

    public void getEnemyImage() {

        down1 = setup("lagartixa/lagartixaBase");
        down2 = setup("lagartixa/lagartixa1");
        down3 = down1;
        down4 = setup("lagartixa/lagartixa2");

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

    public void setAction() {
        int i = random.nextInt(125) + 1;
        actionLockCounter++;
        if (actionLockCounter == 50) {
            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            if (i > 100 && i <= 125) {
            }
            actionLockCounter = 0;
        }
    }

    public BufferedImage setup(String imageName) {

        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/res/individuos/" + imageName + ".png"));
            image = UtilityTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}