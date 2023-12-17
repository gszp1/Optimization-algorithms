package Genetic;

public class RouletteField {
    private final double lowerBoundary;

    private final double upperBoundary;

    private final Chromosome chromosome;

    public RouletteField(double lowerBoundary, double upperBoundary, Chromosome chromosome) {
        this.lowerBoundary = lowerBoundary;
        this.upperBoundary = upperBoundary;
        this.chromosome = chromosome;
    }

    public Chromosome getChromosome() {
        return chromosome;
    }

    public double getLowerBoundary() {
        return lowerBoundary;
    }

    public double getUpperBoundary() {
        return upperBoundary;
    }
}
