package es.florida;


import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

//Es la persona que se encarga de enviar los correos (va acorde con el MemberMonitor)
public class MailSender {

    synchronized public void sendEmail(String user, String newUser) throws InterruptedException, EmailException {
        System.out.println("Sending email ");

        SimpleEmail email = new SimpleEmail();

        //Configuracion necesaria para enviar correos
        email.setHostName("localhost");
        email.setSmtpPort(1025);
        email.setSslSmtpPort("1025");
        email.setAuthentication("", ""); //Cuenta de gmail y contraseña
        email.addTo("carlaaparicio0905@gmail.com"); //Cuenta de destino
        email.setFrom(user); //Cuenta de origen
        email.setSubject("New user added");
        email.setMsg("Added user " + newUser );
        email.send();

        System.out.println("Informed to user " + user + " that there is an other user added");
        Thread.sleep(1000);
    }
}
