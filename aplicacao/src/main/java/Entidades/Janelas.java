package Entidades;

public class Janelas {
    private Long id;
    private String titulo;
    private Long pid;
    private Integer total;
    private String fkMaquina;

    public Janelas(Long id, String titulo, Long pid, Integer total, String fkMaquina) {
        this.id = id;
        this.titulo = titulo;
        this.pid = pid;
        this.total = total;
        this.fkMaquina = fkMaquina;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Long getPid() {
        return pid;
    }

    public Integer getTotal() {
        return total;
    }

    public String getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(String fkMaquina) {
        this.fkMaquina = fkMaquina;
    }
}
