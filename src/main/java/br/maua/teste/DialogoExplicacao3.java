package br.maua.teste;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

import gamecontrol.main.GamePanel;
import gamecontrol.main.UtilityTool;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogoExplicacao3 extends javax.swing.JFrame {
    // CurrentState CS = new CurrentState();
    GamePanel gp;
    private String[] textos = {
            "Dentro da minha teoria, é Importante frisar que o que determina a boa adaptação de uma espécie",
            "É o ambiente. Pois como suas mutações são completamente aleatórias, a espécie só prosperará se as mutações forem boas para o ambiente que ela está."
    };
    private int index = 0;
    private int textIndex = 0;
    private Timer timer;

    private JTextArea[] textAreas;

    public DialogoExplicacao3() {
        initComponents();
        iniciarAnimacaoTexto();
    }
    public DialogoExplicacao3(GamePanel gp) {
        initComponents();
        iniciarAnimacaoTexto();
        setGp(gp);
    }
    private void setGp(GamePanel gp){
        this.gp=gp;
    }
    private GamePanel getGp(){
        return gp;
    }

    private void initComponents() {

        fala_balao = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        fala_balao1 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        darwin = new javax.swing.JLabel();
        primeiro_dialogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(768, 576));
        getContentPane().setLayout(null);

        textAreas = new JTextArea[]{jTextArea3, jTextArea2}; // Primeiro o balão de cima, depois o de baixo
        configurarTextArea(jTextArea2);
        configurarTextArea(jTextArea3);

        configurarScrollPane(fala_balao, jTextArea2);
        configurarScrollPane(fala_balao1, jTextArea3);

        getContentPane().add(fala_balao);
        getContentPane().add(fala_balao1);

        fala_balao.setBounds(300, 340, 420, 80);
        fala_balao1.setBounds(90, 120, 420, 80);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 13));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/caixa_dialogo.png")));
        jLabel3.setToolTipText("");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(260, 310, 490, 140);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 13));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/caixa_dialogo.png")));
        jLabel1.setToolTipText("");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 90, 490, 140);

        darwin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/darwinframe.png")));
        getContentPane().add(darwin);
        darwin.setBounds(20, 310, 210, 220);

        primeiro_dialogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tela_dialogo_resized.png")));
        primeiro_dialogo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        primeiro_dialogo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                primeiro_dialogoMouseClicked(evt);
            }
        });
        getContentPane().add(primeiro_dialogo);
        primeiro_dialogo.setBounds(0, 0, 768, 576);

        pack();
    }

    private void configurarTextArea(JTextArea textArea) {
        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setBorder(new LineBorder(Color.WHITE, 1));
        textArea.setFont(new Font("Arial", Font.PLAIN, 18));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
    }

    private void configurarScrollPane(JScrollPane scrollPane, JTextArea textArea) {
        scrollPane.setViewportView(textArea);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBorder(null);

        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setPreferredSize(new Dimension(10, verticalScrollBar.getHeight()));
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }

            private JButton createZeroButton() {
                JButton button = new JButton();
                Dimension zeroDim = new Dimension(0, 0);
                button.setPreferredSize(zeroDim);
                button.setMinimumSize(zeroDim);
                button.setMaximumSize(zeroDim);
                return button;
            }

            @Override
            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
                // No track painting
            }

            @Override
            protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                Color color;
                JScrollBar sb = (JScrollBar) c;
                if (!sb.isEnabled() || thumbBounds.height > thumbBounds.width) {
                    return;
                } else if (isDragging) {
                    color = new Color(0, 153, 255);
                } else if (isThumbRollover()) {
                    color = new Color(102, 102, 102);
                } else {
                    color = new Color(192, 192, 192);
                }
                g2.setPaint(color);
                g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
                g2.setPaint(Color.BLACK);
                g2.drawRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, 10, 10);
                g2.dispose();
            }
        });
    }

    private void iniciarAnimacaoTexto() {
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (index < textos[textIndex].length()) {
                    textAreas[textIndex].append(String.valueOf(textos[textIndex].charAt(index)));
                    index++;
                } else {
                    timer.stop();
                    if (textIndex < textos.length - 1) {
                        textIndex++;
                        index = 0;
                        timer.start();
                    }
                }
            }
        });
        timer.start();
    }

    private void primeiro_dialogoMouseClicked(java.awt.event.MouseEvent evt) {
        getGp().getWindow().setVisible(true);
        UtilityTool.setAcabouLer(true);
        dispose(); 
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DialogoExplicacao3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            DialogoExplicacao3 dialog = new DialogoExplicacao3();
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel darwin;
    private javax.swing.JScrollPane fala_balao;
    private javax.swing.JScrollPane fala_balao1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JLabel primeiro_dialogo;
    // End of variables declaration                   
}
