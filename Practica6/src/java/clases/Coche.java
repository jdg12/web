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
    private double potencia;

    public Coche(String nombre, double potencia) {
        this.nombre = nombre;
        this.potencia = potencia;
    }

    public Coche() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPotencia() {
        return potencia;
    }

    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    @Override
    public String toString() {
        return "Coche{" + "nombre=" + nombre + ", ganancia=" + potencia + '}';
    }
    
    
}
