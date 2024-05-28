package br.maua.teste;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PerpetuouBesouro extends javax.swing.JFrame {
    
    private String[] textos = {
            "PARABÉNS CARO ALUNO, VOCÊ CONSEGUIU PERPETUAR A ESPÉCIE DE BESOUROS!!",
            "AGORA SUA PROLE ESTÁ MAIS EVOLUÍDA E APTA AO AMBIENTE AO SEU REDOR!"
    };
    private int index = 0;
    private int textIndex = 0;
    private Timer timer;
    
    private JTextArea[] textAreas;

    public PerpetuouBesouro() {
        initComponents();
        iniciarAnimacaoTexto();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        fala_balao_2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        fala_balao_1 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        balaozao1 = new javax.swing.JLabel();
        balaozao2 = new javax.swing.JLabel();
        darwin = new javax.swing.JLabel();
        fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(768, 576));
        setSize(new java.awt.Dimension(768, 576));
        getContentPane().setLayout(null);

        textAreas = new JTextArea[]{jTextArea2, jTextArea3};
        configurarTextArea(jTextArea2);
        configurarTextArea(jTextArea3);

        configurarScrollPane(fala_balao_2, jTextArea2);
        configurarScrollPane(fala_balao_1, jTextArea3);

        getContentPane().add(fala_balao_2);
        fala_balao_2.setBounds(100, 80, 280, 100);

        getContentPane().add(fala_balao_1);
        fala_balao_1.setBounds(350, 310, 280, 100);

        balaozao1.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        balaozao1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        balaozao1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/balao_grande.png"))); // NOI18N
        balaozao1.setToolTipText("");
        getContentPane().add(balaozao1);
        balaozao1.setBounds(60, 40, 360, 180);

        balaozao2.setFont(new java.awt.Font("Arial", 0, 13)); // NOI18N
        balaozao2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        balaozao2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/balao_grande.png"))); // NOI18N
        balaozao2.setToolTipText("");
        getContentPane().add(balaozao2);
        balaozao2.setBounds(310, 260, 360, 200);

        darwin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/darwinframe.png"))); // NOI18N
        getContentPane().add(darwin);
        darwin.setBounds(20, 310, 210, 220);

        fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fundo_pos_besouro.png"))); // NOI18N
        getContentPane().add(fundo);
        fundo.setBounds(0, 0, 770, 560);

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
        scrollPane.setBounds(50, 40, 400, 80);
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
                    }else{
                        dispose();
                        PrimeiraPergunta priPergunta = new PrimeiraPergunta();
                        priPergunta.setVisible(true);
                        priPergunta.setLocationRelativeTo(null);
                    }
                }
            }
        });
        timer.start();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new PerpetuouBesouro().setVisible(true);
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel balaozao1;
    private javax.swing.JLabel balaozao2;
    private javax.swing.JLabel darwin;
    private javax.swing.JScrollPane fala_balao_1;
    private javax.swing.JScrollPane fala_balao_2;
    private javax.swing.JLabel fundo;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    // End of variables declaration                   
}
