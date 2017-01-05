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
        ArrayList<Pelicula> pelis  = new ArrayList <>();
        
        cons.setPeliculaBuilder(b1);
        cons.crearPelicula("Viernes 13", "sinopsis", "pagina", "titulo", "Terror", "nacionalidad", 120, 1999, "distribuidora", "director", actores, "otros");
        peli=cons.getPelicula();
        pelis.add(peli);
        
        cons.setPeliculaBuilder(b2);
        cons.crearPelicula("Pulp Fiction", "sinopsis2", "pagina2", "titulo2", "Accion", "nacionalidad2", 90, 2002, "distribuidora2", "director2", actores, "otros");
        peli=cons.getPelicula();
        pelis.add(peli);
        
        cons.setPeliculaBuilder(b2);
        cons.crearPelicula("Frozen", "sinopsis2", "pagina2", "titulo2", "Animacion", "nacionalidad2", 90, 2016, "distribuidora2", "director2", actores, "otros");
        peli=cons.getPelicula();
        pelis.add(peli);
        //Patron Strategy
        System.out.println("Ordenados por Nombre:");
        EstrategiaNombre e= new EstrategiaNombre();
        Contexto con= new Contexto(e,pelis);
        con.ejecutaEstrategia();
        
        System.out.println("\nOrdenados por Año:");
        EstrategiaAno ea= new EstrategiaAno();
        con.setEstrategia(ea);
        con.ejecutaEstrategia();
        
    }
    
}
