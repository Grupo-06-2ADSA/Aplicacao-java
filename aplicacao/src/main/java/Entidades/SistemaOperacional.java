package Entidades;

public class SistemaOperacional {
    private String nome;
    private Long tempoAtividade;

    public SistemaOperacional(String nome, Long tempoAtividade) {
        this.nome = nome;
        this.tempoAtividade = tempoAtividade;
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
}
