package gamecontrol.entidade;

import gamecontrol.main.GamePanel;
import gamecontrol.main.KeyHandler;
import gamecontrol.main.UI;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Player extends Entity {

    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int playerSpriteIndex = 0;
    private boolean isDashing = false;
    private float dashSpeed = 20f;
    private float dashDuration = 0.2f; // Em segundos
    private float dashTime = 0;
    private long lastDashTime = 0; // Para controlar o tempo de dash
    public float cooldown = 2f; // Tempo de espera para o próximo dash
    private final double dashCooldown = 2.0; // cooldown do dash em segundos
    private long lastDashEndTime = 0; // armazena o tempo em que o dash terminou


    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        BufferedImage[] sprites = gp.sManager.lerSpritesheet("TodosBesourinhos.png", 46, 48);

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

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

    public void setDefaultValues() {
        worldX = gp.tileSize * 25;
        worldY = gp.tileSize * 15;
        speed = 5;
        direction = "down";

        maxLife = 6;
        life = maxLife;
    }

    public void getPlayerImage() {
        BufferedImage[] sprites;
        int spriteIndex;

        while (gp.dControler.cruzamentos == 0) {
            spriteIndex = 0;
            playerSpriteIndex = spriteIndex;
            sprites = gp.sManager.lerSpritesheet("TodosBesourinhos.png", 46, 48);
            setPlayerSprites(sprites, spriteIndex);
            break;
        }
        if (gp.dControler.cruzamentos != 0) {
            spriteIndex = playerSpriteIndex;
            sprites = gp.sManager.lerSpritesheet("TodosBesourinhos.png", 46, 48);
            if (gp.sManager.contador == 1) {
                spriteIndex = SpriteManager.spriteSorteado1Index;
            } else if (gp.sManager.contador == 2) {
                spriteIndex = SpriteManager.spriteSorteado2Index;
            }
            playerSpriteIndex = spriteIndex;
            setPlayerSprites(sprites, spriteIndex);
        }
    }

    private void setPlayerSprites(BufferedImage[] sprites, int spriteIndex) {
        down1 = gp.sManager.setup(sprites[spriteIndex]);
        down2 = gp.sManager.setup(sprites[spriteIndex + 1]);
        down3 = gp.sManager.setup(sprites[spriteIndex]);
        down4 = gp.sManager.setup(sprites[spriteIndex + 2]);

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

    public void dash() {
        long currentTime = System.nanoTime();
        // Verifica se o cooldown terminou
        if (!isDashing && (currentTime - lastDashEndTime) / 1_000_000_000.0 >= dashCooldown) {
            isDashing = true;
            dashTime = dashDuration;
            lastDashTime = currentTime;
        }
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed) {
            if (!isDashing) {
                if (keyH.upPressed) {
                    direction = "up";
                } else if (keyH.downPressed) {
                    direction = "down";
                } else if (keyH.leftPressed) {
                    direction = "left";
                } else if (keyH.rightPressed) {
                    direction = "right";
                }

                collisionOn = false;
                gp.cChecker.checkTile(this);

                int objIndex = gp.cChecker.checkObject(this, true);
                pickUpObject(objIndex);

                int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
                interactNPC(npcIndex);

                int enemyIndex = gp.cChecker.checkEntity(this, gp.enemy);

                if (!collisionOn) {
                    switch (direction) {
                        case "up":
                            worldY -= speed;
                            break;
                        case "down":
                            worldY += speed;
                            break;
                        case "left":
                            worldX -= speed;
                            break;
                        case "right":
                            worldX += speed;
                            break;
                    }
                }

                spriteCounter++;
                if (spriteCounter > 10) {
                    spriteNumber = (spriteNumber % 4) + 1;
                    spriteCounter = 0;
                }
            } else {
                dashUpdate();
            }
        }

        if (invencible) {
            invencibleCounter++;
            if (invencibleCounter > 60) {
                invencible = false;
                invencibleCounter = 0;
            }
            if (life == 0) {
                //gp.se.playSE(3);
                gp.currentState.setGameOverState();
            }
        }
    }

    private void dashUpdate() {
        if (isDashing) {
            long currentTime = System.nanoTime();
            dashTime -= (currentTime - lastDashTime) / 1_000_000_000.0; // Convertendo para segundos
            lastDashTime = currentTime;
    
            if (dashTime <= 0) {
                stopDash();
            } else {
                collisionOn = false;
                float dashSpeedX = 0;
                float dashSpeedY = 0;
    
                switch (direction) {
                    case "up":
                        dashSpeedY = -dashSpeed;
                        break;
                    case "down":
                        dashSpeedY = dashSpeed;
                        break;
                    case "left":
                        dashSpeedX = -dashSpeed;
                        break;
                    case "right":
                        dashSpeedX = dashSpeed;
                        break;
                }
    
                for (int i = 0; i < dashSpeed; i++) {
                    worldX += dashSpeedX / dashSpeed;
                    worldY += dashSpeedY / dashSpeed;
    
                    gp.cChecker.checkTile(this);
    
                    if (collisionOn) {
                        stopDash();
                        break;
                    }
                }
            }
        }
    }
    
    private void stopDash() {
        isDashing = false;
        lastDashEndTime = System.nanoTime(); // Registra o tempo em que o dash terminou
    }

    public void pickUpObject(int i) {
        if (i != 999) {
          
        }
    }

    public void interactNPC(int i) {
        if (i != 999 ) {
            if (UI.canReproduce) {
                gp.stopMusic();
                UI.canReproduce = false;
                UI.pararTimer();
                gp.currentState.setChooseState();
                gp.dControler.cruzamentos++;
                gp.dControler.darwinInteference();
                gp.stopMusic();
                gp.playMusic(1);
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = switch (spriteNumber) {
                    case 1 -> up1;
                    case 2 -> up2;
                    case 3 -> up3;
                    case 4 -> up4;
                    default -> up1;
                };
                break;
            case "down":
                image = switch (spriteNumber) {
                    case 1 -> down1;
                    case 2 -> down2;
                    case 3 -> down3;
                    case 4 -> down4;
                    default -> down1;
                };
                break;
            case "left":
                image = switch (spriteNumber) {
                    case 1 -> left1;
                    case 2 -> left2;
                    case 3 -> left3;
                    case 4 -> left4;
                    default -> left1;
                };
                break;
            case "right":
                image = switch (spriteNumber) {
                    case 1 -> right1;
                    case 2 -> right2;
                    case 3 -> right3;
                    case 4 -> right4;
                    default -> right1;
                };
                break;
        }

        g2.drawImage(image, screenX, screenY, null);
    }

    public void setSpeed(int i) {
        // Implementação para definir a velocidade, se necessário
    }
}
