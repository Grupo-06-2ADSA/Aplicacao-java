import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        System.out.println("""
                **      **  **  **   **  *******         ********  **********  *********    *********
                ** **** **  **  ***  **  **     **      **         **      **  **      **   **
                **  **  **  **  ** * **  **      **    **          **      **  *********    *****
                **      **  **  **  ***  **     **      **         **      **  **   **      **
                **      **  **  **   **  *******         ********  **********  **     **    *********
                
                """);

        AutenticacaoUsuario autenticacaoUsuario = new AutenticacaoUsuario();

        List<String> listaDadosUsuario = new ArrayList<>();

        listaDadosUsuario.add("andre.silva@sptech.school");
        listaDadosUsuario.add("123456");

        listaDadosUsuario.add("sofia.machado@sptech.school");
        listaDadosUsuario.add("654321");

        autenticacaoUsuario.FazerLogin(listaDadosUsuario);

    }
}
