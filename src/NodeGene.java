import java.util.ArrayList;
/**
 * NodeGene Object
 *
 * @author kyrem1
 * @version 1
 * @since 02-20-2019
 */

public class NodeGene {
    private int layer;                          // Default (input layer) is 0.
    private int number;                         // Position in the corresponding Genome's nodes ArrayList
    private ArrayList<Float> inputs;            // ArrayList of the inputs to this.NodeGene
    private float output;

    /**
     * Constructor for Regular Node
     * @param layer (int) Layer location within the Genome. First layer is 0.
     */
    public NodeGene(int layer) {
        this.inputs = new ArrayList<Float>();
        this.output = 0;
        this.layer = layer;

    }

    /**
     * Constructor for Bias Node
     * @param layer (int) Layer location within the Genome. First layer is 0.
     * @param bias (float) Initializes the Node with constant input.
     */
    public NodeGene(int layer, float bias) {
        this.inputs = new ArrayList<Float>();
        this.inputs.add(bias);
        this.output = 0;
        this.layer = layer;
    }

    // TODO Make SENSIBLE toString method
}
