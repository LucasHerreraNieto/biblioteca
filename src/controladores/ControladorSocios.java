package controladores;

import entities.Prestamo;
import entities.Socio;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ControladorSocios {
    private static final String NOMBRE_ARCHIVO = "socios.txt";
    private static final String SEPARADOR_CAMPO = ";";
    private static final String SEPARADOR_REGISTRO = "\n";


    public static void solicitarDatosParaRegistrar(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese numero de socio: ");
        String numero = sc.nextLine();
        System.out.println("Ingrese nombre de socio");
        String nombre = sc.nextLine();
        System.out.println("Ingrese direccion de socio: ");
        String direccion = sc.nextLine();
        ControladorSocios.registrar(new Socio(numero,nombre,direccion));
    }

    public static void registrar(Socio unSocio){
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(NOMBRE_ARCHIVO,true));
            bufferedWriter.write(unSocio.getNumeroDeSocio()+ SEPARADOR_CAMPO + unSocio.getNombre() + SEPARADOR_CAMPO +
                    unSocio.getDireccion() + SEPARADOR_REGISTRO);
            bufferedWriter.close();
        }catch (IOException e){
            System.out.println("Error escribiendo en archivo: " + e.getMessage());
        }
    }

    public static ArrayList<Socio> obtener() {
        ArrayList<Socio> socios = new ArrayList<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try{
            fileReader = new FileReader(NOMBRE_ARCHIVO);
            bufferedReader = new BufferedReader(fileReader);
            String linea;
            while ((linea = bufferedReader.readLine()) != null){
                String[] socioComoArreglo = linea.split(SEPARADOR_CAMPO);
                socios.add(new Socio(socioComoArreglo[0], socioComoArreglo[1],socioComoArreglo[2]));
            }
        }catch (IOException e){
            System.out.print("Excepcion leyendo archivo: " + e.getMessage());
        }finally {
            try {
                if(fileReader != null){
                    fileReader.close();
                }
                if (bufferedReader != null){
                    bufferedReader.close();
                }
            }catch (IOException e){
                System.out.println("Excepcion cerrando: " + e.getMessage());
            }
        }
        return socios;
    }

    public static void imprimirSocios(ArrayList<Socio> socios) {
        ArrayList<Prestamo> prestamos = ControladorPrestamos.obtener();
        System.out.println(
                "+-----+----------+----------------------------------------+----------------------------------------+--------------------+");
        System.out.printf("|%-5s|%-10s|%-40s|%-40s|%-20s|\n", "#", "No. socio", "Nombre", "Direccion",
                "Libros prestados");
        System.out.println(
                "+-----+----------+----------------------------------------+----------------------------------------+--------------------+");
        for (int x = 0; x < socios.size(); x++) {
            Socio socio = socios.get(x);
            System.out.printf("|%-5s|%-10s|%-40s|%-40s|%-20s|\n", x + 1, socio.getNumeroDeSocio(), socio.getNombre(),
                    socio.getDireccion(), ControladorPrestamos.cantidadLibrosPrestados(socio.getNumeroDeSocio(), prestamos));
            System.out.println(
                    "+-----+----------+----------------------------------------+----------------------------------------+--------------------+");
        }
    }

    public static void imprimirSociosNoFiables(ArrayList<Socio> socios) {
        ArrayList<Prestamo> prestamos = ControladorPrestamos.obtener();
        System.out.println(
                "+-----+----------+----------------------------------------+----------------------------------------+--------------------+");
        System.out.printf("|%-5s|%-10s|%-40s|%-40s|%-20s|\n", "#", "No. socio", "Nombre", "Direccion",
                "Libros prestados");
        System.out.println(
                "+-----+----------+----------------------------------------+----------------------------------------+--------------------+");
        for (int x = 0; x < socios.size(); x++) {
            Socio socio = socios.get(x);
            int librosPrestados = ControladorPrestamos.cantidadLibrosPrestados(socio.getNumeroDeSocio(), prestamos);
            if (librosPrestados < 10) {
                continue;
            }
            System.out.printf("|%-5s|%-10s|%-40s|%-40s|%-20s|\n", x + 1, socio.getNumeroDeSocio(), socio.getNombre(),
                    socio.getDireccion(), librosPrestados);
            System.out.println(
                    "+-----+----------+----------------------------------------+----------------------------------------+--------------------+");
        }
    }

    public static int buscarSocioPorNumero(String numero,ArrayList<Socio> socios){
        for (int x = 0 ; x < socios.size();x++){
            Socio socio = socios.get(x);
            if (socio.getNumeroDeSocio().equals(numero)){
                return x;
            }
        }
        return -1;
    }

    public static Socio imprimirSociosYPedirSeleccion(){
        ArrayList<Socio> socios = ControladorSocios.obtener();
        Scanner sc = new Scanner(System.in);
        while (true){
            ControladorSocios.imprimirSocios(socios);
            System.out.println("Ingrese el numero de socio: ");
            String numero = sc.nextLine();
            int indice = ControladorSocios.buscarSocioPorNumero(numero,socios);
            if(indice == -1){
                System.out.println("no existe socio con ese numero");
            }else{
                return socios.get(indice);
            }
        }
    }
}
