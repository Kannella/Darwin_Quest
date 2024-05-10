package gamecontrol.assets;


//Essas linhas importam as classes necessárias do pacote java.sql, que são utilizadas para realizar operações relacionadas ao banco de dados, como conexão, execução de consultas preparadas e manipulação de resultados.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


//Esta linha abaixo declara a classe ConexaoBD, que é responsável por gerenciar a conexão com o banco de dados e fornecer métodos para operações relacionadas ao banco de dados, como obter conexão, fechar conexão e autenticar usuário.
public class ConexaoBD {

    // Essas linhas abaixo declaram constantes que representam a URL de conexão com o banco de dados, o nome de usuário e a senha para autenticação no banco de dados MySQL.
    private static final String URL = "jdbc:mysql://localhost:3306/darwin_quest";
    private static final String USUARIO = "root";
    private static final String SENHA = "23006420";

    // Esta linha abaixo declara um método público e estático chamado obterConexao(), que retorna um objeto do tipo Connection (conexão com o banco de dados) e pode lançar uma exceção do tipo SQLException.
    public static Connection obterConexao() throws SQLException {

        //Esta linha abaixo declara uma variável conexao do tipo Connection e a inicializa como null.
        Connection conexao = null; 

        //Esta linha abaixo inicia um bloco de código try, onde o código que pode lançar uma exceção é colocado.
        try {
            // Esta linha abaixo carrega o driver JDBC para o MySQL. O método forName() da classe Class é usado para carregar dinamicamente a classe do driver.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Esta linha abaixo obtém uma conexão com o banco de dados usando a URL, nome de usuário e senha fornecidos.
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);

        // Esta linha abaixo inicia um bloco catch para capturar uma exceção do tipo ClassNotFoundException, que pode ser lançada se o driver JDBC não for encontrado.    
        } catch (ClassNotFoundException e) {
            
            System.err.println("Erro ao carregar o driver JDBC: " + e.getMessage()); //Esta linha imprime uma mensagem de erro no console se ocorrer um erro ao carregar o driver JDBC.
        }
        return conexao; //Esta linha retorna o objeto de conexão, que pode ser null se ocorrer um erro ao obter a conexão.
    }

    // Método para fechar a conexão com o banco de dados
    public static void fecharConexao(Connection conexao) {
        if (conexao != null) { //Esta linha verifica se a conexão não é nula.
            try {
                conexao.close(); // Se a conexão não for nula, o método close() é chamado para fechar a conexão com o banco de dados.
            } catch (SQLException e) { // Esta linha inicia um bloco catch para capturar uma exceção do tipo SQLException, que pode ser lançada ao fechar a conexão se ocorrer um erro ao fechar a conexão
                
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
            }
        }
    }

    // Esta linha abaixo declara o método autenticarUsuario como público e estático. Ele recebe dois parâmetros: email e senha, ambos do tipo String. O método retorna um valor booleano indicando se o usuário foi autenticado com sucesso ou não. Ele também declara que pode lançar uma exceção do tipo SQLException.
    public static boolean autenticarUsuario(String email, String senha) throws SQLException {



    // Estas linhas declaram variáveis para a conexão com o banco de dados (conexao), o comando SQL preparado (stmt), o conjunto de resultados (rs) e uma variável booleana indicando se o usuário foi autenticado com sucesso (autenticado). Elas são inicializadas como null ou false.

    //Um comando SQL preparado é uma maneira de executar consultas SQL no banco de dados de forma segura e eficiente. Ele é usado para pré-compilar uma consulta SQL que pode ter parâmetros variáveis e, em seguida, substituir esses parâmetros pelos valores apropriados antes de executar a consulta. Nesse contexto, stmt é uma abreviação comum para uma variável do tipo PreparedStatement, que é uma interface no Java usada para representar um comando SQL preparado. Essa variável é usada para criar, configurar e executar consultas SQL parametrizadas no banco de dados.

        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean autenticado = false;
        
        try {
            // Esta linha obtém uma conexão com o banco de dados chamando o método obterConexao da classe ConexaoBD.
            conexao = obterConexao();
            
            // Esta linha define a consulta SQL para verificar se o usuário e a senha correspondem a alguma entrada no banco de dados. Ela seleciona todas as colunas da tabela jogadores onde o email e a senha correspondem aos valores fornecidos.
            String sql = "SELECT * FROM jogadores WHERE email = ? AND senha = ?";

            stmt = conexao.prepareStatement(sql); // Esta linha prepara o comando SQL para execução usando o método prepareStatement do objeto de conexão.

            //Estas linhas abaixo definem os valores dos parâmetros na consulta preparada. O método setString é usado para substituir os ? na consulta pelo email e senha fornecidos.
            stmt.setString(1, email);
            stmt.setString(2, senha);
            
            // Esta linha executa a consulta preparada e armazena o resultado em um objeto ResultSet.
            rs = stmt.executeQuery();
            
            // Esta linha verifica se há pelo menos uma linha no conjunto de resultados usando o método next() do ResultSet. Se houver pelo menos uma linha, significa que o usuário foi autenticado com sucesso.
            autenticado = rs.next();

        //Esta linha inicia um bloco finally, que é executado independentemente de ocorrer uma exceção ou não.
        } finally {
            // Estas linhas abaixo fecham os recursos (ResultSet, PreparedStatement e conexão) no bloco finally para garantir que sejam liberados adequadamente, mesmo se ocorrer uma exceção durante a execução do método.
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            fecharConexao(conexao);
        }
        
        return autenticado; //Esta linha retorna o valor da variável autenticado, indicando se o usuário foi autenticado com sucesso ou não. Se autenticado for true, o usuário foi autenticado com sucesso; caso contrário, o usuário não foi autenticado com sucesso.
    }
}

