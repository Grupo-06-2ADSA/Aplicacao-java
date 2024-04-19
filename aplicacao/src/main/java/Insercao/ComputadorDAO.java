package Insercao;

import Conexao.Conexao;
import Entidades.Computador;

import java.sql.PreparedStatement;

public class ComputadorDAO {
    public static boolean cadastrarComputador(Computador computador){
        String sql = "INSERT INTO infoPc (sistemaOperacional, memoriaUso, discoUso) VALUES (?, ?, ?)";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, computador.getSistemaOperacional());
            ps.setDouble(2, computador.getMemoriaUso());
            ps.setDouble(3, computador.getDiscoUso());
            ps.execute();

            System.out.println("O computador foi cadastrado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}

