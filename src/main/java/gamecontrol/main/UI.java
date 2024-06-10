package gamecontrol.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;

import br.maua.teste.TelaMortePersonagem;
import gamecontrol.objeto.ObjHeart;
import gamecontrol.objeto.SuperObject;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
    BufferedImage fullHeart, halfHeart, emptyHeart, imagemFundo;
    public int commandNum = 0;

    private static Timer timer;
    private static TimerTask timerTask;
    private static boolean running;
    public static long tempoDecorrido;
    public static boolean canReproduce = false;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("verdana", Font.PLAIN, 40);

        SuperObject heart = new ObjHeart(gp);
        fullHeart = heart.image;
        halfHeart = heart.image2;
        emptyHeart = heart.image3;

        timer = new Timer();
        running = false;
        tempoDecorrido = 0;

        iniciarTimer(gp);
    }

    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        if (gp.currentState.getGameState() == gp.currentState.playState) {
            drawPlayerLife();
            drawTimeBar(); // Desenha a barra de tempo no estado de jogo
        }
        if (gp.currentState.getGameState() == gp.currentState.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        if (gp.currentState.getGameState() == gp.currentState.gameOverState) {
            Window w = javax.swing.SwingUtilities.getWindowAncestor(gp);
            w.dispose();
            drawGameOverScreen();
        }
        if (gp.currentState.getGameState() == gp.currentState.chooseState) {
            drawChooseScreen();
        }
    }

    public void drawPlayerLife() {
        int x = gp.tileSize / 2;
        int y = gp.tileSize / 2;
        int i = 0;

        while (i < gp.getPlayer().maxLife / 2) {
            g2.drawImage(emptyHeart, x, y, null);
            i++;
            x += gp.tileSize;
        }

        x = gp.tileSize / 2;
        y = gp.tileSize / 2;
        i = 0;

        while (i < gp.getPlayer().life) {
            g2.drawImage(halfHeart, x, y, null);
            i++;
            if (i < gp.getPlayer().life) {
                g2.drawImage(fullHeart, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawChooseScreen() {
        try {
            imagemFundo = ImageIO.read(getClass().getResourceAsStream("/res/mapas/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        g2.drawImage(imagemFundo, 0, 0, gp.tileSize * 16, gp.tileSize * 12, null);

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
        g2.drawImage(gp.getPlayer().down1, 0, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(fullHeart, gp.tileSize, 0, gp.tileSize, gp.tileSize, null);
        g2.drawImage(gp.partner.down1, gp.tileSize * 2, 0, gp.tileSize, gp.tileSize, null);

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 40F));

        text = "Opção1";
        x = gp.tileSize * 2;
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        BufferedImage scaledSprite1 = UtilityTool.scaleImage(gp.sManager.spriteSorteado1, gp.tileSize * 3,
                gp.tileSize * 3);
        g2.drawImage(scaledSprite1, x + gp.tileSize / 2, y - gp.tileSize * 4, null);

        text = "Opção2";
        x = gp.tileSize * 10;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize, y);
        }
        BufferedImage scaledSprite2 = UtilityTool.scaleImage(gp.sManager.spriteSorteado2, gp.tileSize * 3,
                gp.tileSize * 3);
        g2.drawImage(scaledSprite2, x + gp.tileSize / 2, y - gp.tileSize * 4, null);
    }

    public void drawPauseScreen() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "Pausado";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2;

        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text) {
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth / 2 - length / 2;
        return x;
    }

    public void drawTimeBar() {
        int elapsedTimeSeconds = (int) (tempoDecorrido / 1000); // Converte o tempo decorrido de milissegundos para
                                                                // segundos para realizar calculos
        int barWidth = (int) ((gp.screenWidth - 20) * (120 - elapsedTimeSeconds) / 120.0); // 120 segundos = 2 minutos

        if (elapsedTimeSeconds >= 30 && elapsedTimeSeconds < 120) { // Se o tempo decorrido for maior que 50 segundos e
                                                                    // menor que 2 minutos
            g2.setColor(Color.GREEN);
        } else {
            g2.setColor(Color.RED);
        }

        // Garante que a largura da barra não seja negativa ou maior que a largura da
        // tela
        barWidth = Math.max(0, barWidth);

        g2.fillRect(10, gp.screenHeight - 40, barWidth, 25); // Desenha a barra de tempo com Rectangle
    }

    public static void iniciarTimer(GamePanel gp) {
        if (!running) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    CurrentState currentState = gp.currentState;
                    if (currentState != null) {
                        if (currentState.getGameState() == currentState.pauseState) {
                            System.out.println("pausado");
                        } else if (currentState.getGameState() == currentState.gameOverState) {
                            pararTimer();
                        } else {
                            tempoDecorrido += 1000;

                            // Reduzir a vida em meio coração a cada 10 segundos
                            if (tempoDecorrido % (10 * 1000) == 0) {
                                int playerLife = gp.getPlayer().life;
                                if (playerLife > 0) {
                                    gp.getPlayer().life = playerLife - 1;
                                    if (gp.getPlayer().life <= 0) {
                                        currentState.setGameOverState();
                                    }
                                }
                            }

                            if (tempoDecorrido >= 30 * 1000 && !canReproduce) {
                                System.out.println("Possivel reproduzir");
                                canReproduce = true;
                            }
                            if (tempoDecorrido >= 2 * 60 * 1000) { // 2 minutos = 120 segundos = 120.000 milissegundos
                                pararTimer();
                                System.out.println("tempo esgotado");
                                currentState.setGameOverState();
                            }
                        }
                    } else {
                        // System.out.println("CurrentState é nulo.");
                    }
                }
            };

            timer.scheduleAtFixedRate(timerTask, 0, 1000);
            running = true;
        } else {
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

    public void drawLifeSpam() {
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 20F));
        g2.setColor(Color.white);
        g2.drawString("Tempo de vida: " + tempoDecorrido / 1000 + " segundos", 10, 20);
    }
}
