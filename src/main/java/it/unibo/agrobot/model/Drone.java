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
     * @return il livello attuale di acqua nel serbatoio del drone
     */
    double getWaterLevel();

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
     * ticarica completamente il serbatoio dell'acqua del drone
     * chiamato quando il drone si trova sul lago
     */
    void rechargeWaterAtLake();

    /**
     * Sposta il drone in una specifica direzione per una determinata distanza
     * (serve per collegare gli input da tastiera)
     * 
     * @param dir la direzione verso cui muoversi
     * @param distance l'entità dello spostamento
     */
    void move(Direction dir, double distance);

    /**
     * esegue azione di aratura del terreno
     * questa azione consuma più energia rispetto a un normale movimento
     */
    void plow();

    /**
     * raccoglie la risorsa presente nella posizione corrente
     * esegurie questa azione consuma energia(piu del movimento)
     */
    void harvest();

    /**
     * butta acqua sul terreno per irrigare
     * consuma sia acqua dal serbatoio(consuma 10 su max 50) che energia dalla batteria
     * 
     * @return true se c'era abbastanza acqua ed energia per irrigare, false altrimenti
     */
    boolean irrigate();

    /**
     * Verifica se il drone ha ancora batteria
     * @return true se l'energia è 0, false altrimenti
     */
    boolean isDead();

    /**
     * Ripristina l'energia del drone ricaricando la batteria
     * viene chiamata quando il drone si trova sull hanggar
     */
    void rechargeAtHangar();
}
