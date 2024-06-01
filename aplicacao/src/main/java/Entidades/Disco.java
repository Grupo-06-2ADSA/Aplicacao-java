package Entidades;

public class Disco {
    private Long disponivel;
    private Long total;
    private Long emUso;
    private String fkMaquina;

    public Disco(Long disponivel, Long total, Long emUso, String fkMaquina) {
        this.disponivel = disponivel;
        this.total = total;
        this.emUso = emUso;
        this.fkMaquina = fkMaquina;
    }

    public Long getDisponivel() {
        return disponivel;
    }

    public Long getTotal() {
        return total;
    }

    public Long getEmUso() {
        return emUso;
    }

    public String getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(String fkMaquina) {
        this.fkMaquina = fkMaquina;
    }
}
