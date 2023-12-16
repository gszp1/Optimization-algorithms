package ParticleSwarm;

public class Particle {

    private Position bestLocalPosition;

    private Position bestGlobalPosition;

    private final Position position;

    private double value;

    public Particle(double x, double y) {
        this.position = new Position(x, y);
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
