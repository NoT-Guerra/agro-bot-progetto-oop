package it.unibo.agrobot.model;

/**
 * implementazione base del Drone
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
}
