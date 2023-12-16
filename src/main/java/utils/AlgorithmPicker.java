package utils;

public class AlgorithmPicker {

    public static void main(String [] args) {
        Function f = new Function("x^2 + y");
        System.out.println(f.evaluate(2, 4));
    }
}
