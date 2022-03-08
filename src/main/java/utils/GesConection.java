/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author adryc
 */
public class GesConection {

    private String server;
    private Integer port;

    private Object object;
    private Socket socket = null;

    private ObjectInputStream inputStream = null;
    private ObjectOutputStream outputStream = null;

    public GesConection(String server, Integer port) {
        this.server = server;
        this.port = port;
    }

    //Envía un objeto al servidor
    public void sendObject(Object object) {
        try {
            this.socket = new Socket(server, port);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(object);
            outputStream.flush();
        } catch (IOException e) {
            System.err.println("Error al enviar objeto: " + e);
        }
    }

    //Recibe un objeto desde el servidor
    public Object getObject() {
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            object = inputStream.readObject();
        } catch (IOException e) {
            System.err.println("Error al recibir objeto: " + e);
        } catch (ClassNotFoundException e) {
            System.err.println("Error al recibir objeto: " + e);
        }
        return object;
    }
}
