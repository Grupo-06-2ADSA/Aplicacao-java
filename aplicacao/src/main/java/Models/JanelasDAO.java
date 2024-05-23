package Models;

import Conexao.Conexao;
import Entidades.Janelas;

import java.sql.PreparedStatement;

public class JanelasDAO {
    public static boolean cadastrarJanelas(Janelas janelas) {
        String sql = "INSERT INTO LeituraJanelas (identificador, titulo, pid, totalJanelas, fkMaquina) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setLong(1, janelas.getId());
            ps.setString(2, janelas.getTitulo());
            ps.setLong(3, janelas.getPid());
            ps.setInt(4, janelas.getTotal());
            ps.setInt(5, janelas.getFkMaquina());
            ps.execute();

            System.out.println("A Janela foi cadastrada com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return true;
    }
}
