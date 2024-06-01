package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://bd:3306/MindCore";
    // jdbc:mysql://localhost:porta/bancodedados
    private static final String user = "root"; // nome do seu usuário
    private static final String password = "mindcore123grupo6"; // sua senha
    private static Connection con;

    private static final String sqlServerConnectionUrl = "jdbc:sqlserver://3.225.222.139:1433;" + "databaseName=MindCore";
    private static final String sqlServerUsername = "sa";
    private static final String sqlServerPassword = "mindcore123";
    private static Connection sqlServerCon;

    public static Connection getConexao() {
        try {
            if (con == null || con.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, user, password);
            }

            return con;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static Connection getSQLConexao(){
        try {
            if (sqlServerCon == null || sqlServerCon.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                sqlServerCon = DriverManager.getConnection(sqlServerConnectionUrl, sqlServerUsername, sqlServerPassword);
            }

            return sqlServerCon;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
