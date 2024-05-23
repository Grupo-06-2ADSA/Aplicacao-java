package Entidades;

public class SistemaOperacional {
    private String nome;
    private Long tempoAtividade;
    private Integer fkMaquina;


    public SistemaOperacional(String nome, Long tempoAtividade, Integer fkMaquina) {
        this.nome = nome;
        this.tempoAtividade = tempoAtividade;
        this.fkMaquina = fkMaquina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTempoAtividade() {
        return tempoAtividade;
    }

    public void setTempoAtividade(Long tempoAtividade) {
        this.tempoAtividade = tempoAtividade;
    }

    public Integer getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(Integer fkMaquina) {
        this.fkMaquina = fkMaquina;
    }
}
