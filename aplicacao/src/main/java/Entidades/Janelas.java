package Entidades;

public class Janelas {
    private Long id;
    private String titulo;
    private Long pid;
    private Integer total;

    public Janelas(Long id, String titulo, Long pid, Integer total) {
        this.id = id;
        this.titulo = titulo;
        this.pid = pid;
        this.total = total;
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
}