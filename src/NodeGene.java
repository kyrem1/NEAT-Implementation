import java.util.ArrayList;
/**
 * NodeGene Object
 *
 * @author kyrem1
 * @version 1
 * @since 02-20-2019
 */

public class NodeGene {
    private int type;                          // Options: "0" In |  "1" Hid |  "2" Out
    private int number;                         // Position in the corresponding Genome's nodes ArrayList
    private ArrayList<Float> inputs;            // ArrayList of the inputs to this.NodeGene
    private float input;                          // Used for Input Nodes as alternative to ArrayList
    private float output;
    private ArrayList<ConnectionGene> connectionsTo;        // Connections Going to this Node
    private ArrayList<ConnectionGene> connectionsFrom;      // Connection Genes Originating at this Node


    /**
     * Constructor for Regular Node
     * @param type (String) Layer location within the Genome.
     */
    public NodeGene(int type) {
        this.inputs = new ArrayList<Float>();
        this.output = 0;
        this.type = type;
        this.connectionsTo = new ArrayList<>();
        this.connectionsFrom = new ArrayList<>();

    }

    /**
     * Constructor for Bias Node
     * @param type (String) Layer location within the Genome.
     * @param bias (float) Initializes the Node with constant input.
     */
    public NodeGene(int type, float bias) {
        this.inputs = new ArrayList<Float>();
        this.inputs.add(bias);
        this.output = 0;
        this.type = type;
        this.connectionsTo = new ArrayList<>();
        this.connectionsFrom = new ArrayList<>();
    }

    public int getType() {
        return type;
    }

    public ArrayList<Float> getInputs() {
        return inputs;
    }

    public float getInput() {
        return input;
    }

    public void setInputs(ArrayList<Float> inputs) {
        this.inputs = inputs;
    }

    public void setInput(float input) {
        this.input = input;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void findConnections(ST<Integer, ConnectionGene> connections) {
        for(Integer key : connections) {
            ConnectionGene tempcon;
            tempcon = connections.get(key);

            if(tempcon.getFromNode() == this.number) {
                this.connectionsFrom.add(tempcon);
            }

            if(tempcon.getToNode() == this.number) {
                this.connectionsTo.add(tempcon);
            }
        }
    }

    /**
     * prints Corresponding connections for testing this gene.
     */
    public void printConnections() {
        System.out.print("Node no: " + this.number + "\n");

        System.out.println("\t----FROM----");
        for (ConnectionGene cg : this.connectionsFrom) {
            System.out.printf("\t%d -- > %d\n", cg.getFromNode(), cg.getToNode());
        }

        System.out.println("\n\t----TO----");
        for (ConnectionGene cg : this.connectionsTo) {
            System.out.printf("\t%d -- > %d\n", cg.getFromNode(), cg.getToNode());
        }
    }

    /**
     * Main Info Printer
     */
    public void printNodeInfo() {
        // Node Information
        System.out.print("Node no: " + this.number + "\n");

        // Input Info
        System.out.print("Input(s): ");
        if(this.type == 0) {
            System.out.println(this.input);
        } else {
            Util.printArr(inputs);
        }

        // Connection Info
        System.out.println("\t----FROM----");
        for (ConnectionGene cg : this.connectionsFrom) {
            System.out.printf("\t%d -- > %d  W: %f\n", cg.getFromNode(), cg.getToNode(), cg.getWeight());
        }

        System.out.println("\n\t----TO----");
        for (ConnectionGene cg : this.connectionsTo) {
            System.out.printf("\t%d -- > %d  W: %f\n", cg.getFromNode(), cg.getToNode(), cg.getWeight());
        }
    }


    // TODO Make SENSIBLE toString method
}
