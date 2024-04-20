package Entidades;

public class Computador {
    private String hostName;
    private String ipv4;

    public Computador(String hostName, String ipv4) {
        this.hostName = hostName;
        this.ipv4 = ipv4;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIpv4() {
        return ipv4;
    }

    public void setIpv4(String ipv4) {
        this.ipv4 = ipv4;
    }
}
