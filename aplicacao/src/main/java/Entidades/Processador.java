package Entidades;

public class Processador {
    private String nome;
    private Double emUso;
    private Double temp;

    public Processador(String nome, Double emUso, Double temp) {
        this.nome = nome;
        this.emUso = emUso;
        this.temp = temp;
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
}
