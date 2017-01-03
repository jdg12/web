
package bbdd;


public class Fecha {

    /**
     * Entero que representa el día.
     */
    private int dia;
    /**
     * Entero que representa el mes.
     */
    private int mes;
    /**
     * Entero que representa el año.
     */
    private int año;

    public Fecha(int dia, int mes, int año) {
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }

    public Fecha() {
    }

    
    
    /**
     * Devuelve el año.
     * @return Entero que representa el año.
     */
    public int getAño() {
        return this.año;
    }

    /**
     * Establece el año.
     * @param año Entero que representa el año. 
     */
    public void setAño(int año) {
        this.año = año;
    }

    /**
     * Devuelve el día.
     * @return Entero que representa el día.
     */
    public int getDia() {
        return this.dia;
    }

    /**
     * Establece el día.
     * @param dia Entero que representa el día.
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Devuelve el mes.
     * @return Entero que representa el mes.
     */
    public int getMes() {
        return this.mes;
    }

    /**
     * Establece el mes.
     * @param mes Entero que representa el mes.
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    @Override
    public String toString() {
        return dia + " - " + mes + " - " + año ;
    }
    
}
