package Models;

import Conexao.Conexao;
import Entidades.Processador;

import java.sql.PreparedStatement;

public class ProcessadorDAO {
    public static void cadastrarCPU(Processador processador){
        String sql = "INSERT INTO LeituraCPU (nome, emUso, temp, fkMaquina) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;
        PreparedStatement psSql = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, processador.getNome());
            ps.setDouble(2, processador.getEmUso());
            ps.setDouble(3, processador.getTemp());
            ps.setString(4, processador.getFkMaquina());
            ps.execute();

            psSql = Conexao.getSQLConexao().prepareStatement(sql);
            psSql.setString(1, processador.getNome());
            psSql.setDouble(2, processador.getEmUso());
            psSql.setDouble(3, processador.getTemp());
            psSql.setString(4, processador.getFkMaquina());
            psSql.execute();

            System.out.println("A CPU foi cadastrada com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
