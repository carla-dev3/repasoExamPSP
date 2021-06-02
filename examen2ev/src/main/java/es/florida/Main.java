package es.florida;

import org.jasypt.util.text.StrongTextEncryptor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Main {

    private static Socket socket;
    private static final String ENCRYPT_COMMAND = "3456";

    public static void main(String[] args) throws IOException, InterruptedException {
        DronController dronController = new DronController();
        socket = dronController.connect(); //Conectar socket al dron
        dronController.takeOff(); //
        dronController.land();
        dronController.firePrimaryCannon();
        dronController.fireSecondaryWeapon();
        dronController.shutDown();
        socket.close(); //Cerrar socket
    }

    public static void sendCommand(String command, boolean wait, boolean encrypt) throws IOException, InterruptedException {
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printer = new PrintWriter(new OutputStreamWriter(outputStream));
        if (encrypt) {
            command = encryptCommand(command);
        }
        printer.println(command);
        printer.flush();
        if (wait) {
            Thread.sleep(1000); //Esperar 1 segundo
        }
    }

    private static String encryptCommand(String key) {
        StrongTextEncryptor commandEncryptor = new StrongTextEncryptor();
        commandEncryptor.setPassword(ENCRYPT_COMMAND);
        return commandEncryptor.encrypt(key);
    }
}
