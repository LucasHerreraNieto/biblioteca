package entities;

import java.util.ArrayList;

public class Socio {
    private String numeroDeSocio;
    private String nombre;
    private String direccion;

    //Constructor
    public Socio(String numeroDeSocio, String nombre, String direccion) {
        this.numeroDeSocio = numeroDeSocio;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    //getters and setters
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumeroDeSocio() {
        return numeroDeSocio;
    }

    public void setNumeroDeSocio(String numeroDeSocio) {
        this.numeroDeSocio = numeroDeSocio;
    }

    @Override
    public String toString() {
        return "Socio{" +
                "numeroDeSocio=" + numeroDeSocio +
                ", nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                '}';
    }
}
