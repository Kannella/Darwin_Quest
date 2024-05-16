package gamecontrol.main;

import gamecontrol.entidade.CurrentSprite;
//Essa classe é responsavel por organizar a tela. Nela temos o ciclo em que as imagens são desenhadas
import gamecontrol.entidade.Entity;
import gamecontrol.entidade.Partner;
import gamecontrol.entidade.Player;
import gamecontrol.entidade.SpriteManager;
import gamecontrol.objeto.SuperObject;
import gamecontrol.tile.TileManager;


import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;




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
    public final int maxMap = 4; //quantidade de mapas presentes
    public final int currentMap = Main.getGameStage(); //variavel que recebe qual o mapa atual
    
    
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
    public SpriteManager sManager = new SpriteManager(this);
    public CurrentState currentState;
    
    Thread gameThread;
    
    //Entidade e objeto
    public AnimalManager aManager = new AnimalManager();
    public Player player = new Player(this, keyH);
    public Partner partner = new Partner(this);
    public SuperObject obj[][] = new SuperObject[maxMap][10]; 
    public Entity npc[][]= new Entity[maxMap][10];
    public Entity enemy[][] = new Entity[maxMap][10];

    //só pra chamar player antes de currentSprite
    public CurrentSprite currentSprite = new CurrentSprite(this);

    // Game State (estado do jogo)
    // private CurrentState currentState;
    // public int gameState;
    //public int estagio; //não usado por enquanto
    //public final int playState = 1;
    //public final int pauseState = 2;
    //public final int gameOverState = 3;
    //public final int chooseState = 4;

    
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); //Dimensiona o tamanho da tela
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){       //PREPARA O NIVEL, SPAWNA NPCS E OBJETOS, INICIA MUSICA 
        currentState = new CurrentState();
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setEnemy();
        playMusic(0);
        Sound.isPlaying = true;
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
        if (currentState.getGameState() == currentState.playState){
            //player
            player.update();
            //npc mapa 1
            for(int i=0; i<npc[0].length;i++){
                if(npc[currentMap][i] != null){
                    npc[currentMap][i].update();
                }    
            }
            //inimigo mapa 1
            for (int i = 0;i<enemy[0].length;i++){
                if(enemy[currentMap][i] != null){
                    enemy[currentMap][i].update();
                }
            }
            //npc mapa 2
             for (int i = 0; i < npc[1].length; i++) {
                 if (npc[currentMap][i] != null) {
                     npc[currentMap][i].update();
                 }
            }
            //novo sorteio de sprite
            sManager.sortearSprites();
        }
        if (currentState.getGameState() == currentState.pauseState){
            //tá pausado e só isso mesmo por enquanto
        }
        if (currentState.getGameState() == currentState.chooseState){
            //Destroi os objetos do jogo ao sair do estado de escolha
            clearEntity();
            //cria novos objetos do jogo ao entrar no estado de escolha
            aSetter.setNPC();
            aSetter.setEnemy();

            stopMusic();
        }
    }
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        if (currentState.getGameState() == currentState.chooseState){
            ui.draw(g2);
        }
        else{
            // Tile
            tileM.draw(g2);

            // Objetc
            for (int i = 0; i < obj[0].length; i++) {
                if (obj[currentMap][i] != null) {
                    obj[currentMap][i].draw(g2, this);
                }
            }
            // NPC
            for (int i = 0; i < npc[0].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].draw(g2);
                }
            }
            //Inimigo
            for (int i = 0; i < enemy[0].length; i++) {
                if (enemy[currentMap][i] != null) {
                    enemy[currentMap][i].draw(g2);
                }
            }

            // Player
            player.draw(g2);

            // UI
            ui.draw(g2);
        }

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
        try {
            music.stop();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void playSE(int i){

        se.setFile(i);
        se.play();
    }

    public void nextStage(){
        Window w = javax.swing.SwingUtilities.getWindowAncestor(this);  
        w.dispose();
        Main.setGameStage(+1);
        try{stopMusic();} catch(Exception e){System.out.println(e);}
        Main.abrirJanelaDoJogo();
    }

    public void clearEntity() {
        // Limpe os objetos do jogo ao sair do estado de escolha
        // Por exemplo, redefina NPCs, limpe objetos, etc.
        // Exemplo:
        npc = new Entity[maxMap][10];
        enemy = new Entity[maxMap][10];
        // Preencher npc com novas instâncias de NPCs, se necessário
    }

}