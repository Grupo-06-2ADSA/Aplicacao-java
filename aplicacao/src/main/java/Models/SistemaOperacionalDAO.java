package Models;

import Conexao.Conexao;
import Entidades.SistemaOperacional;

import java.sql.PreparedStatement;

public class SistemaOperacionalDAO {
    public static boolean cadastrarSO(SistemaOperacional sistemaOperacional){
        String sql = "INSERT INTO leituraSO (nome, tempoAtividade) VALUES (?, ?)";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, sistemaOperacional.getNome());
            ps.setLong(2, sistemaOperacional.getTempoAtividade());
            ps.execute();

            System.out.println("O Sistema Operacional foi cadastrado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
