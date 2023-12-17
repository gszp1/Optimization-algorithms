package utils;

import Genetic.GeneticAlgorithm;
import ParticleSwarm.ParticleSwarmAlgorithm;
import ParticleSwarm.Position;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AlgorithmPicker {

    public static void main(String [] args) {
        String function = String.join(" ", args);
        System.out.println(function);
        Function f = new Function(function);
//        ParticleSwarmAlgorithm particleSwarm = new ParticleSwarmAlgorithm(0, 100, 0, 100, 100, f,
//                1000, 0.5, 0.5, 0.5);
//        Position pos = particleSwarm.runAlgorithm();
//        System.out.println(pos.getX() + " | " + pos.getY() + " | " + f.evaluate(pos.getX(), pos.getY()));
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(0.9, 0.5, 25, 0, 100, 0 , 100, 6, f, 100);
        geneticAlgorithm.runAlgorithm();
    }
}
