import javax.swing.*;
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
        this.nodes.put(3, new NodeGene(0,1.0f));
        this.nodes.put(4, new NodeGene(1));

        this.connections.put(1, new ConnectionGene(1,4, rng.nextFloat(), 1, true));
        this.connections.put(2, new ConnectionGene(2, 4, rng.nextFloat(), 2, true));
        this.connections.put(3, new ConnectionGene(3, 4, rng.nextFloat(), 3, true));

        this.input_nodes = new ArrayList<>();
        this.hidden_nodes = new ArrayList<>();
        this.output_nodes = new ArrayList<>();

    }

    public void printConnections() {
        for(int key : this.connections.keys()) {
            System.out.println(this.connections.get(key).toString());
        }
    }

    public float evaluate(ArrayList<Float> inputs) {
        // TODO Implement evaluation algorithm
        //  -   Can Initially specialized for XOR
        //  -   Afterwards, Generalize the Algorithm
        NodeGene tempnode;
        for(Integer key : this.input_nodes) {
            tempnode = this.nodes.get(key);
            for(Float in : inputs) {
                tempnode.setInput(in);
            }
        }

        for(Integer key : this.input_nodes) {

        }


        return 1.0f;    // Temporary
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



    // TODO Implement Mutation Methods


    /**
     * Unit Test for Genome Connection Genes
     * @param args
     */
    public static void main(String[] args) {
        Genome g = new Genome();
        g.updateKeyLists();
        NodeGene tempnode;
        for(Integer key : g.nodes) {
            tempnode = g.nodes.get(key);
            tempnode.findConnections(g.connections);
        }
        for(Integer key : g.nodes) {
            tempnode = g.nodes.get(key);
            tempnode.printConnections();
        }
//        g.printConnections();
    }










}
