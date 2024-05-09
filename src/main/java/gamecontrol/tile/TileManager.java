package gamecontrol.tile;

//Classe que organiza os tiles de verdade, aqui tem métodos que desenham e amarram os tiles, incluse um buffer simples de imagem que
//delimita quantos tiles são gerados por vez(conceito de chunk), pra não ter uma sobrecarga de gerar tudo sempre
import gamecontrol.main.GamePanel;
import gamecontrol.main.UtilityTool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


public class TileManager {
    
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){

        this.gp = gp;

        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/MapaPreHistorico1.txt");
    }

    public void getTileImage(){

        setup(0, "undadasea", false);
        setup(1, "ParedinhaDuMarCima", true);
        setup(2, "aguinhaMaisClara", false);
        setup(3, "aguinhaMaisClaraAinda", false);
        setup(4, "terrinha", false);
        setup(5, "AreiaDoMarExemplo", false);

            //modo manual - classe Utility faz sem precisar fazer um por um
            //Vamos tentar escalar as imagens antes delas serem desenhadas pra tentar tirar alguns bugs visuais
            //O buffered image cria uma área de trabalho, um local que vai receber o desenho no caso
            //Na linha do Graphics2D ele vai salvar o que quer que seja criado no scaledImage
            //Depois ele desenha a imagem com o tamanho referenciado
            //BufferedImage scaledImage = new BufferedImage(gp.tileSize, gp.tileSize, tile[0].image.getType());
            //Graphics2D g2 = scaledImage.createGraphics();
            //g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
            //tile[0].image = scaledImage;
    }

    public void setup(int index, String imageName, boolean collision){

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath){

        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

                String line = br.readLine();

                while(col < gp.maxWorldCol){

                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                
                    mapTileNum[col][row] = num;
                    col++;                
                }
                if(col == gp.maxWorldCol){
                    col =0;
                    row++;
                }
            }
            br.close();

        }catch(Exception e){
        }

    }

    public void draw(Graphics2D g2){
        // jeito pouco eficiente de desenhar os tiles, mas possivel
        // g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        // g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
        // g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);

        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

            int tileNum = mapTileNum[worldCol][worldRow];

            //essas variaveis worldX e worldY, fazem a conta de onde o personagem está, ou seja se ele está no x1 y0
            //ele vai fazer a conta e mostrar no mapa baseado na conta 1 * 48 e 0 * 48 (que é o tamanho do tileSize que é 3*16)
            //já o screen X and Y, fazem a conta de distancia do player aos outros tiles, posicionando o mapa e não examente o player
            //a subtração faz essa conta de tiles away from o jogador e a soma a correção de quando o player vai chegar numa borda
            //e queremos que ele se mantenha no meio da tela e não que ele bata lá com a camera junto
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //aqui vamos tentar melhorar o programa e previnir riscos de performance, pois o jogo está desenhando todos os tiles sempre sem esse if
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                
                   g2.drawImage(tile[tileNum].image, screenX, screenY, null);     
            } 

            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }

        }

    }

}
