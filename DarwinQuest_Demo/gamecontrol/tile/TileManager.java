package gamecontrol.tile;

//Classe que organiza os tiles de verdade, aqui tem métodos que desenham e amarram os tiles, incluse um buffer simples de imagem que
//delimita quantos tiles são gerados por vez(conceito de chunk), pra não ter uma sobrecarga de gerar tudo sempre
import gamecontrol.main.GamePanel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;


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

        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/undadasea.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/ParedinhaDuMarCima.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/aguinhaMaisClara.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/aguinhaMaisClaraAinda.png"));
            //tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/terrinha.png"));

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/AreiaDoMarExemplo.png"));

        }catch(IOException e){
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
                
                   g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);     
            } 

            worldCol++;

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }

        }

    }

}
