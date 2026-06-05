package it.unibo.agrobot.model;

/**
 * definisce il comportamento di base del drone.
 */
public interface Drone {

    Position getPosition();

    /**
     * sposta il drone rispetto alla sua posizione attuale
     * il movimento avviene tramite l'aggiunta di una differenza
     * alle coordinate X e Y
     * 
     * @param deltaX valore da sommare all'attuale coordinata X
     * @param deltaY valore da sommare all'attuale coordinata Y
     */
    void move(double deltaX, double deltaY);
}
