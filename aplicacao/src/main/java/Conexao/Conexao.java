package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String url = "jdbc:mysql://localhost:3306/MindCore";
        // jdbc:mysql://localhost:porta/bancodedados
        private static final String user = "root"; // nome do seu usuário
        private static final String password = "25101724"; // sua senha

        private static Connection con;

        public static Connection getConexao(){
            try {
                if(con == null){
                    con = DriverManager.getConnection(url, user, password);
                    return con;
                }else {
                    return con;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
}
