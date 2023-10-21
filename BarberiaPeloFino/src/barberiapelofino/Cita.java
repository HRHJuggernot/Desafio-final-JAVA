package barberiapelofino;

import java.io.Serializable;
import java.time.LocalDate;

public class Cita implements Serializable{
    private static final long serialVesionUID = 1L;
    private int id;
    private LocalDate fechaInicio, fechaFinal;
    private String estado;

    public Cita() {
    }

    public Cita(int id, LocalDate fechaInicio, LocalDate fechaFinal, String estado) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(LocalDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
