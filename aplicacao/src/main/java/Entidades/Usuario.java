package Entidades;

import Main.Slack;
import Models.UsuarioDAO;
import Main.App;
import com.github.britooo.looca.api.core.Looca;
import org.json.JSONObject;


public class Usuario {
    private String email;
    private String senha;

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public static void FazerLogin() {
        try {
            String email = "luan@gmail.com";
            String senha = "654321";

            System.out.println("+----------------------------------------------+");
            System.out.println("|            Entrando na sua conta             |");
            System.out.println("| Usuario: %s".formatted(email));
            System.out.println("+----------------------------------------------+");

            Usuario usuario = new Usuario(email, senha);

            boolean usuarioExiste = UsuarioDAO.verificarUsuario(usuario);

            if (usuarioExiste) {
                App.CapturarDados();
//                Looca looca = new Looca();
//
//                JSONObject json = new JSONObject();
//                json.put("text", "O usuário " + usuario.getEmail() + " Realizou login na máquina: " + looca.getRede().getParametros().getHostName());
//                Slack.sendMessage(json);
            } else {
                System.out.println("Usuário não encontrado...");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Erro ao verificar usuário: Índice fora dos limites - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro ao verificar usuário: " + e.getMessage());
        }
    }


    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
