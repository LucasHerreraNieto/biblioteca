package controladores;

import entities.Libro;
import entities.Prestamo;
import entities.Socio;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

public class ControladorPrestamos {
    private static final String NOMBRE_ARCHIVO = "prestamos.txt";
    private static final String SEPARADOR_CAMPO = ";";
    private static final String SEPARADOR_REGISTRO = "\n";

    public static void registrar(Prestamo prestamo){
        ControladorLibros.marcarComoPrestado(prestamo.getCodLibro());
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO, true));
            bufferedWriter.write(prestamo.getCodLibro() + SEPARADOR_CAMPO + prestamo.getNumSocio()
                    + SEPARADOR_CAMPO + prestamo.getFechaFormateada() + SEPARADOR_REGISTRO);
            bufferedWriter.close();
        }catch (IOException e){
            System.out.println("Error escribiendo el archivo:" + e.getMessage());
        }
    }

    public static ArrayList<Prestamo> obtener(){
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try{
            fileReader = new FileReader(NOMBRE_ARCHIVO);
            bufferedReader = new BufferedReader(fileReader);
            String linea;
            while((linea = bufferedReader.readLine())!=null){
                String[] prestamosComoArreglo = linea.split(SEPARADOR_CAMPO);
                prestamos.add(new Prestamo(prestamosComoArreglo[0], prestamosComoArreglo[1],
                        LocalDateTime.parse(prestamosComoArreglo[2],
                                new DateTimeFormatterBuilder().parseCaseInsensitive()
                                        .append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toFormatter())));
            }
        }catch (IOException e){
            System.out.println("Excepcion leyendo archivo: " + e.getMessage());
        }finally {
            try{
                if (fileReader != null){
                    fileReader.close();
                }
                if (bufferedReader != null){
                    bufferedReader.close();
                }
            }catch (IOException e){
                System.out.println("Excepcion cerrando: " + e.getMessage());
            }
        }
        return prestamos;
    }

    public static int cantidadLibrosPrestados(String numeroSocio, ArrayList<Prestamo> prestamos){
        int cantidad = 0;
        for (int x = 0; x < prestamos.size();x++){
            Prestamo prestamo = prestamos.get(x);
            if(prestamo.getNumSocio().equals(numeroSocio)){
                cantidad++;
            }
        }
        return cantidad;
    }

    public static void imprimirPrestamos(ArrayList<Prestamo> prestamos){
        System.out
                .println("+-----+------------------------------+------------------------------+--------------------+");
        System.out.printf("|%-5s|%-30s|%-30s|%-20s|\n", "No", "Codigo libro", "No. Socio", "Fecha");
        System.out
                .println("+-----+------------------------------+------------------------------+--------------------+");
        for (int x = 0; x < prestamos.size(); x++){
            Prestamo prestamo = prestamos.get(x);
            System.out.printf("|%-5d|%-30s|%-30s|%-20s|\n", x, prestamo.getCodLibro(), prestamo.getNumSocio(),
                    prestamo.getFechaFormateada());
            System.out.println(
                    "+-----+------------------------------+------------------------------+--------------------+");
        }
    }

    public static void solicitarDatosYCrearPrestamo(){
        Libro libro = ControladorLibros.imprimirLibrosYPedirSeleccion();
        Socio socio = ControladorSocios.imprimirSociosYPedirSeleccion();
        ControladorPrestamos.registrar(new Prestamo(libro.getCodigo(), socio.getNumeroDeSocio(), LocalDateTime.now()));
        System.out.println("Prestamo registrado correctamente");
    }
}
