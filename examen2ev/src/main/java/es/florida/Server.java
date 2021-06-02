package es.florida;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static int PORT = 6789; //Puerto por el que escucha el servidor
    private static int clients = 2; //máximo de drones

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(clients);
        ServerSocket server = new ServerSocket(PORT);
        Socket clientConnection;
        while (true) {
            System.out.println(" Listening connections from clients ...");
            clientConnection = server.accept();
            System.out.println("Client connected on port: " + clientConnection.getPort());
            executorService.execute(new Client(clientConnection));
        }
    }
}
