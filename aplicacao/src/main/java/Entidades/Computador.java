package Entidades;

public class Computador {
    private String hostName;
    private String ipv4;
    private Integer fkSala;
    private String fkEmpresa;

    public Computador(String hostName, String ipv4, String fkEmpresa) {
        this.hostName = hostName;
        this.ipv4 = ipv4;
        fkSala = 6;
        this.fkEmpresa = fkEmpresa;
    }

    public String getHostName() {
        return hostName;
    }

    public String getIpv4() {
        return ipv4;
    }

    public Integer getFkSala() {
        return fkSala;
    }

    public String getFkEmpresa() {
        return fkEmpresa;
    }
}
