package it.unibo.agrobot.model;

/**
 * gestisce l'energia disponibile per le azioni del drone
 * ongi azione comporta un decremetno della batteria
 */
public class Battery {

    private double level;
    private double maxCapacity;

    public Battery(double maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.level = maxCapacity;
    }

    public double getLevel() {
        return this.level;
    }

    /**
     * riduce il livello della batteria dell'ammontare specificato.
     * il livello non può mai scendere sotto lo zero
     * 
     * @param amount quantità di energia da consumare
     */
    public void decrease(double amount) {
        this.level -= amount;
        if (this.level < 0) {
            this.level = 0;
        }
    }
}
