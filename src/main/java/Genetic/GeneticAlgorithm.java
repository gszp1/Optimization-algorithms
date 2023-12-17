package Genetic;

import utils.Function;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GeneticAlgorithm {

    private final double minX;

    private final double maxX;

    private final double minY;

    private final double maxY;

    private final float crossingProbability;

    private final float mutationProbability;

    private final int populationSize;

    private final int precision;

    private ArrayList<Chromosome> chromosomes;

    private Roulette roulette;

    private final Function function;

    private final int numberOfGenerations;



    public GeneticAlgorithm(float crossingProbability, float mutationProbability, int populationSize,
                            double minX, double maxX, double minY, double maxY, int precision, Function function,
                            int numberOfGenerations) {
        this.crossingProbability = crossingProbability;
        this.mutationProbability = mutationProbability;
        this.populationSize = populationSize;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.function = function;
        this.precision = precision;
        this.numberOfGenerations = numberOfGenerations;
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

    public void runAlgorithm() {
        int generationsCounter = 0;
        while (generationsCounter < numberOfGenerations) {
            int xDomainLength = (int) Math.abs(maxX - minX);
            int yDomainLength = (int) Math.abs(maxY - minY);
            double fitnessFunctionValuesSum = 0;
            for(Chromosome chromosome: chromosomes) {
                double value = function.evaluate(chromosome.decodeX(xDomainLength, minX),
                        chromosome.decodeY(yDomainLength, minY));
                fitnessFunctionValuesSum += value;
                chromosome.setFitnessFunctionValue(value);
            }
            roulette = new Roulette(chromosomes, fitnessFunctionValuesSum);


            ++generationsCounter;
        }
    }

    private ArrayList<Chromosome> getParentsPopulation() {
        ArrayList<Chromosome> parentsPopulation = new ArrayList<>();
        while (parentsPopulation.size() < chromosomes.size()) {
            double randomValue = ThreadLocalRandom.current().nextDouble(0, roulette.getCurrentUpperBoundary());
            Chromosome chromosome = roulette.findChromosome(randomValue);
            if (chromosome != null) {
                parentsPopulation.add(chromosome);
            }
        }
        return parentsPopulation;
    }

    private ArrayList<Chromosome> getNewGeneration(ArrayList<Chromosome> parentsPopulation) {
        ArrayList<Chromosome> newPopulation = new ArrayList<>();
        ArrayList<ChromosomePair> parentPairs = new ArrayList<>();
        while (!parentsPopulation.isEmpty()) {
            int parentID = ThreadLocalRandom.current().nextInt(0, parentsPopulation.size());
            if (parentsPopulation.size() == 1) {
                newPopulation.add(chromosomes.get(parentID));
                parentsPopulation.remove(parentID);
                break;
            }
            int secondParentID = ThreadLocalRandom.current().nextInt(0, parentsPopulation.size());
            parentsPopulation.remove(secondParentID);
            parentPairs.add(new ChromosomePair(chromosomes.get(parentID), chromosomes.get(secondParentID)));
            parentsPopulation.remove(parentID);
            parentsPopulation.remove(secondParentID);
        }
        return newPopulation;
    }

    private ArrayList<Chromosome> parentsPairsCrossover(ArrayList<ChromosomePair> parentsPairs) {
        ArrayList<Chromosome> newPopulation = new ArrayList<>();
        for (ChromosomePair pair: parentsPairs) {
            if (ThreadLocalRandom.current().nextDouble(0, 1.0) > crossingProbability) {
                newPopulation.add(pair.getFirstChromosome());
                newPopulation.add(pair.getSecondChromosome());
            } else {
                ChromosomePair crossedPair = pair.chromosomeCrossover();
                newPopulation.add(crossedPair.getFirstChromosome());
                newPopulation.add(crossedPair.getSecondChromosome());
            }
        }
        return newPopulation;
    }
}
