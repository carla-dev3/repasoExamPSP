package es.florida;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MemberCreator implements Runnable {

    private int member = 0;
    
    public void run() { //encargado de ir agregando miembros para simular que la base de datos va creciendo
        File f = new File("members.txt");
        f.delete();
        while(true) {
            if(Lock.blocked) {
                addMember();
            }
            try {
                Thread.sleep(4000); //Se encargará de, cada N segundos (donde N debe ser
               // mayor que 3, ya que es lo que tarda en monitorizar el MemberMonitor) agregar una
               // nueva dirección de correo.
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void addMember(){
        File file = new File("members.txt");
        try {
            FileWriter writer = new FileWriter(file,true);
            PrintWriter print = new PrintWriter(writer);
            print.println(member + "@gmail.com");
            member++;
            print.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
