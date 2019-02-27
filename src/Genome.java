import java.util.ArrayList;
import java.util.Random;

/**
 * A Genome defines the topological map of a
 * neural network via a Symbol Table of nodes and a Symbol Table of connections.
 *
 * @author kyrem1
 * @version 1
 * @since 02-20-2019
 */
public class Genome {

    private ST<Integer, ConnectionGene> connections;
    private ST<Integer, NodeGene> nodes;
    private ArrayList<Integer> input_nodes;
    private ArrayList<Integer> hidden_nodes;
    private ArrayList<Integer> output_nodes;
    private int globalInnovationNumber;

    /**
     * Constructor function for a new Genome.
     */
    public Genome() {
        Random rng = new Random();
        this.connections = new ST<Integer, ConnectionGene>();
        this.nodes = new ST<Integer, NodeGene>();
        this.globalInnovationNumber = 3;                    // TODO Make global Innovation number increment automatically

        this.nodes.put(1, new NodeGene(0));
        this.nodes.put(2, new NodeGene(0));
        this.nodes.put(3, new NodeGene(0,1.0));
        this.nodes.put(4, new NodeGene(1));
        this.nodes.put(5, new NodeGene(2));

        this.connections.put(1, new ConnectionGene(1,5, rng.nextDouble(), 1, true));
        this.connections.put(2, new ConnectionGene(2, 4, rng.nextDouble(), 2, true));
        this.connections.put(3, new ConnectionGene(4, 5, rng.nextDouble(), 3, true));
        this.connections.put(4, new ConnectionGene(3, 5, rng.nextDouble(), 4, true));

        this.input_nodes = new ArrayList<>();
        this.hidden_nodes = new ArrayList<>();
        this.output_nodes = new ArrayList<>();
    }

    /**
     * <p>Prints entire Connections array</p>
     */
    public void printConnections() {
        for(int key : this.connections.keys()) {
            System.out.println(this.connections.get(key).toString());
        }
    }

    //TODO CHANGE RETURN TYPE TO Double AND HANDLE ACCORDINGLY
    public void evaluate(ArrayList<Double> inputs) {
        // TODO Implement evaluation algorithm
        NodeGene tempnode;
        //Initialize Connection List in each NodeGene
        for(Integer key : this.nodes.keys()) {
            this.nodes.get(key).findConnections(this.connections);
        }
        //Initialize the Input nodes with args
        for(Integer key : this.input_nodes) {
            tempnode = this.nodes.get(key);
            for(Double in : inputs) {
                tempnode.setOutput(in);
            }
            tempnode.printNodeInfo();
        }


        //TODO MAKE WAY TO SKIP TO OUTPUT LAYER IF THERE IS NO HIDDEN LAYER
        //Feed forward
        for(Integer key : this.input_nodes) {
            for(ConnectionGene cg : this.nodes.get(key).getConnectionsTo()) {
                //Appends Weight * Previous Output to the Input list of the next Node.
                double wval = cg.getWeight() * this.nodes.get(key).getOutput();
                System.out.println(wval);
                this.nodes.get(cg.getToNode()).addInput(wval);
            }
            this.nodes.get(key).printNodeInfo();
        }

        //TODO ADD ERROR HANDLING FOR EMPTY HIDDEN LAYER AGAIN
        for(Integer key : this.hidden_nodes) {
            this.nodes.get(key).activation();
            for(ConnectionGene cg : this.nodes.get(key).getConnectionsFrom()) {
                //Appends Weight * Previous Output to the Input list of the next Node.
                double wval = cg.getWeight() * this.nodes.get(key).getOutput();
                System.out.println(wval);
                this.nodes.get(cg.getToNode()).addInput(wval);
            }
        }
        for(Integer key : this.output_nodes) {
            this.nodes.get(key).activation();
            this.nodes.get(key).printNodeInfo();
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
