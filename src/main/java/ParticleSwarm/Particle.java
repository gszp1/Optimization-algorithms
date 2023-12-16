package ParticleSwarm;

public class Particle {

    private Position bestLocalPosition;

    private Position bestGlobalPosition;

    private final Position position;

    private final Velocity velocity;

    private double value;

    public Particle(double x, double y, Velocity velocity) {
        this.position = new Position(x, y);
        this.velocity = velocity;
        bestLocalPosition = position;
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
}
