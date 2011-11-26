package Util;

import java.sql.Connection;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class Conecta {

    private final static String USER = "teste1";
    private final static String PASSWD = "1";
    private final static String SERVER = "RENATOODA-PC";
    Connection connection = null;
    private static Conecta instance = null;

    /**
     * Construtor da Classe
     * Inicializa a conexao
     */
    private Conecta() {
        inicializar();
    }

    /**
     * Metodo que carrega o driver e efetua a conexao com o BD
     */
    public void inicializar() {
        try {
            // Carregando o driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // Criando a conexao jdbc

            SQLServerDataSource ds = new SQLServerDataSource();
            ds.setServerName(SERVER);
            ds.setInstanceName("SQLEXPRESS");
            ds.setDatabaseName("Controle_Financeiro");
            ds.setUser(USER);
            ds.setPassword(PASSWD);

            connection = ds.getConnection();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo chamado pelas outras classes para solicitar a conexao com o BD
     */
    public Connection getConnection() {
        try {
            if (connection.isClosed()) {
                inicializar();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Metodo chamado por outras classes para instanciar um objeto do tipo Conecta
     * Este metodo verifica se uma instancia ja foi criada ou nao
     */
    public static Conecta getInstance() {
        if (instance == null) {
            instance = new Conecta();
        }

        return instance;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
