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
public class Sorteo {
    private Originador originador;
    private Conserje conserje;
    private FichaSorteo ganador;

    public Sorteo() {
        originador = new Originador();
        conserje = new Conserje();
        ganador=null;
    }
    
    public void nuevoParticipante(Usuario usuario,Reserva reserva){
        FichaSorteo ficha = new FichaSorteo(usuario,reserva);
        originador.setFichaSorteo(ficha);
        conserje.setParticipante(originador.crearParticipante());
    }
    
    public int sortear(){
        int participantes = conserje.getNumParticipantes();
        double winner=-1;
        if(participantes!=0){
            winner = Math.floor(Math.random()*participantes);
            originador.setParticipante(conserje.getParticipante((int)winner));
            ganador = originador.getFichaSorteo();
        }
        return (int) winner;
    }
    
    public FichaSorteo getGanador(){
        
        return ganador;
    }
    public ArrayList<FichaSorteo> getParticipantes(){
        ArrayList<FichaSorteo> fichas = new ArrayList<>();
        ArrayList<Participante> participantes = conserje.getParticipantes();
        if(participantes == null){
            return null;
        }
        FichaSorteo ficha;
        Participante parti;
        Iterator it = participantes.iterator();
        while(it.hasNext()){
            parti = (Participante)it.next();
            ficha= parti.getFichaSorteo();
            fichas.add(ficha);
        }
        return fichas;
    }
    
}
