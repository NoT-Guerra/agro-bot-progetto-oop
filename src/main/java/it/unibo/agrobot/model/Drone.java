package it.unibo.agrobot.model;

/**
 * rappresenta l'entità principale controllata dal giocatore, ovvero il drone che si muove nel campo.
 * definisce le funzionalità per interrogarne lo stato e per muoverlo nello spazio.
 */
public interface Drone {

    /**
     * @return l'oggetto position che contiene le coordinate attuali del drone
     */
    Position getPosition();

    /**
     * @return il livello attuale di carica della batteria
     */
    double getBatteryLevel();

    /**
     * sposta il drone rispetto alla sua posizione attuale
     * il movimento avviene tramite l'aggiunta di una differenza
     * alle coordinate X e Y
     * 
     * @param deltaX valore da sommare all'attuale coordinata X
     * @param deltaY valore da sommare all'attuale coordinata Y
     */
    void move(double deltaX, double deltaY);

    /**
     * Sposta il drone in una specifica direzione per una determinata distanza.
     * risulta utile per collegare gli input da tastiera.
     * 
     * @param dir la direzione verso cui muoversi
     * @param distance l'entità dello spostamento
     */
    void move(Direction dir, double distance);
}
