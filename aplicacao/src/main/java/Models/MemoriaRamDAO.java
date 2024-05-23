package Models;

import Conexao.Conexao;
import Entidades.MemoriaRam;

import java.sql.PreparedStatement;

public class MemoriaRamDAO {
    public static boolean cadastrarRAM(MemoriaRam memoriaRam){
        String sql = "INSERT INTO LeituraMemoriaRam (emUso, disponivel, total, fkMaquina) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setDouble(1, memoriaRam.getEmUso());
            ps.setDouble(2, memoriaRam.getDisponivel());
            ps.setDouble(3, memoriaRam.getTotal());
            ps.setInt(4, memoriaRam.getFkMaquina());
            ps.execute();

            System.out.println("A Memória RAM foi cadastrada com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
