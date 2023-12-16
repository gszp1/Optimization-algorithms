package ParticleSwarm;

import utils.Function;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ParticleSwarm {

    private final double minX;

    private final double maxX;

    private final double minY;

    private final double maxY;

    private final int populationSize;

    private final ArrayList<Particle> particles;

    private final Function function;

    private Position bestGlobalPosition;

    public ParticleSwarm(double minX, double maxX, double minY, double maxY, int size, Function function) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        populationSize = size;
        particles = new ArrayList<>();
        int particleCount = 0;
        while (particleCount < populationSize) {
            Particle particle = new Particle(ThreadLocalRandom.current().nextDouble(this.minX, this.maxX),
                    ThreadLocalRandom.current().nextDouble(this.minY, this.maxY)
            );
            if (!inPopulation(particle)) {
                particles.add(particle);
                ++particleCount;
            }
        }
        this.function = function;
        updateParticlesValues();
        updateBestGlobalSolution();
        for (Particle particle: particles) {
            particle.setBestGlobalPosition(bestGlobalPosition);
        }
    }


    private void updateParticlesValues() {
        for (Particle particle: particles) {
            particle.setValue(function.evaluate(particle.getPosition().getX(), particle.getPosition().getY()));
        }
    }


    private void updateBestGlobalSolution() {
        Particle bestParticle = particles.get(0);
        for (Particle particle: particles) {
            if (particle.getValue() > bestParticle.getValue()) {
                bestParticle = particle;
            }
        }
        bestGlobalPosition = bestParticle.getPosition();
    }


    private boolean inPopulation(Particle particle) {
        for (Particle particle1: particles) {
            if (particle.comparePositions(particle1)) {
                return true;
            }
        }
        return false;
    }
}
