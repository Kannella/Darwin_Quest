// classe que arruma os tiles
package gamecontrol.tile;

import gamecontrol.main.GamePanel;
import gamecontrol.main.UtilityTool;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;


public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[70]; // Ajuste o tamanho conforme necessário
        mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        loadTileImages("/res/Tiles/GameTileSetAtualizado.png", 16, 16); // Ajuste o caminho e o tamanho dos tiles conforme
                                                              // necessário
        loadTileConfig("/res/Tiles/tileConfig.txt");
        loadMap("/res/mapas/MapinhaFinal.txt", 0);
    }

    public void loadTileImages(String path, int tileWidth, int tileHeight) {
        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream(path));
            int cols = spritesheet.getWidth() / tileWidth;
            int rows = spritesheet.getHeight() / tileHeight;
            int index = 0;

            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < cols; x++) {
                    if (index == 36 || index == 46 || index == 56 || index == 66) {
                        index++;
                        continue;
                    }
                    if (index < tile.length) {
                        tile[index] = new Tile();
                        tile[index].image = spritesheet.getSubimage(x * tileWidth, y * tileHeight, tileWidth,
                                tileHeight);
                        tile[index].image = UtilityTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
                        index++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTileConfig(String configPath) {
        try {
            InputStream is = getClass().getResourceAsStream(configPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                int index = Integer.parseInt(parts[0]);
                boolean collision = Boolean.parseBoolean(parts[1]);

                if (index >= 0 && index < tile.length) {
                    tile[index].collision = collision;
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap(String filePath, int map) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();

                if (line == null) {
                    break;
                }

                String[] numbers = line.split(" ");
                for (int i = 0; i < numbers.length; i++) {
                    int num = Integer.parseInt(numbers[i]);
                    mapTileNum[map][col][row] = num;
                    col++;

                    if (col == gp.maxWorldCol) {
                        col = 0;
                        row++;
                    }
                }
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow];

            if (tileNum >= 0 && tileNum < tile.length && tile[tileNum] != null) {
                int worldX = worldCol * gp.tileSize;
                int worldY = worldRow * gp.tileSize;
                int screenX = worldX - gp.getPlayer().worldX + gp.getPlayer().screenX;
                int screenY = worldY - gp.getPlayer().worldY + gp.getPlayer().screenY;

                if (worldX + gp.tileSize > gp.getPlayer().worldX - gp.getPlayer().screenX &&
                        worldX - gp.tileSize < gp.getPlayer().worldX + gp.getPlayer().screenX &&
                        worldY + gp.tileSize > gp.getPlayer().worldY - gp.getPlayer().screenY &&
                        worldY - gp.tileSize < gp.getPlayer().worldY + gp.getPlayer().screenY) {

                    g2.drawImage(tile[tileNum].image, screenX, screenY, null);
                }
            } else {
                System.out.println("Invalid tile number: " + tileNum);
            }

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
