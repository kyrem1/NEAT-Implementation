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
    private ArrayList<Double> inputs;            // ArrayList of the inputs to this.NodeGene
    private double input;                          // Used for Input Nodes as alternative to ArrayList
    private double output;
    private ArrayList<ConnectionGene> connectionsTo;        // Connections Going to this Node
    private ArrayList<ConnectionGene> connectionsFrom;      // Connection Genes Originating at this Node


    /**
     * Constructor for Regular Node
     * @param type (String) Layer location within the Genome.
     */
    public NodeGene(int type) {
        this.inputs = new ArrayList<Double>();
        this.output = 0.0;
        this.type = type;
        this.connectionsTo = new ArrayList<>();
        this.connectionsFrom = new ArrayList<>();
    }

    /**
     * Constructor for Bias Node
     * @param type (String) Layer location within the Genome.
     * @param bias (Double) Initializes the Node with constant input.
     */
    public NodeGene(int type, Double bias) {
        this.inputs = new ArrayList<>();
        this.inputs.add(bias);
        this.output = bias;
        this.type = type;
        this.connectionsTo = new ArrayList<>();
        this.connectionsFrom = new ArrayList<>();
    }

    // Required for Activation
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

    public void addInput(double f) {
        this.inputs.add(f);
    }

    /**
     *   <p>Takes ArrayList of all the weighted inputs, and sets output as sigmoid of that</p>
     */
    public void activation() {
        double total = 0.0;
        for(Double f : this.inputs) {
            total += f;
        }
        this.output = Util.sigmoid(total);
        this.printNodeInfo();     // Debug
    }

    // ***********************************************************************************
    // Information Printing

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
        Util.printArrD(this.inputs);

        // Connection Info
        System.out.println("\t----FROM----");
        for (ConnectionGene cg : this.connectionsFrom) {
            System.out.printf("\t%d -- > %d  W: %f\n", cg.getFromNode(), cg.getToNode(), cg.getWeight());
        }

        System.out.println("\n\t----TO----");
        for (ConnectionGene cg : this.connectionsTo) {
            System.out.printf("\t%d -- > %d  W: %f\n", cg.getFromNode(), cg.getToNode(), cg.getWeight());
        }

        //Output Info
        System.out.print("Output: ");
        System.out.println(this.output);
        System.out.println("\n**********\n");
    }

    // ***********************************************************************************
    // GETTER AND SETTERS
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }

    public void setInputs(ArrayList<Double> inputs) {
        this.inputs = inputs;
    }
    public ArrayList<Double> getInputs() {
        return inputs;
    }

    public double getInput() {
        return input;
    }
    public void setInput(Double input) {
        this.input = input;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }

    public void setOutput(Double output) {
        this.output = output;
    }
    public double getOutput() {
        return output;
    }

    public ArrayList<ConnectionGene> getConnectionsTo() {
        return connectionsTo;
    }
    public ArrayList<ConnectionGene> getConnectionsFrom() {
        return connectionsFrom;
    }



    // TODO Make SENSIBLE toString method
}
