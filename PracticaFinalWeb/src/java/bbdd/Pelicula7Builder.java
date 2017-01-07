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
public class Pelicula7Builder extends PeliculaBuilder {
     @Override
    public void ponerClasificacion(){
        pelicula.setClasificacion(7);
    }
    
    @Override
    public void ponerOtros(String otros){
        //otros+="\nPelicula No recomendada para menores de 7 anios.";
        pelicula.setOtros(otros);
    }
}
