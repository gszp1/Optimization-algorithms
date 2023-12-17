package Genetic;

import java.util.ArrayList;

public class Roulette {

    private double currentUpperBoundary = 0;

    private final ArrayList<RouletteField> rouletteFields;

    public Roulette() {
        rouletteFields = new ArrayList<>();
    }
    
    public Chromosome findChromosome(double fitnessIndex) {
        for (RouletteField field: rouletteFields) {
            if (fitnessIndex >= field.getLowerBoundary() && fitnessIndex < field.getUpperBoundary()) {
                return field.getChromosome();
            }
        }
        return null;
    }

    public void addRouletteField(Chromosome chromosome, double fitnessIndex) {
        rouletteFields.add(new RouletteField(currentUpperBoundary, currentUpperBoundary + fitnessIndex, chromosome));
        currentUpperBoundary += fitnessIndex;
    }

}
