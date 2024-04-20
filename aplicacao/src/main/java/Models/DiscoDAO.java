package Models;

import Conexao.Conexao;
import Entidades.Disco;

import java.sql.PreparedStatement;

public class DiscoDAO {
    public static boolean cadastrarDisco(Disco disco){
        String sql = "INSERT INTO leituraDisco (tamanho, leituras, bytesLeitura, escritas, bytesEscrita, tempoTransferencia) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setDouble(1, disco.getTamanho());
            ps.setDouble(2, disco.getLeituras());
            ps.setDouble(3, disco.getBytesLeitura());
            ps.setDouble(4, disco.getEscritas());
            ps.setDouble(5, disco.getBytesEscrita());
            ps.setLong(6, disco.getTempoTranferencia());
            ps.execute();

            System.out.println("O Disco foi cadastrado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}
