package Entidades;

public class Processador {
    private final String nome;
    private final Double emUso;
    private final Double temp;
    private final Integer fkMaquina;


    public Processador(String nome, Double emUso, Double temp, Integer fkMaquina) {
        this.nome = nome;
        this.emUso = emUso;
        this.temp = temp;
        this.fkMaquina = fkMaquina;
    }

    public String getNome() {
        return nome;
    }

    public Double getEmUso() {
        return emUso;
    }

    public Double getTemp() {
        return temp;
    }

    public Integer getFkMaquina() {
        return fkMaquina;
    }
}
