package Models;

import Conexao.Conexao;
import Entidades.Janelas;

import java.sql.PreparedStatement;

public class JanelasDAO {
    public static void cadastrarJanelas(Janelas janelas) {
        String sql = "INSERT INTO LeituraJanelas (identificador, titulo, pid, totalJanelas, fkMaquina) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;
        PreparedStatement psSql = null;


        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setLong(1, janelas.getId());
            ps.setString(2, janelas.getTitulo());
            ps.setLong(3, janelas.getPid());
            ps.setInt(4, janelas.getTotal());
            ps.setString(5, janelas.getFkMaquina());
            ps.execute();

            psSql = Conexao.getSQLConexao().prepareStatement(sql);
            psSql.setLong(1, janelas.getId());
            psSql.setString(2, janelas.getTitulo());
            psSql.setLong(3, janelas.getPid());
            psSql.setInt(4, janelas.getTotal());
            psSql.setString(5, janelas.getFkMaquina());
            psSql.execute();

            System.out.println("A Janela foi cadastrada com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
