package es.florida;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Es quien tiene todos los lanzadores
public class Main {

    public static void main(String[] args) {
        System.out.println("App started");

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(new MemberMonitor());
    }


}
