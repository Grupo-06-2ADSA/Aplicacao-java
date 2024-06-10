package Entidades;

public class Disco {
    private Double disponivel;
    private Double total;
    private Double emUso;
    private String fkMaquina;

    public Disco(Double disponivel, Double total, Double emUso, String fkMaquina) {
        this.disponivel = disponivel;
        this.total = total;
        this.emUso = emUso;
        this.fkMaquina = fkMaquina;
    }

    public Double getDisponivel() {
        return disponivel;
    }

    public Double getTotal() {
        return total;
    }

    public Double getEmUso() {
        return emUso;
    }

    public String getFkMaquina() {
        return fkMaquina;
    }
}
