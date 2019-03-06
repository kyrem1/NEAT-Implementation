import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Comparator;

/**
 * A Genome defines the topological map of a
 * neural network via a Symbol Table of nodes and a Symbol Table of connections.
 *
 * @author kyrem1
 * @version 4
 * @since 02-20-2019
 */
public class Genome {

    private double fitness; // Fitness Score

    private ArrayList<ConnectionGene> connections = new ArrayList<>();  // List of all connections
    private ArrayList<NodeGene> nodes = new ArrayList<>();  // List of nodes
    private ArrayList<NodeGene> network = new ArrayList<>();    // List of nodes st. in activation order
    private int numInputs;  // Number of outputs
    private int numOutputs; // Number of inputs
    private int layers = 2; // Current total number of layers, begins at 1
    public static int nextInnovationNumber = 1; // Next inoovation number to be assigned. Also encodes innately max innovation number.
    private int nextNode = 0;   // Next number to be assigned to a node
    private int biasNode; // Number assigned to the bias node

    /**
     * Constructor function for a new Genome.
     */
    public Genome(int numInputs, int numOutputs) {
        this.numInputs = numInputs;
        this.numOutputs = numOutputs;

        // Add in input nodes
        for (int i = 0; i < this.numInputs; i++) {
            this.nodes.add(new NodeGene(i));
            this.nextNode++;
            this.nodes.get(i).setLayer(0);
        }

        // Add in output nodes
        for (int i = 0; i < this.numOutputs; i++) {
            this.nodes.add(new NodeGene(i + this.numInputs));
            this.nodes.get(i + this.numInputs).setLayer(1);
            this.nextNode++;
        }

        this.nodes.add(new NodeGene(this.nextNode)); //bias node
        this.biasNode = this.nextNode;
        this.nextNode++;
        this.nodes.get(this.biasNode).setLayer(0);
    }

    //****************************************************************************************************************************************
    // Genome-Specific Utility Methods
    public void addConnection(int from, int to, double weight, boolean isEnabled) {
        this.connections.add(new ConnectionGene(from, to, weight, Genome.nextInnovationNumber, isEnabled));
        Genome.nextInnovationNumber++;
    }

    public void addNode(int layer) {
        this.nodes.add(new NodeGene(this.nextNode, layer));
        this.nextNode++;
        updateNetwork();
    }

    /**
     * <p>Utility for mutation methods</p>
     * @param startNo Beginning Node Number to test
     * @param endNo End Node Number to test
     * @return exists of connection | True if connection exists, else false
     */
    public boolean connectionExists(int startNo, int endNo) {
        boolean exists = false; // Assume False, unless proven otherwise
        for(ConnectionGene cg : this.connections) { // Iterate over connections
            if(cg.getFromNodeNum() == startNo && cg.getToNodeNum() == endNo) {
                exists = true;
            }
        }
        return exists;
    }

    //*****************************************************************************************************************
    // GENOME INFO

    /**
     * <p>Prints entire Connections array</p>
     */
    public void printConnections() {
        for (ConnectionGene cg : connections) {
            System.out.println(cg.toString());
        }
    }

    // FIXME Make topology display better
    public void printTopology() {
        this.printConnections();
        for(NodeGene node : this.network) {
            node.printNodeInfo();
        }
    }

    /**
     * <p>Ordered Network Info</p>
     */
    public void printNetwork() {
        for(NodeGene node : this.network) {
            System.out.println(node);
        }
    }

    //*****************************************************************************************************************
    // GENOME MAIN METHODS

    /**
     * <p>Call after creation of genome</p>
     */
    public void setupNetwork() {
        Random rng = new Random();

        for(NodeGene node : this.nodes) {

            if(node.getLayer() < this.layers - 1) { // Make sure node isn't in last layer

                for(NodeGene node_next_layer : this.nodes) {

                    if(node_next_layer.getLayer() == node.getLayer() + 1) { // Connect to every node in next layer
                        this.addConnection(node.getNumber(), node_next_layer.getNumber(), rng.nextDouble(), true);
                    }

                }

            }
        }

        this.updateNetwork();
    }

