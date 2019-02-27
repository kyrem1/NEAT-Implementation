import java.util.ArrayList;

/**
 * <p>General Utilities for use with NEAT Algorithm</p>
 */
public class Util {

    /**
     * Prints a Float ArrayList
     * @param list ArrayList to Print
     */
    public static void printArrD(ArrayList<Double> list) {
        for(Object f : list) {
            System.out.print(f + " | ");
        }
        System.out.println();
    }

    public static void printArrI(ArrayList<Integer> list) {
        for(Object f : list) {
            System.out.print(f + " | ");
        }
        System.out.println();
    }


    public static double sigmoid(double x) {
        return 1.0 / (1 + Math.exp(x));
    }




}
