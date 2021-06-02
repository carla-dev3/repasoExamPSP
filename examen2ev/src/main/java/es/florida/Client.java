package es.florida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client implements Runnable{

    final Socket connection;

    public Client (Socket clientConnection) {this.connection = clientConnection; }

    @Override
    public void run() {
        try {
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String command = reader.readLine();
            executeCommands(command);
            disconnectClient();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void disconnectClient() throws IOException { //Desconectar el cliente
        this.connection.close();
        System.out.println("Disconnected");
    }

    private void executeCommands(String line) { //Ejecutar comandos
        if (line.equals ("ENEMY_SPOTTED")) {
            System.out.println("Received ENEMY_SPOTTED ");
        } else {
            System.out.println("Command " + line + " not recognized ");
        }
    }
}
