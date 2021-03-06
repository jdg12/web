/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author jesus
 */
public class Circuito {
    private String nombre;
    private String ciudad;
    private String pais;
    private int vueltas;
    private double longitud;
    private int curvas;

    public Circuito(String nombre, String ciudad, String pais, int vueltas, double longitud, int curvas) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.vueltas = vueltas;
        this.longitud = longitud;
        this.curvas = curvas;
    }

    public Circuito() {
    }

    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getVueltas() {
        return vueltas;
    }

    public void setVueltas(int vueltas) {
        this.vueltas = vueltas;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getCurvas() {
        return curvas;
    }

    public void setCurvas(int curvas) {
        this.curvas = curvas;
    }

    @Override
    public String toString() {
        return "Circuito{" + "nombre=" + nombre + ", ciudad=" + ciudad + ", pais=" + pais + ", vueltas=" + vueltas + ", longitud=" + longitud + ", curvas=" + curvas + '}';
    }
    
}
