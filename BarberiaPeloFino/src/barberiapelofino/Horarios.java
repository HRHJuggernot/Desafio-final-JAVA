package barberiapelofino;

import java.time.LocalDate;

public class Horarios {
    private LocalDate horaInicio, HoraFinal;
    private int id;

    public Horarios() {
    }

    public Horarios(LocalDate horaInicio, LocalDate HoraFinal, int id) {
        this.horaInicio = horaInicio;
        this.HoraFinal = HoraFinal;
        this.id = id;
    }

    public LocalDate getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDate horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDate getHoraFinal() {
        return HoraFinal;
    }

    public void setHoraFinal(LocalDate HoraFinal) {
        this.HoraFinal = HoraFinal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
