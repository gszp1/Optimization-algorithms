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
        this.function = function;
        this.precision = precision;
        chromosomes = new ArrayList<>();
        generatePopulation();
    }

    private void generatePopulation() {
        int chromosomesXLength = 0;
        int chromosomesYLength = 0;
        int xDomainLength = (int) Math.abs(maxX - minX);
        int yDomainLength = (int) Math.abs(maxY - minY);
        int numberOfYElements = (int) (yDomainLength * Math.pow(10, precision));
        int numberOfXElements = (int) (xDomainLength * Math.pow(10, precision));
        while (Math.pow(2, chromosomesXLength) < numberOfXElements) {
            ++chromosomesXLength;
        }
        while (Math.pow(2, chromosomesYLength) < numberOfYElements) {
            ++chromosomesYLength;
        }
        for (int i = 0; i < populationSize; ++i) {
            chromosomes.add(new Chromosome(chromosomesXLength, chromosomesYLength));
        }
    }


}
