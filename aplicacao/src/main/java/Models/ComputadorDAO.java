package Models;

import Conexao.Conexao;
import Entidades.Computador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ComputadorDAO {
    public static boolean verificarComputador(Computador computador){
        String sql = "select * from Maquina where hostname = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, computador.getHostName());
            rs = ps.executeQuery();

            if (rs.next()){
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

            if (rs.next()){
                return rs.getInt("idMaquina");
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

