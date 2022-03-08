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
public class ClientView {

    private Usuario u;
    private final GesConection con = new GesConection("127.0.0.1", 9640);

    public ClientView(Usuario u) {
        this.u = u;
    }

    public int changeSaldo() {
        int value = 0;
        System.out.println("Seleccione el importe a ingresar: ");
        while (value < 1) {
            try {
                Scanner sc = new Scanner(System.in);
                value = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Valor no válido" + e);
            }
        }
        return value;
    }

    public void see() {
        
        
        int opt = 0;

        while (opt != 5) {

            System.out.println("Bienvenido a SocketBank. Por favor seleccione una opción:");
            System.out.println("1: Ver mis cuentas");
            System.out.println("2: Añadir saldo");
            System.out.println("3: Retirar saldo");
            System.out.println("4: Ver mi historial de transacciones");
            System.out.println("5: Salir");

            Scanner sc = new Scanner(System.in);
            try {
                opt = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Valor no válido");
            }
            
            switch (opt) {
                case 1:
                    Data <List<Cuenta>> getteddata = new Data();
                    Data<Usuario> data = new Data<Usuario>();
                    data.setObject(u);
                    data.setOpt(3);
                    con.sendObject(data);
                    getteddata = (Data<List<Cuenta>>) con.getObject();
                    if (getteddata.getResult()) {
                        for(Cuenta c : getteddata.getObject()){
                            System.out.println(c.toString());
                        }
                    }
                    break;

                case 2:
                    Data<Cuenta> datacuenta = new Data<>();
                    Cuenta c = new Cuenta(u);
                    System.out.println("Introduzca numero relativo de la cuenta a cambiar: ");
                    try{
                        c.setId(sc.nextLong());
                        datacuenta.setObject(c);
                        datacuenta.setOpt(4);
                        datacuenta.setChange(changeSaldo());
                        con.sendObject(datacuenta);
                        
                    }catch(Exception ex){
                        System.out.println("Valor no válido, abortando ingreso");
                    }
                    datacuenta=(Data<Cuenta>) con.getObject();
                    System.out.println(datacuenta.getObject().toString());
                    break;
                    
                case 3:
                    Data<Cuenta> datacuenta2 = new Data<>();
                    Cuenta c2 = new Cuenta(u);
                    System.out.println("Introduzca el id de la cuenta a cambiar: ");
                    try{
                        c2.setId(sc.nextLong());
                        datacuenta2.setObject(c2);
                        datacuenta2.setOpt(5);
                        datacuenta2.setChange(changeSaldo());
                        con.sendObject(datacuenta2);
                        
                    }catch(Exception ex){
                        System.out.println("Valor no válido, abortando extracción");
                    }
                    datacuenta=(Data<Cuenta>) con.getObject();
                    System.out.println(datacuenta2.getObject().toString());
                    
                    if(datacuenta.getObject().getSaldo() != datacuenta2.getObject().getSaldo()){
                        System.out.println("Has tratado de retirar más dinero del disponible. La transacción ha sido cancelada.");
                    }else{
                        System.out.println("Transacción realizada con éxito, dinero retirado: " + datacuenta.getChange() + ", saldo actual:" + datacuenta.getObject().getSaldo());
                    }
                    break;
                
                
                case 5:
                    System.out.println("Gracias por su visita, vuelva pronto.");
                    break;

                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
