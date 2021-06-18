package es.florida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client implements Runnable {
    final Socket connection;
    public Client(Socket clientConnection) { this.connection = clientConnection; }

    public void run() {
    try {
        InputStream input = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String command = reader.readLine();
        executeCommand(command);
        disconnectClient();

    } catch (IOException e){
        e.printStackTrace();
    }
    }

    private void executeCommand(String line) {
        if(line.equals("ENEMY_SPOTTED")) {
            System.out.println("Received command");
        } else {
            System.out.println("Error. " + line + "not recognized");
        }
    }

    private void disconnectClient() throws IOException {
        this.connection.close();
        System.out.println("Client disconnected");
    }
}
