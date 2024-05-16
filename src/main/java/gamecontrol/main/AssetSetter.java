package gamecontrol.main;

import gamecontrol.entidade.Enemy;
import gamecontrol.entidade.Partner;

//Classe feita para instanciar onde aparecem objetos no jogo, no caso comida


public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        //int mapNum = 0;

        //mapNum = 1;
    }
    public void setNPC(){
        int mapNum = 0;
        gp.npc[mapNum][0]=new Partner(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize*20;   //Controlam a posição X e Y de spawn da instancia da entidade
        gp.npc[mapNum][0].worldY = gp.tileSize*20;

        mapNum = 1;
        //coloque aqui o novo npc desse mapa
        gp.npc[mapNum][0] = new Partner(gp);
        gp.npc[mapNum][0].worldX = gp.tileSize * 20; // Controlam a posição X e Y de spawn da instancia da entidade
        gp.npc[mapNum][0].worldY = gp.tileSize * 20;
        
    }
    public void setEnemy(){
        int mapNum = 0;
        gp.enemy[mapNum][0]=new Enemy(gp);
        gp.enemy[mapNum][0].worldX = gp.tileSize*25;   //Controlam a posição X e Y de spawn da instancia da entidade
        gp.enemy[mapNum][0].worldY = gp.tileSize*20;
    }
}
