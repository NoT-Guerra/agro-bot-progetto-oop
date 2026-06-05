package it.unibo.agrobot.model;

/**
 * definisce il comportamento di base del drone.
 */
public interface Drone {

    Position getPosition();

    void move(double deltaX, double deltaY);
}
