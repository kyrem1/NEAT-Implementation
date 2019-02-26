import java.util.ArrayList;

/**
 * <p>General Utilities for use with NEAT Algorithm</p>
 */
public class Util {

    /**
     * Prints a Float ArrayList
     * @param list ArrayList to Print
     */
    public static void printArr(ArrayList<Float> list) {
        for(Object f : list) {
            System.out.print(f + " | ");
        }
        System.out.println();
    }


}
