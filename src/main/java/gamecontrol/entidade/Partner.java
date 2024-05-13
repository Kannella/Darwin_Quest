package gamecontrol.entidade;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import gamecontrol.main.GamePanel;
import gamecontrol.main.UtilityTool;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;

public class Partner extends Entity {
    Random random= new Random();
    public Partner(GamePanel gp){
        super(gp);  
        direction = "down";
        speed = 1;
        getNPCImage();
 
    }

    public void getNPCImage() {
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

    public void setAction() {
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
            }if(i > 100 && i <= 125){
            }

            actionLockCounter = 0;
        }
        
    }
}