    /**
     * <p>Call after any updating of genomic information.</p>
     */
    public void updateNetwork() {
        this.network.clear();
        this.network.addAll(this.nodes);

        Collections.sort(this.network, new Comparator<NodeGene>() {
            @Override
            public int compare(NodeGene nodeGene1, NodeGene nodeGene2) {
                return Integer.compare(nodeGene1.getLayer(), nodeGene2.getLayer());
            }
        });

        for(ConnectionGene cg : this.connections) {
            cg.findNodes(this.nodes);
        }
        for(NodeGene node : this.nodes) {
            node.findConnections(this.connections);
        }
    }

    /**
     * Evaluates the network given an array of inputs
     *
     * @param inputValues an array of double input value(s)
     * @return an array of the output(s) of the network
     */
    public double[] feedForward(double[] inputValues) {
        //set the outputs of the input nodes
        for (int i = 0; i < this.numInputs; i++) {
            nodes.get(i).setOutputValue(inputValues[i]);
        }
        nodes.get(biasNode).setOutputValue(1); //output of bias is 1

        for (NodeGene node : this.network) {   //for each node in the network engage it(see node class for what this does)
            node.engage();
        }

        //the outputs are nodes[this.numInputs] to nodes[this.numInputs + this.numOutputs-1]
        double[] outs = new double[this.numOutputs];
        for (int i = 0; i < this.numOutputs; i++) {
            outs[i] = nodes.get(this.numInputs + i).getOutputValue();
        }

        for (NodeGene node : this.nodes) { //reset all the nodes for the next feed forward
            node.setInputSum(0);
        }

        return outs;
    }

    //****************************************************************************************************************************************
    // GENOME MUTATION METHODS // TODO Implement More Mutation Methods

    // I think it works....
    public void mutateAddNode() {
        Random rng = new Random();
        if(Util.weightedDecision(Population.MUTATION_RATE)) {
            int rConnectionNum = rng.nextInt(this.connections.size());
            int endlayer = this.connections.get(rConnectionNum).getToNode().getLayer();

            this.connections.addAll(this.connections.get(rConnectionNum).split(nextNode)); // Split Connection

            this.addNode(this.connections.get(rConnectionNum).getToNode().getLayer());
            this.connections.get(rConnectionNum).getToNode().setLayer(endlayer + 1);
            this.updateNetwork();
        }
    }

    // I think it works....
    public void mutateAddConnection() {
        Random rng = new Random();
        if(Util.weightedDecision(0.9)) {
            int random1 = rng.nextInt(this.nodes.size());
            int random2 = rng.nextInt(this.nodes.size());
            while(random1 >= random2) {
                 random2 = rng.nextInt(this.nodes.size());
            }

            if(!connectionExists(random1, random2)) { // Mutate if there is no connection between the two.
                addConnection(random1, random2, rng.nextDouble(), true);
                this.updateNetwork();
            }
        }

    }

    //******************************************************************************************
    //GETTER AND SETTER

    public ArrayList<ConnectionGene> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<ConnectionGene> connections) {
        this.connections = connections;
    }

    public ArrayList<NodeGene> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<NodeGene> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<NodeGene> getNetwork() {
        return network;
    }

    public void setNetwork(ArrayList<NodeGene> network) {
        this.network = network;
    }

    public int getNumInputs() {
        return numInputs;
    }

    public void setNumInputs(int numInputs) {
        this.numInputs = numInputs;
    }

    public int getNumOutputs() {
        return numOutputs;
    }

    public void setNumOutputs(int numOutputs) {
        this.numOutputs = numOutputs;
    }

    public int getLayers() {
        return layers;
    }

    public void setLayers(int layers) {
        this.layers = layers;
    }

    public int getNextNode() {
        return nextNode;
    }

    public void setNextNode(int nextNode) {
        this.nextNode = nextNode;
    }

    public int getBiasNode() {
        return biasNode;
    }

    public void setBiasNode(int biasNode) {
        this.biasNode = biasNode;
    }

}