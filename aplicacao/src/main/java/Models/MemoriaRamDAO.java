package Models;

import Conexao.Conexao;
import Entidades.MemoriaRam;

import java.sql.PreparedStatement;

public class MemoriaRamDAO {
    public static boolean cadastrarRAM(MemoriaRam memoriaRam){
        String sql = "INSERT INTO leituraMemoriaRam (emUso, disponivel, total) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setDouble(1, memoriaRam.getEmUso());
            ps.setDouble(2, memoriaRam.getDisponivel());
            ps.setDouble(3, memoriaRam.getTotal());
            ps.execute();

            System.out.println("A Memória RAM foi cadastrada com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
