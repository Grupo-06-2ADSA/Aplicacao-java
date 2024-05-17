package Entidades;

public class Rede {
    private String nome;
    private Long bytesRecebidos;
    private Long bytesEnviados;
    private Long pacotesRecebidos;
    private Long pacotesEnviados;

    public Rede(String nome, Long bytesRecebidos, Long bytesEnviados, Long pacotesRecebidos, Long pacotesEnviados) {
        this.nome = nome;
        this.bytesRecebidos = bytesRecebidos;
        this.bytesEnviados = bytesEnviados;
        this.pacotesRecebidos = pacotesRecebidos;
        this.pacotesEnviados = pacotesEnviados;
    }

    public String getNome() {
        return nome;
    }

    public Long getBytesRecebidos() {
        return bytesRecebidos;
    }

    public Long getBytesEnviados() {
        return bytesEnviados;
    }

    public Long getPacotesRecebidos() {
        return pacotesRecebidos;
    }

    public Long getPacotesEnviados() {
        return pacotesEnviados;
    }
}
