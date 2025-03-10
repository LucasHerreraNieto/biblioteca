package entities;

import java.util.ArrayList;

public class Libro {
    private String codigo,titulo,autor,localizacion,signatura;
    private boolean disponible;

    //Constructor
    public Libro(String codigo, String titulo, String autor, String localizacion, String signatura, boolean disponible) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.localizacion = localizacion;
        this.signatura = signatura;
        this.disponible = disponible;
    }

    //Getters and setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getSignatura() {
        return signatura;
    }

    public void setSignatura(String signatura) {
        this.signatura = signatura;
    }

    //To string

    @Override
    public String toString() {
        return "Libro{" +
                "codigo='" + codigo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", localizacion='" + localizacion + '\'' +
                ", signatura='" + signatura + '\'' +
                ", disponible=" + disponible +
                '}';
    }
};
