package Entidades;

import Models.UsuarioDAO;
import Main.App;

import java.util.Scanner;

public class Usuario {
    private static String email;
    private String senha;

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public static void FazerLogin(){
        Scanner leitor = new Scanner(System.in);

        System.out.println("+-------------------------------------------+");
        System.out.println("|            Entre na sua conta              ");
        System.out.println("|   Pressione a tecla [ENTER] para começar   ");

        if (leitor.hasNextLine()) {
            System.out.print("| Email: ");
            String email = leitor.next();

            // Verifica se há mais entradas disponíveis antes de solicitar a senha
            if (leitor.hasNextLine()) {
                System.out.print("| Senha: ");
                String senha = leitor.next();
                System.out.println("+-------------------------------------------+");

                Usuario usuario = new Usuario(email, senha);

                boolean usuarioExiste = UsuarioDAO.verificarUsuario(usuario);

                if (usuarioExiste) {
                    App.CapturarDados();
                } else {
                    System.out.println("Dados incorretos, tente novamente.");
                    FazerLogin(); // Chama o método fazerLogin novamente após uma tentativa malsucedida
                }
            } else {
                System.out.println("Entrada de senha não fornecida.");
            }
        } else {
            System.out.println("Entrada de email não fornecida.");
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
