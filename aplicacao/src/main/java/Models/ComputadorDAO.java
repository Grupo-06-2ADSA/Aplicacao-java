package Models;

import Conexao.Conexao;
import Entidades.Computador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ComputadorDAO {
    public static void cadastrarComputador(Computador computador) {
        String sql = "INSERT INTO Maquina (hostname, ip) VALUES (?, ?)";
        String verificarComputador = "select * from Maquina where hostname = ?";
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        ResultSet rs = null;

        try {
            ps1 = Conexao.getConexao().prepareStatement(verificarComputador);
            ps1.setString(1, computador.getHostName());
            rs = ps1.executeQuery();

            if (rs.next()) {
                System.out.println(" ");
                System.out.println("Computador já existe!");
                return;
            }
            ps2 = Conexao.getConexao().prepareStatement(sql);
            ps2.setString(1, computador.getHostName());
            ps2.setString(2, computador.getIpv4());
            ps2.execute();

            System.out.println("O computador foi cadastrado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public static boolean verificarComputador(Computador computador) {
        String sql = "select * from Maquina where hostname = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, computador.getHostName());
            rs = ps.executeQuery();

            if (rs.next()) {
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

    public static Integer buscarIdMaquina(Computador computador) {
        String sql = "select idMaquina from Maquina where hostname = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, computador.getHostName());
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("idMaquina");
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

