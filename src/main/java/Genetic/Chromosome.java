package Genetic;

import java.util.concurrent.ThreadLocalRandom;

public class Chromosome {

    public static StringBuilder generateBinaryVector(int length) {
        StringBuilder binaryVector = new StringBuilder();
        for(int i = 0; i < length; ++i) {
            binaryVector.append(Integer.toString(ThreadLocalRandom.current().nextInt(0, 2)));
        }
        return binaryVector;
    }


    private final StringBuilder x;

    private final StringBuilder y;

    private double fitnessFunctionValue;

    public Chromosome(int xLength, int yLength) {
        x = generateBinaryVector(xLength);
        y = generateBinaryVector(yLength);
    }

    public Chromosome(StringBuilder x, StringBuilder y) {
        this.x = x;
        this.y = y;
    }

    public StringBuilder getX() {
        return x;
    }

    public StringBuilder getY() {
        return y;
    }

    public double decodeX(int domainLength, double minValue) {
        long binaryValue = Long.parseLong(String.valueOf(y), 2);
        return minValue + ((domainLength * binaryValue) / (Math.pow(2, x.length()) - 1));
    }

    public double decodeY(int domainLength, double minValue) {
        long binaryValue = Long.parseLong(String.valueOf(y), 2);
        return minValue + ((domainLength * binaryValue) / (Math.pow(2, y.length()) - 1));
    }

    public double getFitnessFunctionValue() {
        return fitnessFunctionValue;
    }

    public void setFitnessFunctionValue(double value) {
        fitnessFunctionValue = value;
    }

    public void mutateChromosome() {
        int picker = ThreadLocalRandom.current().nextInt(0, 2);
        if (picker == 0) {
            int mutationIndex = ThreadLocalRandom.current().nextInt(0, x.length());
            if (x.charAt(mutationIndex) == '0') {
                x.setCharAt(mutationIndex, '1');
            } else {
                x.setCharAt(mutationIndex, '0');
            }
        }
        int mutationIndex = ThreadLocalRandom.current().nextInt(0, y.length());
        if (y.charAt(mutationIndex) == '0') {
            y.setCharAt(mutationIndex, '1');
        } else {
            y.setCharAt(mutationIndex, '0');
        }
    }
}
