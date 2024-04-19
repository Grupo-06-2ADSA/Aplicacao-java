import java.util.List;
import java.util.Scanner;

public class AutenticacaoUsuario {
    Scanner leitor = new Scanner(System.in);

    // Realiza a verificação na lista de dados
    void RealizarLogin(String email, String senha, List<String> listaDadosUsuario){
        boolean hasCadastro = false;

        for (int i = 0; i < listaDadosUsuario.size(); i++){
            if (listaDadosUsuario.get(i).equals(email) && listaDadosUsuario.get(i + 4).equals(senha)) {
                hasCadastro = true;
                break;
            }
        }

        if (hasCadastro){
            System.out.println("Acesso liberado");
        } else {
            System.out.println("Acesso negado");
        }
    }


    // Monta a estrutura do login
    void FazerLogin(List<String> listaDadosUsuario){
        System.out.println();
        System.out.println("****** Entre na sua conta ******");
        System.out.print("Email: ");
        String email = leitor.next();

        System.out.print("Senha: ");
        String senha = leitor.next();

        RealizarLogin(email, senha, listaDadosUsuario);
    }

}
