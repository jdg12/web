/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

import java.util.ArrayList;

/**
 *
 * @author jesus
 */
interface BBDD {
    public boolean estaUsuario(String idUsuario);
    public boolean contrasenaCorrecta(String idUsuario, String contrasena);
    public Usuario getUsuario(String idUsuario);
    public void guardarUsuario(Usuario usuario);
    public ArrayList<Reserva> getReservas(String idUsuario);
}
