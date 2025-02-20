package br.maua.teste;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



import gamecontrol.assets.ConexaoBD;
import gamecontrol.assets.PerguntaERespostas;


public class SegundaPergunta extends javax.swing.JFrame {

    /**
     * Creates new form PrimeiraPergunta
     */
    public SegundaPergunta() {
        initComponents();
        fetchAndSetData();
    }

    private void fetchAndSetData() {
    ConexaoBD conexaoBD = new ConexaoBD();

    PerguntaERespostas perguntaERespostas = conexaoBD.getPerguntaERespostas(2); // Supondo que você quer a pergunta com ID 1

    if (perguntaERespostas != null) {
        jTextArea1.setText(perguntaERespostas.getPergunta());
        jTextArea2.setText(perguntaERespostas.getResposta1());
        jTextArea3.setText(perguntaERespostas.getResposta2());
        jTextArea4.setText(perguntaERespostas.getResposta3());
    }
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {



        jScrollPane4 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        titulo_pergunta = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        resposta1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        resposta2 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        resposta3 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        balao_resposta1C = new javax.swing.JLabel();
        balao_resposta1A = new javax.swing.JLabel();
        balao_resposta1B = new javax.swing.JLabel();
        balao_pergunta1 = new javax.swing.JLabel();
        darwin = new javax.swing.JLabel();
        imagem_fundo = new javax.swing.JLabel();

        jTextArea2.setLineWrap(true);
        jTextArea3.setLineWrap(true);
        jTextArea4.setLineWrap(true);

                

        //REMOVE O SCROLL DO JTEXTAREA DA PERGUNTA
        titulo_pergunta.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        
        //REMOVE O SCROLL DO JTEXTAREA DAS RESPOSTAS
        resposta1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        resposta2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        resposta3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        //REMOVE O SCROLL HORIZONTAL DO JTEXTAREA DA PERGUNTA'
        titulo_pergunta.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //REMOVE O SCROLL HORIZONTAL DO JTEXTAREA DAS RESPOSTAS
        resposta1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        resposta2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        resposta3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // DEFININDO A BORDA DAS PERGUNTAS COMO BRANCA
        titulo_pergunta.setBorder(null);

        // DEFININDO A BORDA DAS RESPOSTAS COMO BRANCA
        resposta1.setBorder(null);
        resposta2.setBorder(null);
        resposta3.setBorder(null);



        jTextArea1.setEditable(false);
        jTextArea2.setEditable(false);
        jTextArea3.setEditable(false);
        jTextArea4.setEditable(false);

        //DEFININDO O CURSOR PARA OS BALOES DAS RESPOSTAS
        balao_resposta1A.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        balao_resposta1B.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        balao_resposta1C.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // DEFININDO O CURSOR PARA OS JTEXTAREA DAS RESPOSTAS
        jTextArea2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTextArea3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTextArea4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setLineWrap(true); // Permite quebra de texto
        jTextArea1.setWrapStyleWord(true); // Quebra de palavra
        jTextArea1.setOpaque(false); // Remove o fundo
        jTextArea1.setFont(new java.awt.Font("Arial", 2, 18)); // Define a fonte. 2 é o estilo itálico

        jTextArea2.setFont(new java.awt.Font("Arial", 0, 16)); // Define a fonte. 2 é o estilo itálico
        jTextArea3.setFont(new java.awt.Font("Arial", 0, 16)); // Define a fonte. 2 é o estilo itálico
        jTextArea4.setFont(new java.awt.Font("Arial", 0, 16)); // Define a fonte. 2 é o estilo itálico
        
        
        // ADICIONANDO EVENTO DE CLIQUE NAS RESPOSTAS
        balao_resposta1A.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();

                RespostaErrada2 respostaErrada = new RespostaErrada2();
                respostaErrada.setVisible(true);
                respostaErrada.setLocationRelativeTo(null);
                

            }
        });
        jTextArea2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();

                RespostaErrada2 respostaErrada = new RespostaErrada2();
                respostaErrada.setVisible(true);
                respostaErrada.setLocationRelativeTo(null);
                

            }
        });

        balao_resposta1B.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();

                RespostaCorreta2 respostaCorreta = new RespostaCorreta2();
                respostaCorreta.setVisible(true);
                respostaCorreta.setLocationRelativeTo(null);

            }
        });
        jTextArea3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();

                RespostaCorreta2 respostaCorreta = new RespostaCorreta2();
                respostaCorreta.setVisible(true);
                respostaCorreta.setLocationRelativeTo(null);

            }
        });


        balao_resposta1C.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();

                RespostaErrada2 respostaErrada = new RespostaErrada2();
                respostaErrada.setVisible(true);
                respostaErrada.setLocationRelativeTo(null);

            }
        });
        jTextArea4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();

                RespostaErrada2 respostaErrada = new RespostaErrada2();
                respostaErrada.setVisible(true);
                respostaErrada.setLocationRelativeTo(null);
                
                
            }
        });


        

        jScrollPane4.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(768, 576));
        setPreferredSize(new java.awt.Dimension(768, 576));
        setSize(new java.awt.Dimension(768, 576));
        getContentPane().setLayout(null);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        titulo_pergunta.setViewportView(jTextArea1);

        getContentPane().add(titulo_pergunta);
        titulo_pergunta.setBounds(240, 80, 380, 90);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        resposta1.setViewportView(jTextArea2);

        getContentPane().add(resposta1);
        resposta1.setBounds(380, 280, 260, 40);

        jTextArea3.setColumns(20);
        jTextArea3.setRows(5);
        resposta2.setViewportView(jTextArea3);

        getContentPane().add(resposta2);
        resposta2.setBounds(380, 370, 260, 40);

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        resposta3.setViewportView(jTextArea4);

        getContentPane().add(resposta3);
        resposta3.setBounds(380, 460, 260, 40);

        balao_resposta1C.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/balao_branco_resposta.png"))); // NOI18N
        getContentPane().add(balao_resposta1C);
        balao_resposta1C.setBounds(360, 440, 305, 87);

        balao_resposta1A.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/balao_branco_resposta.png"))); // NOI18N
        getContentPane().add(balao_resposta1A);
        balao_resposta1A.setBounds(360, 260, 310, 87);

        balao_resposta1B.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/balao_branco_resposta.png"))); // NOI18N
        getContentPane().add(balao_resposta1B);
        balao_resposta1B.setBounds(360, 350, 305, 87);

        balao_pergunta1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/balao_branco_pergunta.png"))); // NOI18N
        getContentPane().add(balao_pergunta1);
        balao_pergunta1.setBounds(210, 50, 446, 194);

        darwin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/darwinframe.png"))); // NOI18N
        getContentPane().add(darwin);
        darwin.setBounds(30, 300, 215, 215);

        imagem_fundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tela_dialogo_resized.png"))); // NOI18N
        getContentPane().add(imagem_fundo);
        imagem_fundo.setBounds(0, 0, 768, 576);

        pack();
    }// </editor-fold>                        

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
            java.util.logging.Logger.getLogger(PrimeiraPergunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrimeiraPergunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrimeiraPergunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrimeiraPergunta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                SegundaPergunta SegundaPergunta = new SegundaPergunta();
                SegundaPergunta.setVisible(true);
                SegundaPergunta.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JLabel balao_pergunta1;
    private javax.swing.JLabel balao_resposta1A;
    private javax.swing.JLabel balao_resposta1B;
    private javax.swing.JLabel balao_resposta1C;
    private javax.swing.JLabel darwin;
    private javax.swing.JLabel imagem_fundo;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JScrollPane resposta1;
    private javax.swing.JScrollPane resposta2;
    private javax.swing.JScrollPane resposta3;
    private javax.swing.JScrollPane titulo_pergunta;
    // End of variables declaration                   
}
