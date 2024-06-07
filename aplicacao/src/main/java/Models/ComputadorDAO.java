package Models;

import Conexao.Conexao;
import Entidades.Computador;
import org.checkerframework.checker.units.qual.C;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ComputadorDAO {
    public static void cadastrarComputador(Computador computador) {
        String sql = "INSERT INTO Maquina (hostname, ip, fkEmpresa) VALUES (?, ?, ?)";

        PreparedStatement ps2 = null;
        PreparedStatement psSql = null;

        try {
            ps2 = Conexao.getConexao().prepareStatement(sql);
            ps2.setString(1, computador.getHostName());
            ps2.setString(2, computador.getIpv4());
            ps2.setString(3, computador.getFkEmpresa());
            ps2.execute();

            psSql = Conexao.getSQLConexao().prepareStatement(sql);
            psSql.setString(1, computador.getHostName());
            psSql.setString(2, computador.getIpv4());
            psSql.setString(3, computador.getFkEmpresa());
            psSql.execute();

            System.out.println("O computador foi cadastrado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean verificarComputador(Computador computador) {
        String sql = "select * from Maquina where hostname = ?";
        PreparedStatement ps = null;
        PreparedStatement psSql = null;

        ResultSet rs = null;
        ResultSet rsSQl = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, computador.getHostName());
            rs = ps.executeQuery();

            psSql = Conexao.getSQLConexao().prepareStatement(sql);
            psSql.setString(1, computador.getHostName());
            rsSQl = psSql.executeQuery();

            if (rs.next() && rsSQl.next()) {
                System.out.println();
                System.out.println("Máquina já cadastrada");
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

