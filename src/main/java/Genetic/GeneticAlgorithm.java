package Genetic;

import utils.Function;

import java.util.ArrayList;

public class GeneticAlgorithm {

    private final double minX;

    private final double maxX;

    private final double minY;

    private final double maxY;

    private final float crossingProbability;

    private final float mutationProbability;

    private final int populationSize;

    private final int precision;

    private final ArrayList<Chromosome> chromosomes;

    private final Function function;

    public GeneticAlgorithm(float crossingProbability, float mutationProbability, int populationSize,
                            double minX, double maxX, double minY, double maxY, int precision, Function function) {
        this.crossingProbability = crossingProbability;
        this.mutationProbability = mutationProbability;
        this.populationSize = populationSize;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.precision = precision;
        chromosomes = new ArrayList<>();

    }

    public static void main(String [] args) {
        Chromosome chromosome = new Chromosome(15);
        System.out.println(chromosome.getX());
        System.out.println(chromosome.getY());
    }

}
