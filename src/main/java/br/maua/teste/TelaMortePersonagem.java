package br.maua.teste;

public class TelaMortePersonagem extends javax.swing.JFrame {


    public TelaMortePersonagem() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    
    private void initComponents() {

        escrita_gameover = new javax.swing.JLabel();
        menu_morte = new javax.swing.JLabel();
        jogar_novamente = new javax.swing.JLabel();
        fundo_gameover = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(768, 576));
        setMinimumSize(new java.awt.Dimension(768, 576));
        setResizable(false);
        setSize(new java.awt.Dimension(768, 576));
        getContentPane().setLayout(null);

        escrita_gameover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/escrita.png"))); // NOI18N
        getContentPane().add(escrita_gameover);
        escrita_gameover.setBounds(160, 130, 442, 74);

        menu_morte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu_botao.png"))); // NOI18N
        menu_morte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menu_morte.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menu_morteMouseClicked(evt);
            }
        });
        getContentPane().add(menu_morte);
        menu_morte.setBounds(280, 380, 212, 54);

        jogar_novamente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/jogar_novamente.png"))); // NOI18N
        jogar_novamente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jogar_novamente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jogar_novamenteMouseClicked(evt);
            }
        });
        getContentPane().add(jogar_novamente);
        jogar_novamente.setBounds(270, 300, 240, 69);

        fundo_gameover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fundo_gameover.png"))); // NOI18N
        getContentPane().add(fundo_gameover);
        fundo_gameover.setBounds(0, 0, 863, 566);

        pack();
    }// </editor-fold>                        

    private void jogar_novamenteMouseClicked(java.awt.event.MouseEvent evt) {                                             
        System.out.println("Clicou em jogar novamente");
    }                                            

    private void menu_morteMouseClicked(java.awt.event.MouseEvent evt) {   
                                    
        // Método para abrir a janela do jogo
        TelaInicial telaInicial = new TelaInicial();
        // Método para abrir a janela de login
        telaInicial.setVisible(true);
        telaInicial.setLocationRelativeTo(null);
        
    }                                       

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify                     
    private javax.swing.JLabel escrita_gameover;
    private javax.swing.JLabel fundo_gameover;
    private javax.swing.JLabel jogar_novamente;
    private javax.swing.JLabel menu_morte;
    // End of variables declaration                   
}
