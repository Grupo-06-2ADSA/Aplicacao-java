package Entidades;

public class SistemaOperacional {
    private String nome;
    private Long tempoAtividade;
    private String fkMaquina;


    public SistemaOperacional(String nome, Long tempoAtividade, String fkMaquina) {
        this.nome = nome;
        this.tempoAtividade = tempoAtividade;
        this.fkMaquina = fkMaquina;
    }

    public String getNome() {
        return nome;
    }

    public Long getTempoAtividade() {
        return tempoAtividade;
    }

    public String getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(String fkMaquina) {
        this.fkMaquina = fkMaquina;
    }
}
