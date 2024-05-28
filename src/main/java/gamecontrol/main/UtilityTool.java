//Classe que pode receber várias utilidades 
package gamecontrol.main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class UtilityTool {

    private static boolean chegouofim=false;
    
    public static boolean getchegouofim() {
        return chegouofim;
    }

    public static void setchegouofim(boolean fim) {
        chegouofim=fim;
    }

    private static boolean tocaMusica=true;
    
    public static boolean gettocaMusica() {
        return tocaMusica;
    }
    public static void settocaMusica(boolean estadoMusica) {
        tocaMusica=estadoMusica;
    }
    private static boolean acabouLer=false;
    public static boolean getacabouler(){
        return acabouLer;
    }
    public static void setAcabouLer(boolean acabouLer) {
        UtilityTool.acabouLer = acabouLer;
    }

    public static BufferedImage scaleImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        // Redimensiona a imagem usando o método getScaledInstance
        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage scaledBufferedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);

        // Desenha a imagem redimensionada no BufferedImage
        Graphics2D g2d = scaledBufferedImage.createGraphics();
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return scaledBufferedImage;
    }
}
