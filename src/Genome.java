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
    private int globalInnovationNumber;

    /**
     * Constructor function for a new Genome.
     */

    public Genome() {
        Random rng = new Random();
        this.connections = new ST<Integer, ConnectionGene>();
        this.nodes = new ST<Integer, NodeGene>();
        this.globalInnovationNumber = 3;                    // TODO Make global Innovation number increment automatically

        this.nodes.put(1, new NodeGene("INPUT"));
        this.nodes.put(2, new NodeGene("INPUT"));
        this.nodes.put(3, new NodeGene("INPUT",1.0f));
        this.nodes.put(4, new NodeGene("OUTPUT"));

        this.connections.put(1, new ConnectionGene(1,4, rng.nextFloat(), 1, true));
        this.connections.put(2, new ConnectionGene(2, 4, rng.nextFloat(), 2, true));
        this.connections.put(3, new ConnectionGene(3, 4, rng.nextFloat(), 3, true));
    }

    public void printConnections() {
        for(int key : this.connections.keys()) {
            System.out.println(this.connections.get(key).toString());
        }
    }

    public float feedforward() {
        // TODO Implement Feedforward algorithm
        //  -   Can Initially specialized for XOR
        //  -   Afterwards, Generalize the Algorithm



        return 1.0f;    // Temporary
    }


    // TODO Implement Mutation Methods


    /**
     * Unit Test for Genome Connection Genes
     * @param args
     */
    public static void main(String[] args) {
        Genome g = new Genome();
        g.printConnections();
    }










}
