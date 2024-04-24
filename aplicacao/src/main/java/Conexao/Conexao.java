package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
        private static final String url = "jdbc:mysql://localhost:3306/MindCore";
        // jdbc:mysql://localhost:porta/bancodedados
        private static final String user = "root"; // nome do seu usuário
        private static final String password = ""; // sua senha

        private static Connection con;

        public static Connection getConexao(){
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                if(con == null){
                    con = DriverManager.getConnection(url, user, password);
                    return con;
                }else {
                    return con;
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
}
