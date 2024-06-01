package Models;

import Conexao.Conexao;
import Entidades.MemoriaRam;

import java.sql.PreparedStatement;

public class MemoriaRamDAO {
    public static void cadastrarRAM(MemoriaRam memoriaRam){
        String sql = "INSERT INTO LeituraMemoriaRam (emUso, disponivel, total, fkMaquina) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;
        PreparedStatement psSql = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setDouble(1, memoriaRam.getEmUso());
            ps.setDouble(2, memoriaRam.getDisponivel());
            ps.setDouble(3, memoriaRam.getTotal());
            ps.setString(4, memoriaRam.getFkMaquina());
            ps.execute();

            psSql = Conexao.getSQLConexao().prepareStatement(sql);
            psSql.setDouble(1, memoriaRam.getEmUso());
            psSql.setDouble(2, memoriaRam.getDisponivel());
            psSql.setDouble(3, memoriaRam.getTotal());
            psSql.setString(4, memoriaRam.getFkMaquina());
            psSql.execute();

            System.out.println("A Memória RAM foi cadastrada com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
