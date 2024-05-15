package gamecontrol.main;

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
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true){
                    entidade.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entidade.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entidade.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entidade.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entidade.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entidade.speed) / gp.tileSize;
                tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
                if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
                    entidade.collisionOn = true;
                }
                break;            
        }
    }

    public int checkObject(Entity entidade, boolean player){

        int index = 999;

        for(int i = 0; i < gp.obj[1].length; i++){

            if(gp.obj[gp.currentMap][i] != null){

                //Pegar da entidade solid area position
                entidade.solidArea.x = entidade.worldX + entidade.solidArea.x;
                entidade.solidArea.y = entidade.worldY + entidade.solidArea.y;
                //Pegar dos objetos a solid area position
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;

                switch(entidade.direction){
                    case "up":
                        entidade.solidArea.y -= entidade.speed;
                        if(entidade.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)){
                            if(gp.obj[gp.currentMap][i].collision == true){
                            entidade.collisionOn = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }   
                        break;
                    case "down":
                        entidade.solidArea.y += entidade.speed;
                        if (entidade.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if (gp.obj[gp.currentMap][i].collision == true) {
                                entidade.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }   
                        break;
                    case "left":
                        entidade.solidArea.x -= entidade.speed;
                        if (entidade.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if (gp.obj[gp.currentMap][i].collision == true) {
                                entidade.collisionOn = true;
                            }
                            if (player == true) {
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entidade.solidArea.x += entidade.speed;
                        if (entidade.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {
                            if (gp.obj[gp.currentMap][i].collision == true) {
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
                gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX;
                gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY;

            }
        }
         return index;
    }
    //Checar colisão de Entidade
    public int checkEntity(Entity entidade, Entity[][] target){
        int index = 999;

        for(int i = 0; i < target[1].length; i++){
            if(target[gp.currentMap][i] != null){

                //Pegar da entidade solid area position
                entidade.solidArea.x = entidade.worldX + entidade.solidArea.x;
                entidade.solidArea.y = entidade.worldY + entidade.solidArea.y;
                //Pegar dos objetos a solid area position
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;

                switch(entidade.direction){
                    case "up":
                        entidade.solidArea.y -= entidade.speed;
                        break;
                    case "down":
                        entidade.solidArea.y += entidade.speed;
                        break;
                    case "left":
                        entidade.solidArea.x -= entidade.speed;
                        break;
                    case "right":
                        entidade.solidArea.x += entidade.speed;
                        break;
                    }
                    if(entidade.solidArea.intersects(target[gp.currentMap][i].solidArea)){
                        if(target[gp.currentMap][i] != entidade){
                            entidade.collisionOn = true;
                            index = i;
                        }
                    }                    
                entidade.solidArea.x = entidade.solidAreaDefaultX;
                entidade.solidArea.y = entidade.solidAreaDefaultY;
                target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;
                target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;

            }
        }
         return index;

    }
    public boolean checkPlayer(Entity entidade){ 

        boolean contactPlayer = false;

        //Pegar da entidade solid area position
        entidade.solidArea.x = entidade.worldX + entidade.solidArea.x;
        entidade.solidArea.y = entidade.worldY + entidade.solidArea.y;
        //Pegar dos objetos a solid area position
        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;

        switch(entidade.direction){
            case "up":
                entidade.solidArea.y -= entidade.speed;
                break;
            case "down":
                entidade.solidArea.y += entidade.speed;
                break;
            case "left":
                entidade.solidArea.x -= entidade.speed;
                break;
            case "right":
                entidade.solidArea.x += entidade.speed;
                break;
            }
            if(entidade.solidArea.intersects(gp.player.solidArea)){
                if(gp.player != entidade){
                    entidade.collisionOn = true;
                    contactPlayer=true;

                }
            }                    
        entidade.solidArea.x = entidade.solidAreaDefaultX;
        entidade.solidArea.y = entidade.solidAreaDefaultY;
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        
        return contactPlayer;
    }
}
