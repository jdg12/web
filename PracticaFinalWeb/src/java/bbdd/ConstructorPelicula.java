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
public class ConstructorPelicula {
    private PeliculaBuilder pb;

    public PeliculaBuilder getPb() {
        return pb;
    }

    public void setPeliculaBuilder(PeliculaBuilder pb) {
        this.pb = pb;
    }
    
    /**
     * Devuelve el producto complejo creado.
     * @return producto Pizza
     */
    public Pelicula getPelicula() {
        return pb.getPelicula();
    }
    
    public void crearPelicula(String nombre, String sinopsis, String pagina, String titulo, String genero, String nacionalidad, int duracion, int año, String distribuidora, String director, ArrayList<Actor> actores, String otros){
        pb.crearPelicula();
        pb.ponerNombre(nombre);
        pb.ponerSinopsis(sinopsis);
        pb.ponerPagina(pagina);
        pb.ponerTitulo(titulo);
        pb.ponerGenero(genero);
        pb.ponerNacionalidad(nacionalidad);
        pb.ponerDuracion(duracion);
        pb.ponerAno(año);
        pb.ponerDistribuidora(distribuidora);
        pb.ponerDirector(director);
        pb.ponerActores(actores);
        pb.ponerOtros(otros);
        pb.ponerClasificacion();
    }
}
