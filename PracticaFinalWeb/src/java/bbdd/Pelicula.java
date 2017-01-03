/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bbdd;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author JD
 */
public class Pelicula {
    private String nombre;
    private String sinopsis;
    private String pagina;
    private String titulo;
    private String genero;
    private String nacionalidad;
    private int duracion;
    private int ano;
    private String distribuidora;
    private String director;
    private ArrayList<Actor> actores;
    private int clasificacion;
    private String otros;

    public Pelicula(String nombre, String sinopsis, String pagina, String titulo, String genero, String nacionalidad, int duracion, int año, String distribuidora, String director, ArrayList<Actor> actores, int edad, String otros) {
        this.nombre = nombre;
        this.sinopsis = sinopsis;
        this.pagina = pagina;
        this.titulo = titulo;
        this.genero = genero;
        this.nacionalidad = nacionalidad;
        this.duracion = duracion;
        this.ano = año;
        this.distribuidora = distribuidora;
        this.director = director;
        this.actores = actores;
        this.clasificacion = edad;
        this.otros = otros;
    }

    public Pelicula() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getDistribuidora() {
        return distribuidora;
    }

    public void setDistribuidora(String distribuidora) {
        this.distribuidora = distribuidora;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public ArrayList<Actor> getActores() {
        return actores;
    }

    public void setActores(ArrayList<Actor> actores) {
        this.actores = actores;
    }
    
    public boolean añadirActor(Actor actor){
        Iterator it = actores.iterator();
        Actor aux;
        boolean flag=true;
        while(it.hasNext()){
            aux = (Actor) it.next();
            
            if(aux.equals(actor)){
                flag = false;
                break;
            }
                
        }
        if(flag){
            actores.add(actor);
        }
        return flag;
    }
    
    public boolean quitarActor(Actor actor){
        return actores.remove(actor);
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getOtros() {
        return otros;
    }

    public void setOtros(String otros) {
        this.otros = otros;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "nombre=" + nombre + ", sinopsis=" + sinopsis + ", pagina=" + pagina + ", titulo=" + titulo + ", genero=" + genero + ", nacionalidad=" + nacionalidad + ", duracion=" + duracion + ", a\u00f1o=" + ano + ", distribuidora=" + distribuidora + ", director=" + director + ", actores=" + actores + ", edad=" + clasificacion + ", otros datos=" + otros + '}';
    }
    
    
}
