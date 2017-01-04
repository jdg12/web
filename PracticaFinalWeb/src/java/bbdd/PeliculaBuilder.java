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
public abstract class PeliculaBuilder {
    
    protected Pelicula pelicula;
    
    public Pelicula getPelicula(){
        return pelicula;
    }
    
    public void crearPelicula(){
        pelicula = new Pelicula();
    }
    
    public void ponerNombre(String nombre){
        pelicula.setNombre(nombre);
    }
    
    public void ponerSinopsis(String nombre){
        pelicula.setSinopsis(nombre);
    }
    
    public void ponerPagina(String nombre){
        pelicula.setPagina(nombre);
    }
    
    public void ponerTitulo(String nombre){
        pelicula.setTitulo(nombre);
    }
    
    public void ponerGenero(String nombre){
        pelicula.setGenero(nombre);
    }
    
    public void ponerNacionalidad(String nombre){
        pelicula.setNacionalidad(nombre);
    }
    
    public void ponerDuracion(int duracion){
        pelicula.setDuracion(duracion);
    }
    
    public void ponerAno(int ano){
        pelicula.setAno(ano);
    }
    
    public void ponerDistribuidora(String nombre){
        pelicula.setDistribuidora(nombre);
    }
    
    public void ponerDirector(String nombre){
        pelicula.setDirector(nombre);
    }
    
    public void ponerActores(ArrayList<Actor> actores){
        pelicula.setActores(actores);
    }
    
    public abstract void ponerClasificacion();
    
    public abstract void ponerOtros(String otros);
}
