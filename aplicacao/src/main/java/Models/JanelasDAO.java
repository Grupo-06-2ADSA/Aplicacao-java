package Models;

import Conexao.Conexao;
import Entidades.Janelas;

import java.sql.PreparedStatement;

public class JanelasDAO {
    public static boolean cadastrarJanelas(Janelas janelas){
        String sql = "INSERT INTO LeituraJanelas (idJanela, titulo, pid, totalJanelas) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);

            System.out.println("Abrindo nova conexão");
            ps.setLong(1, janelas.getId());
            ps.setString(2, janelas.getTitulo());
            ps.setLong(3, janelas.getPid());
            ps.setInt(4, janelas.getTotal());

            ps.execute();

            System.out.printf("A Janela %s foi cadastrada com sucesso!%n", janelas.getTitulo());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
