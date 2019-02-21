import java.util.ArrayList;
/**
 * Node Object
 *
 * @author kyrem1
 * @version 1
 * @since 02-20-2019
 */

public class Node {
    private int number;
    private float inputSum;
    private float outputValue;
    private ArrayList<ConnectionGene> outputConnections;
    private int layer;

    /**
     * Constructor
     * @param number
     */
    Node(int number) {
        this.number = number;
        inputSum = 0;
        outputValue = 0;
        layer = 0;
    }


}
