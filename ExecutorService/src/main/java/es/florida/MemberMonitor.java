package es.florida;

import org.apache.commons.mail.EmailException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//Es la persona que
public class MemberMonitor implements Runnable {
    /**
     * Funcion para leer un fichero como String y devolverlo como un Array
     * @return ArrayList
     * @throws java.io.IOException
     */
    public ArrayList<String> readUsers() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("Users.txt"));
        ArrayList<String> users = new ArrayList<>();
        String usu;
        while ((usu = br.readLine()) != null) {
            users.add(usu);
        }
        br.close();
        return users;
    }


    @Override
    public void run() {
        ArrayList<String> user1;
        ArrayList<String> user2;
        MailSender sender = new MailSender();
        MemberCreator creator = new MemberCreator();
        Thread threadCreator = new Thread(creator);
        threadCreator.start();
        try {
            creator.asegurarFichero();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                user1 = readUsers();
                Thread.sleep(3000);
                System.out.println("Buscado nuevos usuarios...");
                //Añadimos los String del fichero a la primera array
                //Esperamos 3 seg para añadir otra vez los String a la seguna array
                user2 = readUsers();
                //Comparamos la primera array con la segunda, en caso de ser distintas llamaremos a la clase de MailSender, en caso de ser iguales no pasara nada
                if (!user1.equals(user2)) {
                    System.out.println(" ");
                    System.out.println("----------------------------------");
                    System.out.println("Se ha encontrado un nuevo usuario");
                    threadCreator.suspend();
                    String newUser = (user2.get(user2.size() - 1));
                    System.out.println("===================================================");
                    for (String elem : user1) {
                        sender.sendEmail(elem, newUser);
                    }
                    System.out.println("===================================================");
                    threadCreator.resume();
                    Thread.sleep(500);
                }
            } catch (IOException | InterruptedException | EmailException e) {
                e.printStackTrace();
            }
        }
    }
}

