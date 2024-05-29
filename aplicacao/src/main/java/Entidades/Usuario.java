package Entidades;

import Main.Slack;
import Models.UsuarioDAO;
import Main.App;
import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.rede.Rede;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Scanner;

public class Usuario {
    private static String email;
    private String senha;

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public static void FazerLogin() throws IOException, InterruptedException {
        Scanner leitor = new Scanner(System.in);

        System.out.println("+-------------------------------------------+");
        System.out.println("|            Entre na sua conta              ");
        System.out.println("|   Pressione a tecla [ENTER] para começar   ");

        if (leitor.hasNextLine()) {
            System.out.print("| Email: ");
            String email = leitor.next();

            System.out.print("| Senha: ");
            String senha = leitor.next();

            System.out.println("+-------------------------------------------+");

            Usuario usuario = new Usuario(email, senha);

            boolean usuarioExiste = UsuarioDAO.verificarUsuario(usuario);

            if (usuarioExiste) {
                App.CapturarDados();
//                    Looca looca = new Looca();
//                    Rede rede = looca.getRede();
//
//                    JSONObject json = new JSONObject();
//                    json.put("text", "O usuário " + Usuario.getEmail() + " Realizou login na máquina: " + rede.getParametros().getHostName());
//                    Slack.sendMessage(json);
            } else {
                System.out.println("Dados incorretos, tente novamente.");
                FazerLogin(); // Chama o método fazerLogin novamente após uma tentativa malsucedida
            }
        } else {
            System.out.println("Leitor não disponível.");
        }

    }

    public static String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
