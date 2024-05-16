
package br.maua.teste;


public class TelaCreditos extends javax.swing.JFrame {

    /**
     * Creates new form TelaCreditos
     */
    public TelaCreditos() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        texto_creditos = new javax.swing.JLabel();
        menuzinho = new javax.swing.JLabel();
        fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        texto_creditos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/texto_creditos.png"))); // NOI18N
        getContentPane().add(texto_creditos);
        texto_creditos.setBounds(220, 130, 320, 330);

        menuzinho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menuzinho.png"))); // NOI18N
        menuzinho.setText("jLabel1");
        menuzinho.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        menuzinho.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                menuzinhoMouseClicked(evt);
            }
        });
        getContentPane().add(menuzinho);
        menuzinho.setBounds(350, 460, 80, 30);

        fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tela_creditos.png"))); // NOI18N
        getContentPane().add(fundo);
        fundo.setBounds(0, 0, 793, 525);

        pack();
    }// </editor-fold>                        

    private void menuzinhoMouseClicked(java.awt.event.MouseEvent evt) {                                       
        dispose();                         
        // Método para abrir a janela do jogo
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
        telaInicial.setLocationRelativeTo(null);
    }                                      

    /**
     * @param args the command line arguments
     */



    // Variables declaration - do not modify                     
    private javax.swing.JLabel fundo;
    private javax.swing.JLabel menuzinho;
    private javax.swing.JLabel texto_creditos;
    // End of variables declaration                   
}
