package es.florida;

import java.io.*;

//Es la persona que se encarga de crear los correos
public class MemberCreator implements Runnable{

    private int user = 0;

    @Override
    public void run() {
       File file = new File("Users.txt");
       file.delete();
       while (true) {
           if(Lock.blocked) {
               addUser();
           }
           try {
               Thread.sleep(4000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }

    private void addUser() {
        File file = new File("Users.txt");
        try {
            FileWriter writer = new FileWriter(file, true);
            PrintWriter printer = new PrintWriter(writer);
            printer.println(user + "@gmail.com");
            user++;
            printer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
