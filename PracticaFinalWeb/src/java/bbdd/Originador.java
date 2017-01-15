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
public class Originador {
    // Referencia a la ficha.
    private FichaSorteo ficha;

    /**
     * Devuelve la ficha.
     * @return FichaSorteo.
     */
    public FichaSorteo getFichaSorteo() {
        return this.ficha;
    }

    /**
     * Establece la ficha.
     * @param ficha 
     */
    public void setFichaSorteo(FichaSorteo ficha) {
        this.ficha = ficha;
    }

    /**
     * Asigna un participante a la ficha.
     * @param participante 
     */
    public void setParticipante(Participante participante) {
        ficha = participante.getFichaSorteo();
    }

    /**
     * Devuelve un participante creado a partir de la ficha actual.
     * @return 
     */
    public Participante crearParticipante() {
        return new Participante(ficha);
    }
}
