package es.florida;

import java.io.IOException;
import java.net.Socket;

public class DronController {
    static final String IP = "127.0.0.1";
    static final int PORT = 9876; //Escuchar por el puesto: 9876

    public Socket connect() throws IOException {
        return new Socket(IP, PORT);
    }

    public void takeOff() throws IOException, InterruptedException {
        Main.sendCommand("TAKE-OFF", true, false);
        System.out.println("Taking off...");
    }

    public void land() throws IOException, InterruptedException {
        Main.sendCommand("LAND", true, false);
        System.out.println("Landing");
    }

    public void firePrimaryCannon() throws IOException, InterruptedException {
        Main.sendCommand("FIRE_P_W", true, false);
        System.out.println("Ratatatatatatata!");
    }

    public void fireSecondaryWeapon() throws IOException, InterruptedException {
        Main.sendCommand("FIRE_S_W", true, false);
        System.out.println("Piñau! Piñau!");
    }

    public void shutDown() throws IOException, InterruptedException {
        Main.sendCommand("OFF", true, false);
        System.out.println("Shutting down system...");
    }

}
