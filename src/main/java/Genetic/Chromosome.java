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

    public StringBuilder getX() {
        return x;
    }

    public StringBuilder getY() {
        return y;
    }

    public double decodeX(int domainLength, double minValue) {
        int binaryValue = Integer.parseInt(String.valueOf(x), 2);
        return minValue + ((domainLength * binaryValue) / (Math.pow(2, x.length()) - 1));
    }

    public double decodeY(int domainLength, double minValue) {
        int binaryValue = Integer.parseInt(String.valueOf(y), 2);
        return minValue + ((domainLength * binaryValue) / (Math.pow(2, x.length()) - 1));
    }

    public double getFitnessFunctionValue() {
        return fitnessFunctionValue;
    }

    public void setFitnessFunctionValue(double value) {
        fitnessFunctionValue = value;
    }

    public
}
