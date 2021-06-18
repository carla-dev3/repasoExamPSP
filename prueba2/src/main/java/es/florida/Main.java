package es.florida;

import org.jasypt.util.text.StrongTextEncryptor;

import java.io.*;
import java.net.Socket;

public class Main {

    private static final String ENCRYPT_PASS = "1234";
    private static Socket socket;

    public static void main(String[] args) throws IOException, InterruptedException {

    DronController dronController = new DronController();
    socket = dronController.connect();
    dronController.takeOff();
    dronController.land();
    dronController.firePrimaryCannon();
    dronController.fireSecondaryWeapon();
    dronController.shutDown();
    socket.close();
    }

    public static void sendCommands(String command, boolean hasToWait, boolean hasToEncrypt) throws IOException, InterruptedException {
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter printer = new PrintWriter(new OutputStreamWriter(outputStream));
        if(hasToEncrypt)
            command = encryptCommand(command);

        printer.println(command);
        printer.flush();
        if(hasToWait)
            Thread.sleep(1000);
    }

    private static String encryptCommand(String key) {
        StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
        textEncryptor.setPassword(ENCRYPT_PASS);
        return textEncryptor.encrypt(key);
    }
}
