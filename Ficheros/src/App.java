import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Vamos a crear, escribir y leer un fichero
public class App {
    public static void main(String[] args) throws IOException {
        String filename = "fichero.txt";
        writeFile(filename);
        List<String> fileStrings = readFile(filename);
        for (String line : fileStrings) {
            System.out.println(line);
        }
    }

    //Método para poder leer el fichero (2º)
    private static List<String> readFile(String filename) throws IOException {
        List<String> result = new ArrayList<>();
        FileReader reader = new FileReader(new File(filename)); //Leer el fichero
        BufferedReader bReader = new BufferedReader(reader);
        String line;
        while ((line = bReader.readLine()) != null) { //Meter en esa linea todo lo que no sea null
            result.add(line); //Devolvemos el resultado
        }
        bReader.close();
        return result;
    }

    //Método para poder escribir en el fichero (1º)
    private static void writeFile(String pathname) throws IOException {
        File file = new File(pathname); //Creamos un fichero
        FileWriter writer = new FileWriter(file); //Pasamos el fichero para poder escribir en él
        PrintWriter printer = new PrintWriter(writer); //Decorador: escribimos en el fichero
        //  printer.println("Esto es una prueba de escritura"); //Información dentro del fichero

        long startTime = System.nanoTime();
        Random rand = new Random();
        int num = 0;
        for (int i = 0; i < 100; i++) { //Escribir X líneas consecutivas
            num = (int) (rand.nextFloat() * 100); //Números aleatorios
            printer.println(num);
            printer.flush();
        }
        //Duración de tiempo del proceso de escritura
        long stopTime = System.nanoTime();
        printer.println("Tiempo usado en la creacion de los numeros = "+ (stopTime-startTime)+ " ms");
        printer.close();
    }
}
