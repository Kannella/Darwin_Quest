/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package br.maua.teste;


public class RespostaErrada extends javax.swing.JFrame {

    /**
     * Creates new form RespostaErrada
     */
    public RespostaErrada() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        ah_nao_imagem = new javax.swing.JLabel();
        darwin = new javax.swing.JLabel();
        TextoRespostaCorerta = new javax.swing.JLabel();
        joinha = new javax.swing.JLabel();
        botao_tentar_novamente = new javax.swing.JLabel();
        fundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(768, 576));
        getContentPane().setLayout(null);

        ah_nao_imagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ah_nao.png"))); // NOI18N
        getContentPane().add(ah_nao_imagem);
        ah_nao_imagem.setBounds(70, 80, 230, 120);

        darwin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/darwinframe.png"))); // NOI18N
        getContentPane().add(darwin);
        darwin.setBounds(20, 320, 215, 215);

        TextoRespostaCorerta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/resposta_errada.png"))); // NOI18N
        getContentPane().add(TextoRespostaCorerta);
        TextoRespostaCorerta.setBounds(300, 300, 410, 240);

        joinha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/joinha_negativo.png"))); // NOI18N
        getContentPane().add(joinha);
        joinha.setBounds(430, 90, 170, 150);

        botao_tentar_novamente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/botao_tentar_novamente.png"))); // NOI18N
        botao_tentar_novamente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        botao_tentar_novamente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botao_tentar_novamenteMouseClicked(evt);
            }
        });
        getContentPane().add(botao_tentar_novamente);
        botao_tentar_novamente.setBounds(580, 20, 180, 50);

        fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tela_dialogo_resized.png"))); // NOI18N
        getContentPane().add(fundo);
        fundo.setBounds(0, 0, 768, 576);

        pack();
    }// </editor-fold>                        

    private void botao_tentar_novamenteMouseClicked(java.awt.event.MouseEvent evt) {                                                    
        this.dispose();

        PrimeiraPergunta primeiraPergunta = new PrimeiraPergunta();
        primeiraPergunta.setVisible(true);
        primeiraPergunta.setLocationRelativeTo(null);
    }                                                   

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(RespostaErrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RespostaErrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RespostaErrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RespostaErrada.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RespostaErrada().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel TextoRespostaCorerta;
    private javax.swing.JLabel ah_nao_imagem;
    private javax.swing.JLabel botao_tentar_novamente;
    private javax.swing.JLabel darwin;
    private javax.swing.JLabel fundo;
    private javax.swing.JLabel joinha;
    // End of variables declaration                   
}
