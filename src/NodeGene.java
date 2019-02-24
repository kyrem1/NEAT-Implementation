import java.util.ArrayList;
/**
 * NodeGene Object
 *
 * @author kyrem1
 * @version 1
 * @since 02-20-2019
 */

public class NodeGene {
    private String type;                          // Options: "INPUT" |  "HIDDEN"  |  "OUTPUT"
    private int number;                         // Position in the corresponding Genome's nodes ArrayList
    private ArrayList<Float> inputs;            // ArrayList of the inputs to this.NodeGene
    private float output;

    /**
     * Constructor for Regular Node
     * @param type (String) Layer location within the Genome.
     */
    public NodeGene(String type) {
        this.inputs = new ArrayList<Float>();
        this.output = 0;
        this.type = type;
    }

    /**
     * Constructor for Bias Node
     * @param type (String) Layer location within the Genome.
     * @param bias (float) Initializes the Node with constant input.
     */
    public NodeGene(String type, float bias) {
        this.inputs = new ArrayList<Float>();
        this.inputs.add(bias);
        this.output = 0;
        this.type = type;
    }

    // TODO Make SENSIBLE toString method
}
