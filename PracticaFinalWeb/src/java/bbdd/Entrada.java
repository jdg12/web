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
public abstract class Entrada {
    protected String id;
    protected Sesion sesion;
    protected int fila;
    protected int columna;
    protected boolean vendida;
    protected double precio;

    public Entrada(String id, Sesion sesion, int fila, int columna, boolean vendida, double precio) {
        this.id = id;
        this.sesion = sesion;
        this.fila = fila;
        this.columna = columna;
        this.vendida = vendida;
        this.precio = precio;
    }

    public Entrada() {
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sesion getSesion() {
        return sesion;
    }

    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public boolean isVendida() {
        return vendida;
    }

    public void setVendida(boolean vendida) {
        this.vendida = vendida;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    @Override
    public abstract String toString();
    

    
    
}
