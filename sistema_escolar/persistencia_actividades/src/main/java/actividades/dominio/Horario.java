/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package actividades.dominio;

import java.util.List;

/**
 *
 * @author janethcristinagalvanquinonez
 */
public class Horario {
    
    private String horaInicio;
    private String horaFin;
    private List<String> dias;

    public Horario() {
    }

    public Horario(String horaInicio, String horaFin, List<String> dias) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.dias = dias;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public List<String> getDias() {
        return dias;
    }

    public void setDias(List<String> dias) {
        this.dias = dias;
    }

    
    
}
