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
public class pruebaEntradas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*
        Fecha f= new Fecha(2,10,2016);
        Sesion s = new Sesion("nombre pelicula","nombre sala",f,"21:30");
        
        FactoriaEntradas factor = new FactoriaEntradas();
        Entrada e;
        //Entrada normal
        e = factor.nuevaEntrada(0, "0", s, 10, 5, false, 6);
        System.out.println(e.toString());
        e = factor.nuevaEntrada(1, "1", s, 5, 14, false, 6);
        System.out.println(e.toString());
*/
        int participantes = 9;
        double winner=-1;
        for (int i = 0; i < 20; i++) {
            winner = Math.floor(Math.random()*participantes);
            System.out.println("winner: "+winner);
            
        }
            
    }
    
}
