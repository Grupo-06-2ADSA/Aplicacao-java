package Entidades;

import Models.UsuarioDAO;
import Main.App;

import java.util.Scanner;

public class Usuario {
    private String email;
    private String senha;

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public static void FazerLogin(){
        Scanner leitor = new Scanner(System.in);

        String emailOficial = "leo@gmail.com";
        String senhaOficial = "123456";

        System.out.println();
        System.out.println("******** Entre na sua conta ********");
        System.out.print("Email: ");
        String email = leitor.next();

        System.out.print("Senha: ");
        String senha = leitor.next();

        Usuario usuario = new Usuario(email, senha);

        //Boolean usuarioExiste = UsuarioDAO.verificarUsuario(usuario);

        if (email.equals(emailOficial) && senha.equals(senhaOficial)){
            System.out.println(" ");
            System.out.println("******** Iniciando Captura Dos Dados ********");
            App.CapturarDados();
        } else {
            System.out.println("Dados incorretos, tente novamente.");
            FazerLogin();
        }
    }

    public String getEmail() {
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
