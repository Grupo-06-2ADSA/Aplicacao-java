package Models;

import Conexao.Conexao;
import Entidades.SistemaOperacional;

import java.sql.PreparedStatement;

public class SistemaOperacionalDAO {
    public static void cadastrarSO(SistemaOperacional sistemaOperacional){
        String sql = "INSERT INTO LeituraSO (nome, tempoAtividade, fkMaquina) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        PreparedStatement psSql = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, sistemaOperacional.getNome());
            ps.setLong(2, sistemaOperacional.getTempoAtividade());
            ps.setString(3, sistemaOperacional.getFkMaquina());
            ps.execute();

            psSql = Conexao.getSQLConexao().prepareStatement(sql);
            psSql.setString(1, sistemaOperacional.getNome());
            psSql.setLong(2, sistemaOperacional.getTempoAtividade());
            psSql.setString(3, sistemaOperacional.getFkMaquina());
            psSql.execute();

            System.out.println("O Sistema Operacional foi cadastrado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
