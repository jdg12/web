/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

/**
 *
 * @author JD
 */
public class Reserva {
    private Entrada entrada;
    private Sesion sesion;
    private String idUsuario;
    private String idReserva;

    public Reserva(Entrada entrada, Sesion sesion, String idUsuario, String idReserva) {
        this.entrada = entrada;
        this.sesion = sesion;
        this.idUsuario = idUsuario;
        this.idReserva = idReserva;
    }

    public Reserva() {
    }

    public Entrada getEntrada() {
        return entrada;
    }

    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(String idReserva) {
        this.idReserva = idReserva;
    }

    @Override
    public String toString() {
        return "Reserva{" + "entrada=" + entrada + ", sesion=" + sesion + ", idUsuario=" + idUsuario + ", idReserva=" + idReserva + '}';
    }
    
    
}
