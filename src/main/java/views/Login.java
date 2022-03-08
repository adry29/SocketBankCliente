package views;

import java.util.Scanner;
import model.Cuenta;
import model.Data;
import model.Usuario;
import utils.GesConection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author adryc
 */
public class Login {
    
    private final GesConection con = new GesConection("127.0.0.1", 9640);
    
    public Usuario login(){
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        String password = sc.nextLine();
        Usuario result = new Usuario(name, password);
        return result;
    }
    
    public Usuario register(){
        System.out.println("Creando nuevo usuario: Introduce nombre de usuario y contraseña");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        String password = sc.nextLine();
        Usuario result = new Usuario(name, password);
        return result;
    }
    
    public void see() {
        boolean valid = false;
        Data<Usuario> usuario = new Data<>();
        while (!valid) {
            System.out.println("Inicio de sesión: Introduce nombre de usuario y contraseña");
            //Creamos un usuario desde el método login
            usuario.setObject(login());
            //lo enviamos al servidor junto con la opción de crear usuario
            usuario.setOpt(2);
            con.sendObject(usuario);
            usuario = (Data<Usuario>) con.getObject();
            if (usuario.getObject()!= null) {
                if (usuario.getObject().isAdmin()) {
                    System.out.println(usuario.getObject().toString());
                    new AdminView(usuario.getObject()).see();
                    valid=true;
                } else {
                    System.out.println(usuario.getObject().toString());
                    
                    new ClientView(usuario.getObject()).see();
                    
                    valid=true;
                }

            } else {
                System.out.println("Datos erróneos");
            }
        }
    }
    
    
}
