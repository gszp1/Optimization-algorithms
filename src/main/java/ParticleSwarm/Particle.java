package ParticleSwarm;

import utils.Function;

import java.util.concurrent.ThreadLocalRandom;

public class Particle {

    private final Position bestLocalPosition;

    private Position bestGlobalPosition;

    private final Position position;

    private final Velocity velocity;

    private double value;

    public Particle(double x, double y, Velocity velocity) {
        this.position = new Position(x, y);
        this.velocity = velocity;
        bestLocalPosition = new Position(position.getX(), position.getY());
    }

    public void updateVelocity(double inertiaCoefficient,
                               double socialCoefficient, double cognitiveCoefficient) {
        velocity.setxVelocity(getUpdatedXVelocity(inertiaCoefficient, socialCoefficient, cognitiveCoefficient));
        velocity.setyVelocity(getUpdatedYVelocity(inertiaCoefficient, socialCoefficient, cognitiveCoefficient));
    }

    private double getUpdatedXVelocity(double inertiaCoefficient,
                                       double socialCoefficient, double cognitiveCoefficient) {
        double rp = ThreadLocalRandom.current().nextDouble(0, 1.0);
        double rg = ThreadLocalRandom.current().nextDouble(0, 1.0);
        return inertiaCoefficient * velocity.getxVelocity() +
                cognitiveCoefficient * rp * (bestLocalPosition.getX() - position.getX()) +
                socialCoefficient * rg * (bestGlobalPosition.getX() - position.getX());
    }

    private double getUpdatedYVelocity(double inertiaCoefficient,
                                       double socialCoefficient, double cognitiveCoefficient) {
        double rp = ThreadLocalRandom.current().nextDouble(0, 1.0);
        double rg = ThreadLocalRandom.current().nextDouble(0, 1.0);
        return inertiaCoefficient * velocity.getxVelocity() +
                cognitiveCoefficient * rp * (bestLocalPosition.getY() - position.getY()) +
                socialCoefficient * rg * (bestGlobalPosition.getY() - position.getY());
    }

    public void updateValue(Function function) {
        value = function.evaluate(position.getX(), position.getY());
    }


    public void setBestGlobalPosition(Position position) {
        bestGlobalPosition = position;
    }

    public Position getPosition() {
        return position;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean comparePositions(Particle particle) {
        return (position.getX() == particle.getPosition().getX() &&
                position.getY() == particle.getPosition().getY());
    }

    public void updateBestLocalPosition(Function function) {
        if (function.evaluate(bestLocalPosition.getX(), bestLocalPosition.getY()) < value) {
            bestLocalPosition.setX(position.getX());
            bestLocalPosition.setY(position.getY());
        }
    }

    public void updatePosition() {
        position.setX(position.getX() + velocity.getxVelocity());
        position.setY(position.getY() + velocity.getyVelocity());
    }



}
