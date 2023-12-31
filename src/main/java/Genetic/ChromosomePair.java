package Genetic;

import java.util.concurrent.ThreadLocalRandom;

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

    public ChromosomePair chromosomeCrossover() {
        StringBuilder part1, part2;
        int crossing = ThreadLocalRandom.current().nextInt(0, 2);
        if (crossing == 0) {
            int locus = ThreadLocalRandom.current().nextInt(1, firstChromosome.getX().length() - 1);
            part1 = new StringBuilder(firstChromosome.getX().substring(0, locus)
                    .concat(secondChromosome.getX().substring(locus)));
            part2 = new StringBuilder(secondChromosome.getX().substring(0, locus)
                    .concat(firstChromosome.getX().substring(locus)));
            return new ChromosomePair(new Chromosome(part1, firstChromosome.getY()),
                    new Chromosome(part2, secondChromosome.getY()));
        }
        int locus = ThreadLocalRandom.current().nextInt(1,
                firstChromosome.getY().length() - 1);
        part1 = new StringBuilder(firstChromosome.getY().substring(0, locus)
                .concat(secondChromosome.getY().substring(locus)));
        part2 = new StringBuilder(secondChromosome.getY().substring(0, locus)
                .concat(firstChromosome.getY().substring(locus)));
        return new ChromosomePair(new Chromosome(firstChromosome.getX(), part1),
                new Chromosome(secondChromosome.getX(), part2));
    }
}
