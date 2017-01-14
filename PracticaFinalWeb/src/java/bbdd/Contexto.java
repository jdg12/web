/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

import java.util.ArrayList;

/**
 *
 * @author JD
 */
public class Contexto {
    private Estrategia estrategia; // Estrategia que se usa.
    private ArrayList<Pelicula> peliculas; // Lista de alumnos.

    /**
     * Constructor.
     * @param e Estrategia que se usa.
     * @param p Lista de alumnos.
     */
    public Contexto(Estrategia e, ArrayList<Pelicula> p) {
        this.estrategia = e;
        this.peliculas = p;
    }

    /**
     * Establece la estrategia a usar.
     * @param e Estrategia a usar.
     */
    public void setEstrategia(Estrategia e) {
        this.estrategia = e;
    }

    /**
     * Ejecuta la estrategia.
     */
    public void ejecutaEstrategia() {
        estrategia.ordena(peliculas);
        for (Pelicula alumno : peliculas) {
            //System.out.println(alumno.toString());
        }
    }
}

