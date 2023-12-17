package Genetic;

import java.util.ArrayList;

public class Roulette {

    private double currentUpperBoundary = 0;

    private final ArrayList<RouletteField> rouletteFields;

    public Roulette(ArrayList<Chromosome> chromosomes, double fitnessFunctionSum) {
        rouletteFields = new ArrayList<>();
        for (Chromosome chromosome: chromosomes) {
            addRouletteField(chromosome, (chromosome.getFitnessFunctionValue() / fitnessFunctionSum) * 100);
        }
    }

    public Chromosome findChromosome(double fitnessIndex) {
        for (RouletteField field: rouletteFields) {
            if (fitnessIndex >= field.getLowerBoundary() && fitnessIndex < field.getUpperBoundary()) {
                return field.getChromosome();
            }
        }
        return null;
    }

    private void addRouletteField(Chromosome chromosome, double fitnessIndex) {
        rouletteFields.add(new RouletteField(currentUpperBoundary,
                currentUpperBoundary + fitnessIndex, chromosome));
        currentUpperBoundary += fitnessIndex;
    }

    public double getCurrentUpperBoundary() {
        return currentUpperBoundary;
    }
}
