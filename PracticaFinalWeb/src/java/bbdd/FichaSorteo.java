/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

import java.util.Calendar;

/**
 *
 * @author JD
 */
public class FichaSorteo {
    private Usuario usuario;
    private Reserva reserva;
    private int año;
    private int mes;
    private int dia;
    private int hora;
    private int min;

    public FichaSorteo(Usuario usuario, Reserva reserva) {
        this.usuario = usuario;
        this.reserva = reserva;
        Calendar hoy = Calendar.getInstance();
        año = hoy.get(Calendar.YEAR);
        mes = hoy.get(Calendar.MONTH) + 1;
        dia = hoy.get(Calendar.DAY_OF_MONTH);
        hora = hoy.get(Calendar.HOUR_OF_DAY);
        min = hoy.get(Calendar.MINUTE);
    }

    public FichaSorteo() {
    }
    
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    @Override
    public String toString() {
        return "FichaSorteo{" + "usuario=" + usuario + ", reserva=" + reserva + ", a\u00f1o=" + año + ", mes=" + mes + ", dia=" + dia + ", hora=" + hora + ", min=" + min + '}';
    }
    public String getFecha() {
        if (min < 10) {
            return dia + "/" + mes + "/" + año + "/" + hora + ":0" + min;
        } else {
            return dia + "/" + mes + "/" + año + "/" + hora + ":" + min;
        }
    }
    
    
    
}
