package gamecontrol.main;

import gamecontrol.entidade.Enemy;
import gamecontrol.entidade.Partner;

//Classe feita para instanciar onde aparecem objetos no jogo, no caso comida


public class AssetSetter {
    
    GamePanel gp;
    int px;
    int py;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        //int mapNum = 0;

        //mapNum = 1;
    }
    public void setNPC(){
        int num_npcs=5; //Controla o numero de npcs, não muda isso
        for(int i=1;i!=num_npcs;i++){
            int mapNum = 0;
            if(i==1){px=1044;py=1872;}
            if(i==2){px=1866;py=1460;}
            if(i==3){px=531;py=867;}
            if(i==4){px=1324;py=2052;}
            if(i==5){px=1240;py=312;}

            gp.npc[mapNum][i]=new Partner(gp);
            gp.npc[mapNum][i].worldX = px;   //Controlam a posição X e Y de spawn da instancia da entidade
            gp.npc[mapNum][i].worldY = py;

            // mapNum = 1;
            // //coloque aqui o novo npc desse mapa
            // gp.npc[mapNum][i] = new Partner(gp);
            // gp.npc[mapNum][i].worldX = gp.tileSize * (20+i); // Controlam a posição X e Y de spawn da instancia da entidade
            // gp.npc[mapNum][i].worldY = gp.tileSize * (20+i);
        }
        
    }
    public void setEnemy(){
        int num_enemy=5;
        for(int i=1;i!=num_enemy;i++){
            if(i==1){px=1836;py=1792;}
            if(i==2){px=1928;py=592;}
            if(i==3){px=484;py=1588;}
            if(i==4){px=1966;py=1700;}
            if(i==5){px=1240;py=312;}
            int mapNum = 0;
            gp.enemy[mapNum][i]=new Enemy(gp);
            gp.enemy[mapNum][i].worldX = px;   //Controlam a posição X e Y de spawn da instancia da entidade
            gp.enemy[mapNum][i].worldY = py;
        }
    }
}
