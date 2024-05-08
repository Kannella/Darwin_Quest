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

    }
    public void setNPC(){
            gp.npc[0]=new Partner(gp);
            gp.npc[0].worldX = gp.tileSize*20;   //Controlam a posição X e Y de spawn da instancia da entidade
            gp.npc[0].worldY = gp.tileSize*20;
    }
    public void setEnemy(){
            gp.enemy[0]=new Enemy(gp);
            gp.enemy[0].worldX = gp.tileSize*25;   //Controlam a posição X e Y de spawn da instancia da entidade
            gp.enemy[0].worldY = gp.tileSize*20;
    }
}
