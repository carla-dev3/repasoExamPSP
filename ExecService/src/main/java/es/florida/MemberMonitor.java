package es.florida;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MemberMonitor implements Runnable {

    public void run() {
        comprobarCorreos();
    }

    private void comprobarCorreos() {
        String line = "";
        String line2 = "";
        File file = new File("members.txt");
        while(true) {

            if (file.isFile()) { // comprobará, cada 3 segundos, si existe alguna dirección de correo electrónico nueva
                try {
                    Thread.sleep(3000);
                    FileReader reader = new FileReader(file);
                    BufferedReader bReader = new BufferedReader(reader);
                    String aux;
                    while ((aux = bReader.readLine()) != null) {
                        line = aux;
                    }
                    if (!(line.equals(line2))) { // lanzará un subproceso llamado “MailSender”
                        line2 = line;
                        MailSender.sendMessages(line); //simulará enviar un correo a cada una de
                        /// las direcciones informando sobre el nuevo miembro
                    }
                    bReader.close();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
