/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author JD
 */
public class Coche {
    private String nombre;
    private double ganancia;

    public Coche(String nombre, double ganancia) {
        this.nombre = nombre;
        this.ganancia = ganancia;
    }

    public Coche() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getGanancia() {
        return ganancia;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }

    @Override
    public String toString() {
        return "Coche{" + "nombre=" + nombre + ", ganancia=" + ganancia + '}';
    }
    
    
}
