//Classe que pode receber várias utilidades 
package gamecontrol.main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {

    

    private static boolean tocaMusica=true;
    
    public static boolean gettocaMusica() {
        return tocaMusica;
    }
    public static void settocaMusica(boolean estadoMusica) {
        tocaMusica=estadoMusica;
    }
    
    public BufferedImage scaleImage(BufferedImage original, int width, int height){

        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }
}
