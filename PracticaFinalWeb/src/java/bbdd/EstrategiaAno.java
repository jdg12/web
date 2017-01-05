/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author JD
 */
public class EstrategiaAno implements Estrategia {

    @Override
    public void ordena(ArrayList<Pelicula> peliculas) {
        //Comparador para ordenar los alumnos por su nombre
        Comparator AnoComp = new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                Pelicula peli1 = (Pelicula) o1;
                Pelicula peli2 = (Pelicula) o2;

                if (peli1.getAno() == peli2.getAno()) {
                    return 0;
                } else if (peli1.getAno() < peli2.getAno()) {
                    return -1;
                } else {
                    return +1;
                }
            }
        };

        //Ordenamos los objetos del array por el atributo nombre
        Collections.sort(peliculas, AnoComp);
    }
}
