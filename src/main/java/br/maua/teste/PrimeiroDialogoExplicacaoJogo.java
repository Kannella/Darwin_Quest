package br.maua.teste;

import javax.swing.*;
import javax.swing.border.LineBorder;
import gamecontrol.main.Main;
import javax.swing.plaf.basic.BasicScrollBarUI;

import java.awt.*;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

    public class PrimeiroDialogoExplicacaoJogo extends javax.swing.JFrame {

        private String[] textos = {
                "Você jogará com um animal, e deve ajudá-lo a perpetuar sua espécie! A barrinha vermelha na parte de baixo da tela indica o tempo de vida do animal.",
                "Quando ela se torna verde, significa que ela está pronta para procriar. Quando isso acontecer ache outro de sua espécie!",
                "Lembre-se de ficar longe de inimigos, eles podem te matá-lo! Você pode se esconder debaixo dos troncos, e correr mais rápido que eles, funciona também!"
        };
        private int index = 0;
        private int textIndex = 0;
        private Timer timer;

        private JTextArea[] textAreas;

        /**
         * Creates new form PrimeiroDialogoExplicacaoJogo
         */
        public PrimeiroDialogoExplicacaoJogo() {
            initComponents();
            iniciarAnimacaoTexto();
        }

        @SuppressWarnings("unchecked")
        private void initComponents() {

            primeira_fala = new javax.swing.JScrollPane();
            segunda_fala = new javax.swing.JScrollPane();
            terceira_fala = new javax.swing.JScrollPane();

            jTextArea1 = new javax.swing.JTextArea();
            jTextArea2 = new javax.swing.JTextArea();
            jTextArea3 = new javax.swing.JTextArea();

            jLabel1 = new javax.swing.JLabel();
            jLabel2 = new javax.swing.JLabel();
            jLabel3 = new javax.swing.JLabel();

            darwin = new javax.swing.JLabel();
            primeiro_dialogo = new javax.swing.JLabel();

            setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
            getContentPane().setLayout(null);

            textAreas = new JTextArea[]{jTextArea1, jTextArea2, jTextArea3};
            configurarTextArea(jTextArea1);
            configurarTextArea(jTextArea2);
            configurarTextArea(jTextArea3);

            configurarScrollPane(primeira_fala, jTextArea1);
            configurarScrollPane(segunda_fala, jTextArea2);
            configurarScrollPane(terceira_fala, jTextArea3);

            getContentPane().add(primeira_fala);
            getContentPane().add(segunda_fala);
            getContentPane().add(terceira_fala);

            // Ajuste a posição para mover um pouco para a direita
            primeira_fala.setBounds(50, 40, 400, 80);  // Movido para a direita
            segunda_fala.setBounds(310, 180, 400, 80); // Movido para a direita
            terceira_fala.setBounds(310, 360, 400, 80); // Movido para a direita

            jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/caixa_dialogo.png")));
            getContentPane().add(jLabel1);
            jLabel1.setBounds(10, 10, 490, 140);

            jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/caixa_dialogo.png")));
            getContentPane().add(jLabel2);
            jLabel2.setBounds(260, 160, 490, 140);

            jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/caixa_dialogo.png")));
            getContentPane().add(jLabel3);
            jLabel3.setBounds(260, 340, 490, 140);

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

            setSize(new Dimension(780, 600));
            setLocationRelativeTo(null);
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
            scrollPane.setBounds(50, 40, 400, 80); // Posição e tamanho do JScrollPane
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setBorder(null); // Removendo a borda do JScrollPane
            
            // Ajuste para alinhar a barra de rolagem à direita
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
            Main.abrirJanelaDoJogo();
            Main.setGameStage(0);
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
                java.util.logging.Logger.getLogger(PrimeiroDialogoExplicacaoJogo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

            java.awt.EventQueue.invokeLater(() -> {
                PrimeiroDialogoExplicacaoJogo dialog = new PrimeiroDialogoExplicacaoJogo();
                dialog.setVisible(true);
            });
        }

        // Variables declaration - do not modify
        private javax.swing.JLabel darwin;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JTextArea jTextArea1;
        private javax.swing.JTextArea jTextArea2;
        private javax.swing.JTextArea jTextArea3;
        private javax.swing.JScrollPane primeira_fala;
        private javax.swing.JScrollPane segunda_fala;
        private javax.swing.JScrollPane terceira_fala;
        private javax.swing.JLabel primeiro_dialogo;
        // End of variables declaration
    }
