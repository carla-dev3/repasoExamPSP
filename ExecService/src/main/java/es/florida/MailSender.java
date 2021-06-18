package es.florida;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MailSender {

    public static void sendMessages(String nombre){
        try {
            Lock.lock();
            //rá enviar un correo a cada una de
            //las direcciones informando sobre el nuevo miembro.
            FileReader reader = new FileReader("members.txt");
            BufferedReader bReader = new BufferedReader(reader);
            String aux;
            while(!(aux = bReader.readLine()).equals(nombre)){
                Thread.sleep(1000);
                System.out.println("Correo enviado a " + aux + "\n"+nombre+" es un nuevo integrante");
            }
            bReader.close();
            Lock.unlock();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}
