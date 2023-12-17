package utils;

import Genetic.GeneticAlgorithm;
import ParticleSwarm.ParticleSwarmAlgorithm;

import java.util.Arrays;

public class AlgorithmPicker {

    public static void main(String [] args) {
        if (args.length < 6) {
            System.out.println("Not enough parameters provided.\n"
                    + "Required parameters: Algorithm, xMin, xMax, yMin, yMax, function");
            return;
        }
        double xMin, xMax, yMin, yMax;
        try {
            xMin = Integer.parseInt(args[1]);
            xMax = Integer.parseInt(args[2]);
            yMin = Integer.parseInt(args[3]);
            yMax = Integer.parseInt(args[4]);
            if (xMin >= xMax || yMin >= yMax) {
                System.out.println("Provided x or y domains are incorrect.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Provided x or y domains are incorrect.");
            return;
        }
        String [] functionArgs = Arrays.copyOfRange(args, 5, args.length);
        String function = String.join("", functionArgs);
        Function f = new Function(function);
        String algorithm = args[0].trim().toLowerCase();
        switch (algorithm) {
            case "genetic":
                GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(xMin, xMax, yMin, yMax,
                        0.9, 0.5, 25, 6, f, 100);
                geneticAlgorithm.runAlgorithm();
                break;
            case "particleswarm":
                ParticleSwarmAlgorithm particleSwarm = new ParticleSwarmAlgorithm(xMin, xMax, yMin, yMax,
                        25, f,100, 0.5, 0.5, 0.5);
                particleSwarm.runAlgorithm();
                break;
            default:
                System.out.println("Wrong algorithm provided. Enter 'Genetic' or 'ParticleSwarm'.");
        }
    }
}
