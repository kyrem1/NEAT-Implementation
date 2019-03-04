import java.util.ArrayList;
/**
 * NodeGene Object
 *
 * @author kyrem1
 * @version 1
 * @since 02-20-2019
 */

public class NodeGene {
    private int number;                         // Position in the corresponding Genome's nodes ArrayList
    private double inputSum;                          // Used for Input Nodes as alternative to ArrayList
    private double outputValue;
    private int layer;
    private ArrayList<ConnectionGene> outputConnections;        // Connections Beginning from this Node
    private ArrayList<ConnectionGene> inputConnections;      // Connection Genes Ending at this Node

    // TODO Implement automatic layer limiting

    /**
     * <p>Node Constructor</p>
     * @param number Node Number Label
     */
    public NodeGene(int number) {
        this.number = number;
        this.inputSum = 0.0;
        this.outputValue = 0.0;
        this.layer = 0;
        this.outputConnections = new ArrayList<>();
        this.inputConnections = new ArrayList<>();
    }

    /**
     * Node constructor with specified layer
     * @param number Number of Node
     * @param layer Layer Param
     */
    public NodeGene(int number, int layer) {
        this.number = number;
        this.inputSum = 0.0;
        this.outputValue = 0.0;
        this.layer = layer;
        this.outputConnections = new ArrayList<>();
        this.inputConnections = new ArrayList<>();
    }

    // Initializes the input and output connection lists.
    public void findConnections(ArrayList<ConnectionGene> connections) {
        this.outputConnections.clear();
        this.inputConnections.clear();
        for(ConnectionGene tempcon : connections) {
            if(tempcon.getToNodeNum() == this.number) {
                this.inputConnections.add(tempcon);
            }

            if(tempcon.getFromNodeNum() == this.number) {
                this.outputConnections.add(tempcon);
            }
        }
    }

    public void addInput(double input) {
        this.inputSum += input;
    }

    /**
     *   <p>Engages the node and feeds forward</p>
     */
    public void engage() {
        if (this.layer != 0) {  //Makes sure isn't input node, then gives activation function
            this.outputValue = Util.sigtransfer(this.inputSum);
        }

        for(ConnectionGene cg : this.outputConnections) { // Add new ouput to each of the connected output node's inputSum
            if(cg.isEnabled()) {    // Make sure connection is enabled first
                double feed = cg.getWeight() * this.outputValue;
                cg.getToNode().addInput(feed);
            }

        }

    }

    // ***********************************************************************************
    // Information Printing

    /**
     * prints Corresponding connections for testing this gene.
     */
    public void printConnections() {
        System.out.print("Node no: " + this.number + "\n");

        System.out.println("\t----FROM----");
        for (ConnectionGene cg : this.inputConnections) {
            System.out.printf("\t%d -- > %d\n", cg.getFromNodeNum(), cg.getToNodeNum());
        }

        System.out.println("\n\t----TO----");
        for (ConnectionGene cg : this.outputConnections) {
            System.out.printf("\t%d -- > %d\n", cg.getFromNodeNum(), cg.getToNodeNum());
        }
    }

    /**
     * Main Info Printer
     */
    public void printNodeInfo() {
        // Node Information
        System.out.print("Node no: " + this.number + "\n");

        // Input Info
        System.out.print("Input Sum: ");
        System.out.println(this.inputSum);

        // Connection Info
        System.out.println("\t----Into me----");
        for (ConnectionGene cg : this.inputConnections) {
            System.out.printf("\t%d -- > %d  W: %f\n", cg.getFromNodeNum(), cg.getToNodeNum(), cg.getWeight());
        }
        System.out.println("\n\t----Outta me----");
        for (ConnectionGene cg : this.outputConnections) {
            System.out.printf("\t%d -- > %d  W: %f\n", cg.getFromNodeNum(), cg.getToNodeNum(), cg.getWeight());
        }

        //Output Info
        System.out.print("Output: ");
        System.out.println(this.outputValue);
        System.out.println("\n**********\n");
    }

    /**
     * <p>toString method for NodeGene</p>
     */
    @Override
    public String toString() {
        return String.format("Node.Number: %d  |  Node.Layer: %d", this.number, this.layer);
    }

    // ***********************************************************************************
    // GETTER AND SETTERS
    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public double getInputSum() {
        return inputSum;
    }

    public void setInputSum(double inputSum) {
        this.inputSum = inputSum;
    }

    public double getOutputValue() {
        return outputValue;
    }

    public void setOutputValue(double outputValue) {
        this.outputValue = outputValue;
    }

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public ArrayList<ConnectionGene> getOutputConnections() {
        return outputConnections;
    }

    public void setOutputConnections(ArrayList<ConnectionGene> outputConnections) {
        this.outputConnections = outputConnections;
    }

    public ArrayList<ConnectionGene> getInputConnections() {
        return inputConnections;
    }

    public void setInputConnections(ArrayList<ConnectionGene> inputConnections) {
        this.inputConnections = inputConnections;
    }

}



    // TODO Make SENSIBLE toString method

