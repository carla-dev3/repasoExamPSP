package es.florida;

import org.apache.commons.mail.EmailException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//Es la persona que
public class MemberMonitor implements Runnable {


    @Override
    public void run() {
        checkUsers();
    }

    private void checkUsers() {
        String line = "";
        String line2 = "";
        File file = new File("users.txt");

        while (true) {

            if (file.isFile()) {
                try {
                    Thread.sleep(3000);
                    FileReader reader = new FileReader(file);
                    BufferedReader bReader = new BufferedReader(reader);
                    String aux;
                    while ((aux = bReader.readLine()) != null) {
                        line = aux;
                    }
                    if (!(line.equals(line2))) {
                        line2 = line;
                        MailSender.sendEmail(line);
                    }
                    bReader.close();
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

