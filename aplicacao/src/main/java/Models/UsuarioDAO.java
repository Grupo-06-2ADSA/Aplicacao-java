package Models;

import Conexao.Conexao;
import Entidades.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {
    public static boolean verificarUsuario(Usuario usuario) {
        String sql = "select * from Funcionario where email = ? and senha = ?";
        PreparedStatement ps = null;
        PreparedStatement psSql = null;

        ResultSet rs = null;
        ResultSet rsSql = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, usuario.getEmail());
            ps.setString(2, usuario.getSenha());
            rs = ps.executeQuery();

            psSql = Conexao.getSQLConexao().prepareStatement(sql);
            psSql.setString(1, usuario.getEmail());
            psSql.setString(2, usuario.getSenha());
            rsSql = psSql.executeQuery();

            if (rs.next() && rsSql.next()) {
                System.out.println("Acesso liberado");
                return true;
            } else {
                System.out.println("Acesso negado");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao verificar usuário: Banco de dados - " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
