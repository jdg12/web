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
public class Conserje {
    // Lista de participantes.
    private ArrayList<Participante> participantes = new ArrayList<>();

    /**
     * Añade un recuerdo a la lista de participantes.
     * @param recuerdo Participante a añadir.
     */
    public void setParticipante(Participante recuerdo) {
        participantes.add(recuerdo);
    }

    /**
     * Recupera un recuerdo a partir del índice pasado como argumento.
     * @param indice Índice del recuerdo.
     * @return Participante.
     */
    public Participante getParticipante(int indice) {
        if (indice < participantes.size()) {
            Participante r = participantes.get(indice);
            return r;
        } else {
            return null;
        }
    }
    public ArrayList<Participante> getParticipantes(){
        return participantes;
    }
    
    public int getNumParticipantes(){
        
        return participantes.size();
    }
}
