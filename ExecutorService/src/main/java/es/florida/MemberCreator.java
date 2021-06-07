package es.florida;

import java.io.*;

//Es la persona que se encarga de crear los correos
public class MemberCreator implements Runnable{

    @Override
    public void run() {
        try {
            CrearFicheroNumeros();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void asegurarFichero() throws IOException {
        File file = new File("Users.txt");
        if (! file.exists()) {
            boolean newFile = file.createNewFile();
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write("");
        bw.close();
    }

    /**
     * Creamos un fichero con usuarios
     * @throws IOException
     */
    public void  CrearFicheroNumeros() throws IOException {
        int i = 1;
        String user = "";

        File file = new File("User.txt");
        BufferedWriter bw;
        bw = new BufferedWriter(new FileWriter(file));

        if (file.exists()) {
            PrintWriter writer = new PrintWriter(bw);
            while (true) {
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Insertamos en un fichero de texto a los usuarios, pero incrementandolo
                user = "usu " + i + "@gmail.com";
                i++;
                writer.println(user);
                bw.flush();
                System.out.println("Added user:  " + user);
            }
        }
    }
}
