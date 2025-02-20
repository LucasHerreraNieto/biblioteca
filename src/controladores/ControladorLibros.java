package controladores;

import entities.Libro;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ControladorLibros {

    private static final String NOMBRE_ARCHIVO = "libros.txt";
    private static final String SEPARADOR_CAMPO = ";";
    private static final String SEPARADOR_REGISTRO = "\n";

    public static void solicitarDatosParaRegistrar(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el codigo del libro:");
        String codigo = sc.nextLine();
        System.out.println("Ingrese el titulo del libro:");
        String titulo = sc.nextLine();
        System.out.println("Ingrese el autor del libro:");
        String autor = sc.nextLine();
        System.out.println("El libro esta disponible? [true/false]");
        boolean disponible = Boolean.valueOf(sc.nextLine());
        System.out.println("Ingrese la localizacion del libro:");
        String localizacion = sc.nextLine();
        System.out.println("Ingrese la signatura del libro:");
        String signatura = sc.nextLine();
        ControladorLibros.registrar(new Libro(codigo, titulo, autor, localizacion, signatura, disponible));
        System.out.println("Libro registrado correctamente");
    }

    public static void registrar(Libro unLibro){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO,true));
            bufferedWriter.write(unLibro.getCodigo()
                    + SEPARADOR_CAMPO + unLibro.getTitulo()
                    + SEPARADOR_CAMPO + unLibro.getAutor()
                    + SEPARADOR_CAMPO + String.valueOf(unLibro.isDisponible())
                    + SEPARADOR_CAMPO + unLibro.getLocalizacion()
                    + SEPARADOR_CAMPO + unLibro.getSignatura() + SEPARADOR_REGISTRO);
            bufferedWriter.close();
        }catch (IOException e){
            System.out.println("Error escribiendo en archivo: "+ e.getMessage());
        }
    }

    public static void guardarLibros(ArrayList<Libro> libros){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO,false));
           for (int x = 0; x < libros.size();x++){
               Libro libro = libros.get(x);
               bufferedWriter.write(libro.getCodigo()
                       + SEPARADOR_CAMPO + libro.getTitulo()
                       + SEPARADOR_CAMPO + libro.getAutor()
                       + SEPARADOR_CAMPO + String.valueOf(libro.isDisponible())
                       + SEPARADOR_CAMPO + libro.getLocalizacion()
                       + SEPARADOR_CAMPO + libro.getSignatura() + SEPARADOR_REGISTRO);
           }
           bufferedWriter.close();
        }catch (IOException e){
            System.out.println("Error escribiendo en archivo: " + e.getMessage());
        }
    }

    public static ArrayList<Libro> obtener(){
        ArrayList<Libro> libros = new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try{
            fileReader = new FileReader(NOMBRE_ARCHIVO);
            bufferedReader = new BufferedReader(fileReader);
            String linea;
            while ((linea = bufferedReader.readLine()) != null){
                String[] libroComoArreglo = linea.split(SEPARADOR_CAMPO);
                libros.add(new Libro(libroComoArreglo[0], libroComoArreglo[1], libroComoArreglo[2], libroComoArreglo[4],
                        libroComoArreglo[5], Boolean.valueOf(libroComoArreglo[3])));
            }
        }catch (IOException e){
            System.out.println("Excepcion leyendo archivo: " + e.getMessage());
        }finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("Excepción cerrando: " + e.getMessage());
            }
            return libros;
        }
    }
}
