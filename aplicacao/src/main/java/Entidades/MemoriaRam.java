package Entidades;

public class MemoriaRam {
    private Double emUso;
    private Double disponivel;
    private Double total;

    public MemoriaRam(Double emUso, Double disponivel, Double total) {
        this.emUso = emUso;
        this.disponivel = disponivel;
        this.total = total;
    }

    public Double getEmUso() {
        return emUso;
    }

    public void setEmUso(Double emUso) {
        this.emUso = emUso;
    }

    public Double getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(Double disponivel) {
        this.disponivel = disponivel;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
