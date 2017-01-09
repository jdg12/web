/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

/**
 *
 * @author jesus
 */
public class Pelicula0Builder extends PeliculaBuilder{

    @Override
    public void ponerClasificacion() {
        pelicula.setClasificacion(0);
    }

    @Override
    public void ponerOtros(String otros) {
       pelicula.setOtros(otros);
    }
    
}
