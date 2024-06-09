package gamecontrol.main;

import gamecontrol.entidade.Entity;
import gamecontrol.entidade.Partner;
import gamecontrol.entidade.Player;
import gamecontrol.entidade.SpriteManager;
import gamecontrol.objeto.SuperObject;
import gamecontrol.tile.TileManager;

import javax.swing.JPanel;

import br.maua.teste.PerpetuouBesouro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Window;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    // Configurações de tela
    final int originalTileSize = 16; // Tamanho dos tiles do jogo no caso 16x16
    final int scale = 3; // Isso aqui faz um escalonamento dos tiles para que pareçam maiores no caso 3 x 16 = 48

    public final int tileSize = originalTileSize * scale;
    public final int maxScreenCol = 16; // aqui é a definição de quantos tiles aparecem por vez na horizontal
    public final int maxScreenRow = 12; // aqui é a definição na vertical
    public final int screenWidth = tileSize * maxScreenCol;
    public final int screenHeight = tileSize * maxScreenRow;

    // Configuração de mundo
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxMap = 4; // quantidade de mapas presentes
    public final int currentMap = Main.getGameStage(); // variável que recebe qual o mapa atual

    // Setando FPS do jogo
    int FPS = 60;

    // Sistema
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    public Sound music = new Sound();
    public Sound se = new Sound();
    public UI ui = new UI(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public CurrentState currentState;

    Thread gameThread;

    // Entidade e objeto
    public Player player;
    public Partner partner = new Partner(this);
    public SuperObject[][] obj = new SuperObject[maxMap][10];
    public Entity[][] npc = new Entity[maxMap][10];
    public Entity[][] enemy = new Entity[maxMap][10];
    ArrayList<Entity> entityList = new ArrayList<>();
    public DarwinControler dControler = new DarwinControler(this);
    public SpriteManager sManager = new SpriteManager(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Dimensiona o tamanho da tela
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
        createPlayer();
        getPlayer();
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() { // PREPARA O NIVEL, SPAWNA NPCS E OBJETOS, INICIA MUSICA
        currentState = new CurrentState();
        stopMusic();
        playMusic(0);
        Sound.isPlaying = true;
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setEnemy();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void run() {
        double drawInterval = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public CurrentState getCurrentState() {
        return currentState;
    }

    public void update() {
        if(UtilityTool.getacabouler()){
            currentState.setPlayState();
            UI.pararTimer();
            UI.iniciarTimer(this);
            UtilityTool.setAcabouLer(false);
        }
        if (currentState.getGameState() == currentState.playState) {
            player.update();
            for (int i = 0; i < npc[currentMap].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].update();
                }
            }
            for (int i = 0; i < enemy[currentMap].length; i++) {
                if (enemy[currentMap][i] != null) {
                    enemy[currentMap][i].update();
                }
            }
        } else if (currentState.getGameState() == currentState.pauseState) {
            // Pausado
        } else if (currentState.getGameState() == currentState.chooseState) {
            clearEntities();
            aSetter.setNPC();
            aSetter.setEnemy();
        }
        else if(currentState.getGameState()==currentState.finalState&&!UtilityTool.getchegouofim()){
            Window w = javax.swing.SwingUtilities.getWindowAncestor(this);
            w.dispose();
            UtilityTool.setchegouofim(true);
            PerpetuouBesouro pb = new PerpetuouBesouro();
            pb.setVisible(true);
            pb.setLocationRelativeTo(null);
        }
        if(!UtilityTool.gettocaMusica()){
            Sound.isPlaying=false; 
        }
    }
    public Window getWindow(){
        Window w = javax.swing.SwingUtilities.getWindowAncestor(this);
        return w;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Configurações de renderização para alta qualidade
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (currentState.getGameState() == currentState.chooseState) {
            ui.draw(g2);
        } else {
            tileM.draw(g2);

            // Player
            if (player != null) {
                player.draw(g2);
            } else {
                createPlayer();
            }

            // Desenha as entidades (no caso só o player por enquanto)
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2);
            }

            // Esvaziar a lista de entidades
            entityList.clear();

            // Objetos
            for (int i = 0; i < obj[0].length; i++) {
                if (obj[currentMap][i] != null) {
                    obj[currentMap][i].draw(g2, this);
                }
            }
            // NPCs
            for (int i = 0; i < npc[0].length; i++) {
                if (npc[currentMap][i] != null) {
                    npc[currentMap][i].draw(g2);
                }
            }
            // Inimigos
            for (int i = 0; i < enemy[0].length; i++) {
                if (enemy[currentMap][i] != null) {
                    enemy[currentMap][i].draw(g2);
                }
            }
            // UI
            ui.draw(g2);
        }

        g2.dispose();
    }

    public void playMusic(int i) {
        if (UtilityTool.gettocaMusica()) {
            try {
                music.setFile(i);
                music.play();
                music.loop();
            } catch (Exception e) {
                System.out.println("Sistema de som não detectado");
                System.out.println(e);
            }
        } else {
            System.out.println("Musica não permitida");
        }
    }

    public void stopMusic() {
        try {
            music.stop();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }

    public void nextStage() {
        Window w = javax.swing.SwingUtilities.getWindowAncestor(this);
        w.dispose();
        Main.setGameStage(+1);
        stopMusic();
        Main.abrirJanelaDoJogo();
    }

    public void clearEntities() {
        npc = new Entity[maxMap][10];
        enemy = new Entity[maxMap][10];
    }

    public void createPlayer() {
        player = new Player(this, keyH);
    }

    public void updatePlayer(Player newPlayer) {
        this.player = newPlayer;
    }

    public Player getPlayer() {
        return player;
    }

    public void disposePlayer() {
        player = null;
    }
}
