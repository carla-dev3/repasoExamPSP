package es.florida;

public class Lock {
    public static boolean blocked = true;

    public static void unlock(){
        blocked = true;
    }

    public static void lock(){
        blocked = false;
    }
}
