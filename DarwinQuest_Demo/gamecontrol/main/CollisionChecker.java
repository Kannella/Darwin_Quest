package gamecontrol.main;

import java.lang.annotation.Target;

//Classe feita para verificar a colisão, ela checa alguns retangulos criados em volta de objetos e para saber se eles estão no mesmo lugar
import gamecontrol.entidade.Entity;

public class CollisionChecker {
    
    GamePanel gp;

    public CollisionChecker (GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entidade){
        int entityLeftWorldX = entidade.worldX + entidade.solidArea.x;
        int entityRightWorldX = entidade.worldX + entidade.solidArea.x + entidade.solidArea.width;
        int entityTopWorldY = entidade.worldY + entidade.solidArea.y;
        int entityBottomWorldY = entidade.worldY + entidade.solidArea.y + entidade.solidArea.height;

        int entityLeftCol= entityLeftWorldX / gp.tileSize;
        int entityRightCol = entityRightWorldX / gp.tileSize;
        int entityTopRow = entityTopWorldY / gp.tileSize;
        int entityBottomRow = entityBottomWorldY / gp.tileSize;

        int tileNum1, tileNum2;

        switch(entidade.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entidade.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entidade.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entidade.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entidade.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entidade.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entidade.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entidade.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entidade.collisionOn = true;
                }
                break;            
        }
    }

    public int checkObject(Entity entidade, boolean player){

        int index = 999;

        for(int i = 0; i < gp.obj.length; i++){

            if(gp.obj[i] != null){

                //Pegar da entidade solid area position
                entidade.solidArea.x = entidade.worldX + entidade.solidArea.x;
                entidade.solidArea.y = entidade.worldY + entidade.solidArea.y;
                //Pegar dos objetos a solid area position
                gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
                gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;

                switch(entidade.direction){
                    case "up":
                        entidade.solidArea.y -= entidade.speed;
                        if(entidade.solidArea.intersects(gp.obj[i].solidArea)){
                            if(gp.obj[i].collision == true){
                            entidade.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }   
                        break;
                    case "down":
                        entidade.solidArea.y += entidade.speed;
                        if (entidade.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
                                entidade.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }   
                        break;
                    case "left":
                        entidade.solidArea.y -= entidade.speed;
                        if (entidade.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
                                entidade.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entidade.solidArea.y += entidade.speed;
                        if (entidade.solidArea.intersects(gp.obj[i].solidArea)) {
                            if (gp.obj[i].collision == true) {
                                entidade.collisionOn = true;
                            }                    
                            if (player == true) {
                                index = i;
                            }
                        }    
                        break;                   
                }
                entidade.solidArea.x = entidade.solidAreaDefaultX;
                entidade.solidArea.y = entidade.solidAreaDefaultY;
                gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
                gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;

            }
        }
         return index;
    }
    //Checar colisão de NPC
    public int checkEntity(Entity entidade, Entity[] target){
        int index = 999;

        for(int i = 0; i < target.length; i++){

            if(target[i] != null){

                //Pegar da entidade solid area position
                entidade.solidArea.x = entidade.worldX + entidade.solidArea.x;
                entidade.solidArea.y = entidade.worldY + entidade.solidArea.y;
                //Pegar dos objetos a solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch(entidade.direction){
                    case "up":
                        entidade.solidArea.y -= entidade.speed;
                        if(entidade.solidArea.intersects(target[i].solidArea)){
                            entidade.collisionOn = true;
                            index = i;
                        }   
                        break;
                    case "down":
                        entidade.solidArea.y += entidade.speed;
                        if (entidade.solidArea.intersects(target[i].solidArea)) {
                                entidade.collisionOn = true;
                                index = i;
                        }   
                        break;
                    case "left":
                        entidade.solidArea.y -= entidade.speed;
                        if (entidade.solidArea.intersects(target[i].solidArea)) {
                            entidade.collisionOn = true;
                            index = i;
                        }
                        break;
                    case "right":
                        entidade.solidArea.y += entidade.speed;
                        if (entidade.solidArea.intersects(target[i].solidArea)) {
                            entidade.collisionOn = true;            
                            index = i;
                        }    
                        break;                   
                }
                entidade.solidArea.x = entidade.solidAreaDefaultX;
                entidade.solidArea.y = entidade.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;

            }
        }
         return index;

    }
}
