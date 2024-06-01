package Entidades;

public class Computador {
    private static String hostName;
    private String ipv4;

    public Computador(String hostName, String ipv4) {
        this.hostName = hostName;
        this.ipv4 = ipv4;
    }

    public String getHostName() {
        return hostName;
    }

    public String getIpv4() {
        return ipv4;
    }
}
