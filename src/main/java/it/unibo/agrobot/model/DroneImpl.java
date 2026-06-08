package it.unibo.agrobot.model;

/**
 * implementazione dell'interfaccia Drone
 * Mantiene lo stato interno del drone come le coordinate e ne gestisce
 * l'aggiornamento durante la simulazione
 */
public class DroneImpl implements Drone {

    private Position position;
    private Battery battery;
    
    //costante per il consumo base per movimento
    private static final double MOVEMENT_ENERGY_COST = 2.0;
    //costante per il consumo delle azioni agricole
    private static final double ACTION_ENERGY_COST = 5.0;

    public DroneImpl(Position position) {
        this.position = position;
        this.battery = new Battery(100.0); // La batteria parte sempre carica al max
    }

    @Override
    public double getBatteryLevel() {
        return this.battery.getLevel();
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void move(double deltaX, double deltaY) {
        if (!this.battery.isDead()) {
            this.battery.decrease(MOVEMENT_ENERGY_COST);
            double newX = this.position.getX() + deltaX;
            double newY = this.position.getY() + deltaY;
            this.position.setX(newX);
            this.position.setY(newY);
        }
    }

    @Override
    public void move(Direction dir, double distance) {
        switch (dir) {
            case UP:
                this.move(0, distance);
                break;
            case DOWN:
                this.move(0, -distance);
                break;
            case LEFT:
                this.move(-distance, 0);
                break;
            case RIGHT:
                this.move(distance, 0);
                break;
            default:
                break;
        }
    }

    @Override
    public void plow() {
        if (!this.battery.isDead()) {
            this.battery.decrease(ACTION_ENERGY_COST);
            //todo
            //inserisci la logica di interazione col terreno 
        }
    }

    @Override
    public void harvest() {
        if (!this.battery.isDead()) {
            this.battery.decrease(ACTION_ENERGY_COST);
            // La logica effettiva di raccolta risorsa verrà inserita qui
        }
    }

    @Override
    public boolean isDead() {
        return this.battery.isDead();
    }

    @Override
    public void rechargeAtHangar() {
        this.battery.recharge();
    }
}
