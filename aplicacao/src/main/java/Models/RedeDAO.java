package Models;

import Conexao.Conexao;
import Entidades.Rede;

import java.sql.PreparedStatement;

public class RedeDAO {
    public static boolean cadastrarRede(Rede rede){
        String sql = "INSERT INTO leituraRede (nome, bytesRecebidos, bytesEnviados, pacotesRecebidos, pacotesEnviados) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, rede.getNome());
            ps.setLong(2, rede.getBytesRecebidos());
            ps.setLong(3, rede.getBytesEnviados());
            ps.setLong(4, rede.getPacotesRecebidos());
            ps.setLong(5, rede.getPacotesEnviados());
            ps.execute();

            System.out.println("A Rede foi cadastrada com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
