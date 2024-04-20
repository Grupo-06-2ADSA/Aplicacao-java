package Entidades;

public class Processador {
    private String nome;
    private Double emUso;

    public Processador(String nome, Double emUso) {
        this.nome = nome;
        this.emUso = emUso;
    }

    public String getNome() {
        return nome;
    }

    public Double getEmUso() {
        return emUso;
    }
}
