package gamecontrol.main;

//Classe feita para gerenciar UI no jogo, como textos
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

import gamecontrol.entidade.SpriteManager;
import gamecontrol.objeto.ObjHeart;
import gamecontrol.objeto.SuperObject;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import br.maua.teste.TelaMortePersonagem;

public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
    BufferedImage fullHeart, halfHeart, emptyHeart, imagemFundo;
    public int commandNum = 0;
    public int cruzamento = 0;
    
    private static Timer timer; //esse objeto cria um "relogio" que fica de fundo
    private static TimerTask timerTask;  //esse objeto pode ser chamada pra executar uma comando ou varios comandos repitidos em função de um timer
    private static boolean running;   //variavel que verifica se o timer esta ativo
    public static long tempoDecorrido; //variavel que vai segurar o tempo que o jogador jogou o nivel
    //private static boolean gameOverDisplayed = false; //variavel que verifica se a tela de game over foi exibida

    public static boolean canReproduce = false;  //Variavel que diz se vai ser possivel reproduzir

    private BufferedImage spriteSorteado1; // variável para armazenar o primeiro sprite sorteado
    private BufferedImage spriteSorteado2; // variável para armazenar o segundo sprite sorteado

    public UI (GamePanel gp){
        this.gp = gp;

        arial_40 = new Font("verdana", Font.PLAIN, 40);
        
        //criando objeto HUD
        SuperObject heart = new ObjHeart(gp);
        fullHeart = heart.image;
        halfHeart = heart.image2;
        emptyHeart = heart.image3;

        //criando timer
        timer = new Timer();
        running = false;
        tempoDecorrido = 0;

        iniciarTimer(gp);


        //Faça com que o SpriteManager sorteie as sprites
    
        SpriteManager s = new SpriteManager();
        BufferedImage[] sprites = s.lerSpritesheet("TodosBesourinhos.png", 46, 48);
        BufferedImage[] spritesSorteadas = { sprites[0], sprites[3], sprites[6], sprites[9], sprites[12], sprites[15], sprites[18], sprites[21], sprites[24], sprites[27], sprites[30], sprites[33], sprites[36], sprites[39], sprites[42], sprites[45]};
   
        // Sorteia os sprites uma vez na inicialização
        spriteSorteado1 = sortearSprite(spritesSorteadas);
        do {
            spriteSorteado2 = sortearSprite(spritesSorteadas);
        } while (spriteSorteado2 == spriteSorteado1); // Garante que spriteSorteado2 seja diferente de spriteSorteado1
    }

    public void draw (Graphics2D g2){

        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState){
            drawPlayerLife();
        }
        if(gp.gameState == gp.pauseState){
            drawPlayerLife();
            drawPauseScreen();
        }
        if(gp.gameState == gp.gameOverState){
            // Fechando a janela atual para abrir a tela de game over
            Window w = javax.swing.SwingUtilities.getWindowAncestor(gp);  
            w.dispose();          
            drawGameOverScreen();
            

        }
        if (gp.gameState == gp.chooseState) {
            drawChooseScreen();
        }
    }

    public void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        while (i < gp.player.maxLife / 2) {
            g2.drawImage(emptyHeart, x, y, null);
            i++;
            x += gp.tileSize;
        }
        // Reset
        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        // Draw Current Life
        while (i < gp.player.life) {
            g2.drawImage(halfHeart, x, y, null);
            i++;
            if (i < gp.player.life) {
                g2.drawImage(fullHeart, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }
    
    public void drawChooseScreen() {
        // desenha fundo da tela (pega imagem e faz o tamanho que a gente quer)
        try {
            imagemFundo = ImageIO.read(getClass().getResourceAsStream("/res/mapas/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        g2.drawImage(imagemFundo, 0, 0, gp.tileSize * 16, gp.tileSize * 12, null);
        // cria texto principal
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 50F));
        String text = "Escolha um da prole";
        int x = getXforCenteredText(text);
        int y = gp.tileSize * 3;

        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 5);

        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        x = gp.screenWidth / 2;
        y += gp.screenHeight / 2;
        // pega imagens dos animais e coloca na tela
        g2.drawImage(gp.player.down1, 0, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(fullHeart, gp.tileSize, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(gp.partner.down1, gp.tileSize * 2, 0, gp.tileSize, gp.tileSize, null);

        // mostra sprites sorteadas
        

        // menu de escolha
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

        // Usa os sprites sorteados armazenados
        text = "Opção1";
        x = gp.tileSize * 2;
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        g2.drawImage(spriteSorteado1, x + gp.tileSize/2, y - gp.tileSize*4, gp.tileSize*3, gp.tileSize*3, null);


        text = "Opção2";
        x = gp.tileSize * 10;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        g2.drawImage(spriteSorteado2, x + gp.tileSize/2, y - gp.tileSize*4, gp.tileSize*3, gp.tileSize*3, null);
    }

    // Método para selecionar aleatoriamente uma imagem de um array de sprites
    public BufferedImage selectRandomSprite(BufferedImage[] sprites) {
        Random rand = new Random();
        int index = rand.nextInt(sprites.length); // Gera um índice aleatório dentro do intervalo do array
        return sprites[index]; // Retorna a sprite selecionada aleatoriamente
    }
    public BufferedImage sortearSprite(BufferedImage[] spritesSorteadas) {
        Random random = new Random();
        int indice = random.nextInt(spritesSorteadas.length);
        return spritesSorteadas[indice];
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "Pausado";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }
    public int getXforCenteredText(String text){ //método pra ser chamado toda vez que centralização necessária
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }
  

    public static void iniciarTimer(GamePanel gp) {
        if (!running) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (gp.gameState == gp.pauseState){
                        System.out.println("pausado");
                    }else if(gp.gameState == gp.gameOverState){
                        pararTimer();
                    }
                    
                    else{
                        tempoDecorrido += 1000; //A CADA MIL É 1 SEGUNDO
                        System.out.println("Tempo decorrido: " + tempoDecorrido / 1000 + " segundos");
                    }
                    if (tempoDecorrido >= 10 * 1000 && canReproduce == false){
                        System.out.println("Possivel reproduzir");
                        canReproduce = true;
                    }
                    if (tempoDecorrido >= 3 * 60 * 1000) {
                        pararTimer();
                        System.out.println("tempo esgotado");
                        gp.gameState = gp.gameOverState;
                    }
                }
            };

            timer.scheduleAtFixedRate(timerTask, 0, 1000);
            running = true;
        }else {
            System.out.println("O timer já está em execução.");
        }
    }

    public static void pararTimer() {
        if (running) {
            timerTask.cancel();
            running = false;
            System.out.println("Timer parado.");
        } else {
            System.out.println("O timer já está parado.");
        }
    }

    public void drawGameOverScreen() {
        try {
            gp.music.stop();
        } catch (Exception e) {
            System.out.println("Sistema de som não detectado");
            e.printStackTrace();
        }
        TelaMortePersonagem telaMorte = new TelaMortePersonagem();
        telaMorte.setVisible(true);
        telaMorte.setLocationRelativeTo(null);

    }

}
