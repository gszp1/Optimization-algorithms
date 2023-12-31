package ParticleSwarm;

import utils.Function;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ParticleSwarmAlgorithm {

    private final double minX;

    private final double maxX;

    private final double minY;

    private final double maxY;

    private final int populationSize;

    private final ArrayList<Particle> particles;

    private final Function function;

    private Position bestGlobalPosition;

    private final int numberOfEpochs;

    private final double socialCoefficient;

    private final double cognitiveCoefficient;

    private final double inertiaCoefficient;

    public ParticleSwarmAlgorithm(double minX, double maxX, double minY, double maxY, int size, Function function, int numberOfEpochs,
                                  double socialCoefficient, double cognitiveCoefficient, double inertiaCoefficient) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.numberOfEpochs = numberOfEpochs;
        this.socialCoefficient = socialCoefficient;
        this.cognitiveCoefficient = cognitiveCoefficient;
        this.inertiaCoefficient = inertiaCoefficient;
        populationSize = size;
        particles = new ArrayList<>();
        int particleCount = 0;
        while (particleCount < populationSize) {
            double xVelocity = ThreadLocalRandom.current().nextDouble(-Math.abs(maxX - minX), Math.abs(maxX - minX));
            double yVelocity = ThreadLocalRandom.current().nextDouble(-Math.abs(maxY - minY), Math.abs(maxY - minY));
            Particle particle = new Particle(ThreadLocalRandom.current().nextDouble(minX, maxX),
                    ThreadLocalRandom.current().nextDouble(minY, maxY),
                    new Velocity(xVelocity, yVelocity)
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

    public Position runAlgorithm() {
        int epochCounter = 0;
        while (epochCounter < numberOfEpochs) {
            for(Particle particle: particles) {
                particle.updateVelocity(inertiaCoefficient, socialCoefficient, cognitiveCoefficient);
                particle.updatePosition();
                validatePosition(particle);
                particle.updateValue(function);
                if (particle.getValue() > function.evaluate(bestGlobalPosition.getX(), bestGlobalPosition.getY())) {
                    bestGlobalPosition = particle.getPosition();
                }
                particle.updateBestLocalPosition(function);
            }
            for(Particle particle: particles) {
                System.out.println(particle.getPosition().getX() + " | "
                        + particle.getPosition().getY() + " | "
                        + particle.getValue()
                );
            }
            System.out.println();
            ++epochCounter;
        }
        return bestGlobalPosition;
    }



    private void updateParticlesValues() {
        for (Particle particle: particles) {
            particle.updateValue(function);
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

    private void validatePosition(Particle particle) {
        if (particle.getPosition().getX() < minX) {
            particle.getPosition().setX(minX);
        } else if (particle.getPosition().getX() > maxX) {
            particle.getPosition().setX(maxX);
        }
        if (particle.getPosition().getY() < minY) {
            particle.getPosition().setY(minY);
        } else if (particle.getPosition().getY() > maxY) {
            particle.getPosition().setY(maxY);
        }
    }
}
