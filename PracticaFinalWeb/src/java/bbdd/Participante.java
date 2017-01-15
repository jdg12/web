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
public class Participante {
    //la Ficha del sorteo que se quiere recordar
    private FichaSorteo ficha = null;

    /**
     * Constructor.
     * @param ficha FichaSorteo que se va a recordar.
     */
    public Participante(FichaSorteo ficha) {
        this.ficha = ficha;
    }

    /**
     * Devuelve la ficha que se va a recordar.
     * @return FichaSorteo que se va a recordar.
     */
    public FichaSorteo getFichaSorteo() {
        return ficha;
    }

    /**
     * Establece la ficha que se va a recordar.
     * @param ficha FichaSorteo que se va a recordar.
     */
    public void setFichaSorteo(FichaSorteo ficha) {
        this.ficha = ficha;
    }
}
