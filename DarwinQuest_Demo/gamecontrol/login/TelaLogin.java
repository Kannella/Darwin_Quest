package gamecontrol.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TelaLogin extends JFrame implements ActionListener {

    //Atributos da classe TelaLogin que são utilizados para a criação da tela de login

    private JTextField campoUsuario; // É o campo onde o usuário irá digitar seu nome de usuário.
    private JPasswordField campoSenha; // É o campo onde o usuário irá digitar sua senha.
    private JButton botaoLogin; // É o botão que o usuário irá clicar para tentar fazer login.
    private LoginSuccessListener loginSuccessListener; // É uma interface que será usada para notificar a classe que chamou TelaLogin quando o login for bem-sucedido.

    //Construtor da classe TelaLogin que é responsável por criar a tela de login
    public TelaLogin() {
        setTitle("Login"); // Define o título da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define o comportamento padrão ao fechar a janela
        setSize(500, 150); // Define o tamanho da janela
        setLocationRelativeTo(null); // Centraliza a janela na tela

        setLayout(new GridLayout(3, 2)); // O GridLayout é um dos gerenciadores de layout fornecidos pelo Java Swing. Ele organiza os componentes em uma grade de células, onde cada componente é colocado em uma célula da grade. No caso, estamos usando um GridLayout com 3 linhas e 2 colunas. Portanto a janela terá 3 linhas e 2 colunas.

        JLabel labelUsuario = new JLabel("Usuário:"); //Aqui, estamos criando um rótulo (label) para o campo de usuário. Este rótulo exibirá o texto "Usuário:" ao lado do campo onde o usuário digitará seu nome de usuário.
        JLabel labelSenha = new JLabel("Senha:"); // Aqui, estamos criando um rótulo (label) para o campo de senha. Ele exibirá o texto "Senha:" ao lado do campo onde o usuário digitará sua senha.
        campoUsuario = new JTextField(10); // Aqui, estamos criando um campo de texto (JTextField) para que o usuário digite seu nome de usuário. O número 10 passado para o construtor indica o tamanho inicial do campo em caracteres.
        campoSenha = new JPasswordField(10); // Aqui, estamos criando um campo de senha (JPasswordField) para que o usuário digite sua senha. O número 10 passado para o construtor indica o tamanho inicial do campo em caracteres.
        botaoLogin = new JButton("Login"); // Aqui, estamos criando um botão (JButton) com o texto "Login". Este botão será clicado pelo usuário para tentar fazer login.

        // Adiciona os componentes à janela de login na ordem em que devem aparecer

        add(labelUsuario);
        add(campoUsuario);
        add(labelSenha);
        add(campoSenha);
        add(new JLabel());
        add(botaoLogin);

        botaoLogin.addActionListener(this); // Este metodo adiciona um ouvinte de ação ao botão de login. Quando o botão de login for clicado, o método actionPerformed() da classe atual (a classe TelaLogin, que implementa ActionListener) será chamado para lidar com o evento de clique do botão.
    }

    public void actionPerformed(ActionEvent e) {
        String usuario = campoUsuario.getText(); // Obtém o texto digitado no campo de usuário
        String senha = new String(campoSenha.getPassword()); // Obtém o texto digitado no campo de senha
        
        // Fazer a logica de autenticação aqui
        
        // Usando um exemplo para testar: vamos supor que o usuário e a senha corretos sejam "admin"
        if (usuario.equals("admin") && senha.equals("admin")) {
            if (loginSuccessListener != null) {
                loginSuccessListener.onLoginSuccess();
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos!", "Erro de Login", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setLoginSuccessListener(LoginSuccessListener listener) {
        this.loginSuccessListener = listener;
    }

    public interface LoginSuccessListener {
        void onLoginSuccess();
    }
}
