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
public class FactoriaEntradas {
    
    public static final int NORMAL = 0;
    public static final int REDUCIDA = 1;
    
    public Entrada nuevaEntrada(int tipo, String id, Sesion sesion, int fila, int columna, boolean vendida, double precio){
        if (tipo == NORMAL) {
            return (new EntradaNormal(id,sesion,fila,columna,vendida,precio));
        } else {
            return (new EntradaReducida(id,sesion,fila,columna,vendida,precio));
        }
    }
}
