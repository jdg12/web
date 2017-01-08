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
public class EntradaNormal extends Entrada{

    public EntradaNormal(String id, Sesion sesion, int fila, int columna, boolean vendida, double precio) {
        super(id, sesion, fila, columna, vendida, precio);
        
    }
    
    public EntradaNormal(){}

    @Override
    public String toString() {
        return "Entrada Normal{" + "id: " + id +" "+ sesion.toString() +" fila:" +fila+" columna: "+columna+" vendida: "+vendida+" precio: "+precio+'}';
    }
    
    
}
