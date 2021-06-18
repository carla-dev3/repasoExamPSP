package es.florida;


public class App {
    public static void main(String[] args) throws InterruptedException {
        MemberCreator m = new MemberCreator(); //Member Creator
        MemberMonitor m2 = new MemberMonitor(); //Member Monitor
        Thread monitorThread = new Thread(m2);
        Thread creatorThread = new Thread(m);
        creatorThread.start();
        monitorThread.start();
        creatorThread.start();
        creatorThread.join();
        monitorThread.join();
    }

}
