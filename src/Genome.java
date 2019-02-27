import java.util.ArrayList;
import java.util.Random;

/**
 * A Genome defines the topological map of a
 * neural network via a Symbol Table of nodes and a Symbol Table of connections.
 *
 * @author kyrem1
 * @version 4
 * @since 02-20-2019
 */
public class Genome {

    private ST<Integer, ConnectionGene> connections=  new ST<>();
    private ST<Integer, NodeGene> nodes = new ST<>();
    private ArrayList<Integer> input_nodes;
    private ArrayList<Integer> hidden_nodes;
    private ArrayList<Integer> output_nodes;
    private int globalInnovationNumber = 1;             // TODO Make global Innovation number increment automatically
    private int globalConnectionKey = 1;
    private int globalNodeKey = 1;

    /**
     * Constructor function for a new Genome.
     */
    public Genome() {
        Random rng = new Random();

        // TODO Streamline initial topology and connections
        addNode(0);
        addNode(0);
        addNode(0, 1.0);
        addNode(1);
        addNode(2);

        addConnection(1,5, rng.nextDouble(), true);
        addConnection(2, 4, rng.nextDouble(), true);
        addConnection(4, 5, rng.nextDouble(), true);
        addConnection(3,5, rng.nextDouble(), true);

        this.input_nodes = new ArrayList<>();
        this.hidden_nodes = new ArrayList<>();
        this.output_nodes = new ArrayList<>();
    }

    public void addConnection(int from, int to, double weight, boolean isEnabled) {
        this.connections.put(this.globalConnectionKey, new ConnectionGene(from, to, weight, this.globalInnovationNumber, isEnabled));
        this.globalInnovationNumber++;
        this.globalConnectionKey++;
    }

    public void addNode(int type) {
        this.nodes.put(this.globalNodeKey, new NodeGene(type));
        this.globalNodeKey++;
    }

    public void addNode(int type, double bias) {
        this.nodes.put(this.globalNodeKey, new NodeGene(type, bias));
        this.globalNodeKey++;
    }

    //*****************************************************************************************************************

    /**
     * <p>Prints entire Connections array</p>
     */
    public void printConnections() {
        for(int key : this.connections.keys()) {
            System.out.println(this.connections.get(key).toString());
        }
    }

    public void printWeights() {
        for(Integer key: this.connections) {
            System.out.println(this.connections.get(key));
        }
    }

    // TODO CHANGE RETURN TYPE TO Double AND HANDLE ACCORDINGLY

    /**
     * <p>Evaluates output(s) given an ArrayList of inputs</p>
     * @param inputs List of inputs to evaluate network with
     */
    public void evaluate(ArrayList<Double> inputs) {
        this.updateKeyLists();
        //Initialize Connection List in each NodeGene
        for(Integer key : this.nodes.keys()) {
            this.nodes.get(key).findConnections(this.connections);
        }

        // Initialize Input Nodes with given inputs.
        for(int i = 0; i < inputs.size(); i++) {
            double temp = inputs.get(i);
            this.nodes.get(i+1).addInput(temp);
            this.nodes.get(i+1).setOutput(temp);
            this.nodes.get(i+1).printNodeInfo();
        }

        //TODO MAKE WAY TO SKIP TO OUTPUT LAYER IF THERE IS NO HIDDEN LAYER
        //Feed forward |  INPUT --> HIDDEN
        for(Integer key : this.input_nodes) {
            for(ConnectionGene cg : this.nodes.get(key).getConnectionsFrom()) {
                //Appends Weight * Previous Output to the Input list of the next Node.
                double wval = cg.getWeight() * this.nodes.get(key).getOutput();
                this.nodes.get(cg.getToNode()).addInput(wval);
            }
        }

        //FIXME Might Throw an err or with empty hidden layer.
        //Hidden Activations
        for(Integer key : this.hidden_nodes) {
            this.nodes.get(key).activation();
            for(ConnectionGene cg : this.nodes.get(key).getConnectionsFrom()) {
                //Appends Weight * Previous Output to the Input list of the next Node.
                double wval = cg.getWeight() * this.nodes.get(key).getOutput();
                this.nodes.get(key).setOutput(wval);
                this.nodes.get(cg.getToNode()).addInput(wval);
            }
        }

        // Output Activation
        for(Integer key : this.output_nodes) {
            this.nodes.get(key).activation();
            System.out.println(this.nodes.get(key).getOutput());
        }
    }

    public void updateKeyLists() {
        for(Integer key : nodes) {
            nodes.get(key).setNumber(key);
        }

        NodeGene tempNode;
        for(Integer key : this.nodes.keys()) {
            tempNode = this.nodes.get(key);
            if(tempNode.getType() == 0) {
                this.input_nodes.add(key);
            } else if(tempNode.getType() == 1) {
                this.hidden_nodes.add(key);
            } else {
                this.output_nodes.add(key);
            }
        }


    }

    public ST<Integer, NodeGene> getNodes() {
        return nodes;
    }

    public ST<Integer, ConnectionGene> getConnections() {
        return connections;
    }

    // TODO Implement Mutation Methods

    public void mutateAddConnection() {

    }

    public void mutateAddNode() {

    }



    //******************************************************************************************
    //GETTER AND SETTER
    public ArrayList<Integer> getInput_nodes() {
        return input_nodes;
    }

    public ArrayList<Integer> getHidden_nodes() {
        return hidden_nodes;
    }

    public ArrayList<Integer> getOutput_nodes() {
        return output_nodes;
    }
}
