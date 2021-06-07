package es.florida;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Es quien tiene todos los lanzadores
public class Main {

    public static void main(String[] args) {
        System.out.println("App started");

        MemberCreator memberCreator = new MemberCreator();
        MemberMonitor memberMonitor = new MemberMonitor();

        Thread creatorThread = new Thread(memberCreator);
        Thread monitorThread = new Thread(memberMonitor);
        creatorThread.start();
        monitorThread.start();
    }


}
