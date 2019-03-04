import java.lang.reflect.Type;
import java.util.ArrayList;
import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;

/**
 * <p>General Utilities for use with NEAT Algorithm</p>
 */
public class Util {

    /**
     * Prints a Float ArrayList
     * @param list ArrayList to Print
     */
    public static void printArrD(ArrayList<Double> list) {
        for(Double f : list) {
            System.out.print(f + " :: ");
        }
        System.out.println();
    }

    public static void printArrI(ArrayList<Integer> list) {
        for(Integer f : list) {
            System.out.print(f + " | ");
        }
        System.out.println();
    }

    public static double sigmoid(double x) {
        return 1.0 / (1 + Math.exp(x));
    }

    public static double sigtransfer(double x) {
        return 1.0 / (1 + Math.exp(-4.9 * x));
    }

    public static boolean weightedDecision(double weight) {
        ArrayList<Pair<Boolean, Integer>> itemWeights = new ArrayList<Pair<Boolean, Integer>>();
        itemWeights.add(new Pair(true, weight));
        itemWeights.add(new Pair(false, 1 - weight));
        Object bool = new EnumeratedDistribution(itemWeights).sample();
        return (boolean)bool;
    }


}
