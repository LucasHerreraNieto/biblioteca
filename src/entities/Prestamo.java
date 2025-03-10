package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Prestamo {
    private String codLibro;
    private String numSocio;
    private LocalDateTime fecha;

    public Prestamo(String codLibro, String numSocio, LocalDateTime fecha) {
        this.codLibro = codLibro;
        this.numSocio = numSocio;
        this.fecha = fecha;
    }

    public String getCodLibro() {
        return codLibro;
    }

    public void setCodLibro(String codLibro) {
        this.codLibro = codLibro;
    }

    public String getNumSocio() {
        return numSocio;
    }

    public void setNumSocio(String numSocio) {
        this.numSocio = numSocio;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getFechaFormateada(){
        String formato = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern(formato);
        return formateador.format(this.fecha);
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "codLibro='" + codLibro + '\'' +
                ", numSocio=" + numSocio +
                ", fecha=" + this.getFechaFormateada() +
                '}';
    }
}
