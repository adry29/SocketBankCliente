/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package views;

import java.util.List;
import java.util.Scanner;
import model.Cuenta;
import model.Data;
import model.Usuario;
import utils.GesConection;

/**
 *
 * @author adryc
 */
public class AdminView {
    
    Usuario u;
    private final GesConection con = new GesConection("127.0.0.1", 9640);
    
    public AdminView(Usuario u){
        this.u = u;
    }
    
    public Data<String> selectDueño() {

        String name = "";
        Data<String> result = new Data<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Escribe el nombre de usuario del dueño de la cuenta:");

        name = sc.nextLine();

        result.setObject(name);
        result.setOpt(8);
        return result;
    }
    
    public Data<Long> selectCuenta() {

        Long id = 0L;
        boolean valid = false;
        Data<Long> result = new Data<>();
        Scanner sc = new Scanner(System.in);

        

        while(!valid){
            System.out.println("Escribe el id de la cuenta:");
            try{
            id = sc.nextLong();
        }catch(Exception e){
            System.out.println("Datos mal introducidos");
        }
            if(id>0){
                valid = true;
            }
        }

        result.setObject(id);
        result.setOpt(7);
        return result;
    }
    
    public Data<Usuario> createUsuario(){
        Data<Usuario> result = new Data<Usuario>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Escribe el nombre de usuario: ");
        String nick = sc.nextLine();
        System.out.println("Escribe la contraseña");
        String password = sc.nextLine();
        Usuario data = new Usuario(nick, password);
        result.setObject(data);
        result.setOpt(1);
        return result;
    }
        public void see() {
        
        
        int opt = 0;

        while (opt != 5) {

            System.out.println("Bienvenido a SocketBank. Por favor seleccione una opción:");
            System.out.println("1: Ver todos los usuarios");
            System.out.println("2: Crear usuario");
            System.out.println("3: Crear cuenta");
            System.out.println("4: Borrar cuenta");
            System.out.println("5: Salir");

            Scanner sc = new Scanner(System.in);
            try {
                opt = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Valor no válido");
            }
            
            switch (opt) {
                case 1:
                    Data <List<Usuario>> getteddata = new Data();
                    Data<Usuario> data = new Data<Usuario>();
                    data.setObject(u);
                    data.setOpt(6);
                    con.sendObject(data);
                    getteddata = (Data<List<Usuario>>) con.getObject();
                    if (getteddata.getResult()) {
                        for(Usuario u : getteddata.getObject()){
                            System.out.println(u.toString());
                        }
                    }
                    break;

                case 2:
                    Data<Usuario> datausuario = createUsuario();
                    con.sendObject(datausuario);
                    
                    datausuario=(Data<Usuario>) con.getObject();
                    System.out.println("Usuario creado correctamente: " + datausuario.getObject().toString());
                    break;
                    
                case 3:
                    Data<String> dataname = selectDueño();
                    con.sendObject(dataname);
                    
                    Data<Cuenta> datacuenta=(Data<Cuenta>) con.getObject();
                    System.out.println(datacuenta.getObject().toString());
                    if(datacuenta.getObject()!= null){
                        System.out.println("Cuenta creada correctamente: " + datacuenta.getObject().toString());
                    }else{
                        System.out.println("Datos mal introducidos, abortando operación");
                    }
                    
                    
                    break;
                
                
                case 4:
                    Data<Long> todelete = selectCuenta();
                    con.sendObject(todelete);
                    
                    if(con.getObject()!=null){
                        System.out.println("Cuenta eliminada correctamente");
                    }else{
                        System.out.println("No se puedo eliminar la cuenta. Abortando operación");
                    }
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        }
    }   
    
}
