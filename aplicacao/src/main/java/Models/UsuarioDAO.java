package Models;

import Conexao.Conexao;
import Entidades.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {
    public static boolean verificarUsuario(Usuario usuario){
        String sql = "select * from Funcionario where email = ? and senha = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenha());
            rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Acesso liberado");
                return true;
            } else {
                System.out.println("Acesso negado");
                Usuario.FazerLogin();
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
