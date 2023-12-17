package utils;

import Genetic.GeneticAlgorithm;
import ParticleSwarm.ParticleSwarmAlgorithm;
import ParticleSwarm.Position;

import java.text.ParseException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlgorithmPicker {

    public static void main(String [] args) {
        if (args.length < 6) {
            System.out.println("Not enough parameters provided.\n"
                    + "Required parameters: Algorithm, xMin, xMax, yMin, yMax, function");
            return;
        }
        try {
            double xMin = Integer.parseInt(args[1]);
            double xMax = Integer.parseInt(args[2]);
            double yMin = Integer.parseInt(args[3]);
            double yMax = Integer.parseInt(args[4]);
            if (xMin >= xMax || yMin >= yMax) {
                System.out.println("Provided x or y domains are incorrect.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Provided x or y domains are incorrect.");
            return;
        }
        String function = String.join("", args);
        Function f = new Function(function);
//        ParticleSwarmAlgorithm particleSwarm = new ParticleSwarmAlgorithm(0, 100, 0, 100, 100, f,
//                1000, 0.5, 0.5, 0.5);
//        Position pos = particleSwarm.runAlgorithm();
//        System.out.println(pos.getX() + " | " + pos.getY() + " | " + f.evaluate(pos.getX(), pos.getY()));
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(0.9, 0.5, 25, 0, 100, 0 , 100, 6, f, 100);
        geneticAlgorithm.runAlgorithm();
    }
}
