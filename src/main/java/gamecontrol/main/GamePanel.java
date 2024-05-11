package gamecontrol.main;

//Essa classe é responsavel por organizar a tela. Nela temos o ciclo em que as imagens são desenhadas
import gamecontrol.entidade.Entity;
import gamecontrol.entidade.Player;
import gamecontrol.objeto.SuperObject;
import gamecontrol.tile.TileManager;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class GamePanel extends JPanel implements Runnable{
    //Configurações de tela
    final int originalTileSize = 16; //Tamanho dos tiles do jogo no caso 16x16
    final int scale = 3; //Isso aqui faz um escalonamento dos tiles para que pareçam maiores no caso 3 x 16 = 48
   
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16; //aqui é a definição de quantos tiles aparecem por vez na horizontal
    public final int maxScreenRow = 12; //aqui é a definição na vertical
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    //Configuração de mundo
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 31;
    //public final int worldWidth = tileSize * maxWorldCol; //Por enquanto não usado em nada :D
    //public final int worldHeight = tileSize * maxWorldRow; // Por enquanto não usado em nada :D
    
    //setando FPS do jogo (a variável pelo menos)
    int FPS = 60;

    //Sistema
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    public Sound music = new Sound();
    public Sound se = new Sound();
    public UI ui = new UI(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Thread gameThread;
    
    //Entidade e objeto
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[10];
    public Entity npc[]= new Entity[10];
    public Entity enemy[] = new Entity[10];

    // Game State (estado do jogo)
    public int gameState;
    public int estagio;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int gameOverState =3;

    
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //Dimensiona o tamanho da tela
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){       //PREPARA O NIVEL, SPAWNA NPCS E OBJETOS, INICIA MUSICA 

        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setEnemy();
        playMusic(0);
        Sound.isPlaying = true;
        gameState = playState;
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run(){
        //usaremos nanotime inves de milisegundos, para aumentar a precisão em que a tela é atualizada com a posição do player e tudo mais
        double drawInterval = 1000000000/FPS; //variável para desenhar a tela 0,16666 vezes por segundo
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null){
            //System.out.println("Teste de loop, o jogo está rodando");

            //Parte reservada para update de informações como a posição do personagem
            update ();

            //Parte reservada para desenhar a tela com a tela atualizada
            repaint ();
            
            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;
                // a variável criada acima vai ser responsável para identificar e falar pera ae
                // e parar de desenhar telas intensamente, junto a ela foi feita uma conversão de nano para mili por conta do método sleep 
                if (remainingTime < 0){
                    remainingTime = 0;
                }
                
                Thread.sleep((long) remainingTime);

                nextDrawTime = nextDrawTime + drawInterval;
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void update(){
        if (gameState == playState){
            //player
            player.update();
            //npc
            for(int i=0; i<npc.length;i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }
            for (int i = 0;i<enemy.length;i++){
                if(enemy[i] != null){
                    enemy[i].update();
                }
            }
        }
        if (gameState == pauseState){
            //tá pausado e só isso mesmo por enquanto
        }
    }
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        //Tile
        tileM.draw(g2);

        //Objetc
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        //NPC
        for(int i = 0; i< npc.length;i++){
            if(npc[i] != null){
                npc[i].draw(g2);
            }
        }for(int i = 0; i< enemy.length;i++){
            if(enemy[i] != null){
                enemy[i].draw(g2);
            }
        }

        //Player
        player.draw(g2);

        //UI
        ui.draw(g2);

        g2.dispose();
    }

    public void playMusic(int i){
        try{
            music.setFile(i);
            music.play();
            music.loop();
        }catch(Exception e) {
            System.out.println("Sistema de som não detectado");
            System.out.println(e);
        }
    }

    public void stopMusic(){

        music.stop();
    }

    public void playSE(int i){

        se.setFile(i);
        se.play();
    }
}