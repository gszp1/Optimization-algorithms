package utils;

import ParticleSwarm.ParticleSwarm;

public class AlgorithmPicker {

    public static void main(String [] args) {
        Function f = new Function("x^2 + y");
        ParticleSwarm particleSwarm = new ParticleSwarm(0, 100, 0, 100, 100, f,
                1000, 0.5, 0.5, 0.5);
        particleSwarm.runAlgorithm();
    }
}
