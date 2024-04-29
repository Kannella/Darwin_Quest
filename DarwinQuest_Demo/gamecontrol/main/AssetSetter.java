package gamecontrol.main;

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
        int num_npcs=5; //Essa variavel controla o numero de npcs, mais que 99 crasha o jogo, caso queira mudar isso por algum motivo mudar la no GamePanel
        for(int i=0;i!=num_npcs;i++){
           gp.npc[i]=new Partner(gp);
           gp.npc[i].worldX = gp.tileSize*20;   //Controlam a posição X e Y de spawn da instancia da entidade
           gp.npc[i].worldY = gp.tileSize*20;
        };
    }
}
