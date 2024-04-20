package Entidades;

public class Disco {
    private Double tamanho;
    private Double leituras;
    private Double bytesLeitura;
    private Double escritas;
    private Double bytesEscrita;
    private Long tempoTranferencia;

    public Disco(Double tamanho, Double leituras, Double bytesLeitura, Double escritas, Double bytesEscrita, Long tempoTranferencia) {
        this.tamanho = tamanho;
        this.leituras = leituras;
        this.bytesLeitura = bytesLeitura;
        this.escritas = escritas;
        this.bytesEscrita = bytesEscrita;
        this.tempoTranferencia = tempoTranferencia;
    }

    public Double getTamanho() {
        return tamanho;
    }

    public Double getLeituras() {
        return leituras;
    }

    public Double getBytesLeitura() {
        return bytesLeitura;
    }

    public Double getEscritas() {
        return escritas;
    }

    public Double getBytesEscrita() {
        return bytesEscrita;
    }

    public Long getTempoTranferencia() {
        return tempoTranferencia;
    }
}
