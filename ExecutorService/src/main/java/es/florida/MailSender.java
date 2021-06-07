package es.florida;


import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//Es la persona que se encarga de enviar los correos (va acorde con el MemberMonitor)
public class MailSender {

    public static void sendEmail(String user) {
        try {
            Lock.lock();
            FileReader reader = new FileReader("users.txt");
            BufferedReader bReader = new BufferedReader(reader);
            String aux;

            while(!(aux = bReader.readLine()).equals(user)) {
                Thread.sleep(1000);
                System.out.println("Correo enviado a " + aux+ "\n" + user + " es un nuevo integrante");

                SimpleEmail email = new SimpleEmail();
                email.setHostName("localhost");
                email.setSmtpPort(1025);
                email.setSslSmtpPort("1025");
                email.setAuthentication("", ""); //Cuenta de gmail y contraseña
                email.setSSLOnConnect(false);
                email.addTo(aux); //Cuenta de destino
                email.setFrom("ejemplo@gmail.com"); //Cuenta de origen
                email.setSubject("New user added");
                email.setMsg("Added user " + user);
                email.send();
            }
            bReader.close();
            Lock.unlock();

        } catch (InterruptedException | IOException | EmailException e) {
            e.printStackTrace();
        }
    }
}
