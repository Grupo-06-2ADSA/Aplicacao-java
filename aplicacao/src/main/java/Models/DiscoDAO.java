package Models;

import Conexao.Conexao;
import Entidades.Disco;

import java.sql.PreparedStatement;

public class DiscoDAO {
    public static void cadastrarDisco(Disco disco){
        String sql = "INSERT INTO LeituraDisco (disponivel, total, emUso, fkMaquina) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;
        PreparedStatement psSql = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setDouble(1, disco.getDisponivel());
            ps.setDouble(2, disco.getTotal());
            ps.setDouble(3, disco.getEmUso());
            ps.setString(4, disco.getFkMaquina());
            ps.execute();

            psSql = Conexao.getSQLConexao().prepareStatement(sql);
            psSql.setDouble(1, disco.getDisponivel());
            psSql.setDouble(2, disco.getTotal());
            psSql.setDouble(3, disco.getEmUso());
            psSql.setString(4, disco.getFkMaquina());
            psSql.execute();

            System.out.println("O Disco foi cadastrado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
