package Genetic;

public class ChromosomePair {

    private final Chromosome firstChromosome;

    private final Chromosome secondChromosome;

    public ChromosomePair(Chromosome firstChromosome, Chromosome secondChromosome) {
        this.firstChromosome = firstChromosome;
        this.secondChromosome = secondChromosome;
    }

    public Chromosome getFirstChromosome() {
        return firstChromosome;
    }

    public Chromosome getSecondChromosome() {
        return secondChromosome;
    }
}
