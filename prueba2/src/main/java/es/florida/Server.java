package es.florida;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static final int PORT = 6789;
    private static final int MAX_CLIENTS = 2;

    public static void main(String[] args) throws IOException {

        ExecutorService executorService = Executors.newFixedThreadPool(MAX_CLIENTS);
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket clientConnection;

        while (true) {
            System.out.println("Escuchando conexiones de clientes...");
            clientConnection = serverSocket.accept();
            System.out.println("Cliente conectado en el puerto " + clientConnection.getPort());
            executorService.execute(new Client(clientConnection));
        }
    }
}
