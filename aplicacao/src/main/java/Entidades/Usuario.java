package Entidades;

import Main.Slack;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import org.json.JSONObject;

import java.io.IOException;

public class Usuario {
    private String email;
    private String fkEmpresa;

    public Usuario(String email, String fkEmpresa) {
        this.email = email;
        this.fkEmpresa = fkEmpresa;
    }

    public void enviarMensagemSlack() throws IOException, InterruptedException {
        Looca looca = new Looca();
        Rede rede = looca.getRede();

        JSONObject json = new JSONObject();
        json.put("text", "O usuário " + email + " Realizou login na máquina: " + rede.getParametros().getHostName());
        Slack.sendMessage(json);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(String fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }
}