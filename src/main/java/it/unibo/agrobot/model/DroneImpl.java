package it.unibo.agrobot.model;

/**
 * implementazione dell'interfaccia Drone.
 * Mantiene lo stato interno del drone come le coordinate e ne gestisce
 * l'aggiornamento durante la simulazione.
 */
public class DroneImpl implements Drone {

    private Position position;

    public DroneImpl(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public void move(double deltaX, double deltaY) {
        double newX = this.position.getX() + deltaX;
        double newY = this.position.getY() + deltaY;
        this.position.setX(newX);
        this.position.setY(newY);
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
}
