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
public class pruebaPelis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Pelicula peli;
        ArrayList <Actor> actores = new ArrayList <>();
        Actor actor = new Actor("Pepe","Lopez");
        actores.add(actor);
        actor = new Actor("Julia","Lopez");
        actores.add(actor);
        ConstructorPelicula cons = new ConstructorPelicula();
        Pelicula18Builder b1 = new Pelicula18Builder();
        Pelicula13Builder b2 = new Pelicula13Builder();
        
        cons.setPeliculaBuilder(b1);
        cons.crearPelicula("nombre", "sinopsis", "pagina", "titulo", "genero", "nacionalidad", 120, 1900, "distribuidora", "director", actores, "otros");
        System.out.println(cons.getPelicula().toString());
        
        
        cons.setPeliculaBuilder(b2);
        cons.crearPelicula("nombre2", "sinopsis2", "pagina2", "titulo2", "genero2", "nacionalidad2", 90, 2002, "distribuidora2", "director2", actores, "otros");
        System.out.println(cons.getPelicula().toString());
    }
    
}
