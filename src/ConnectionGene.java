import java.util.ArrayList;

/**
 * ConnectionGene Object
 *
 * @author kyrem1
 * @version 1
 * @since 02-20-2019
 */
public class ConnectionGene {

    private int fromNodeNum;
    private int toNodeNum;

    private NodeGene fromNode;
    private NodeGene toNode;

    private double weight;
    private boolean isEnabled;  // TODO Implement evaluation of isEnabled in evaluate method.
    private int innovationNumber;

    /**
     * Connection Constructor
     * @param fromNodeNum Node number for start of connection
     * @param toNodeNum  Node number for endpoint of connection
     * @param weight  Initial value for Weight of connection
     * @param innovationNumber  Innovation Number of the connection
     * @param isEnabled  Specifies if connection is initially enabled
     */
    public ConnectionGene(int fromNodeNum, int toNodeNum, double weight, int innovationNumber, boolean isEnabled) {
        this.fromNodeNum = fromNodeNum;
        this.toNodeNum = toNodeNum;
        this.weight = weight;
        this.innovationNumber = innovationNumber;
        this.isEnabled = isEnabled;
    }

    //******************************************************************************************************
    //MAIN Connection Methods

    public void findNodes(ArrayList<NodeGene> nodes) {
        for(NodeGene node : nodes) {
            if(node.getNumber() == this.fromNodeNum) {
                this.fromNode = node;
            } else if(node.getNumber() == this.toNodeNum) {
                this.toNode = node;
            }
        }
    }

    public ArrayList<ConnectionGene> split(int newNodeNum) {
        this.disable();

        ConnectionGene newConn1 = new ConnectionGene(this.fromNodeNum, newNodeNum, 1.0, Genome.nextInnovationNumber, true);
        Genome.nextInnovationNumber++;
        ConnectionGene newConn2 = new ConnectionGene(newNodeNum, this.toNodeNum, this.weight, Genome.nextInnovationNumber, true);
        Genome.nextInnovationNumber++;

        ArrayList<ConnectionGene> result = new ArrayList<>();
        result.add(newConn1);
        result.add(newConn2);
        return result;
    }

    public void disable() {
        if(this.isEnabled) {
            this.isEnabled = false;
        } else {
            System.out.println("Connection is already disabled");
        }
    }

    public void enable() {
        if(!this.isEnabled) {
            this.isEnabled = true;
        } else {
            System.out.println("Connection is already enabled");
        }
    }

    //******************************************************************************************************
    // GETTER AND SETTER

    public double getWeight() {
        return weight;
    }

    public int getFromNodeNum() {
        return fromNodeNum;
    }

    public int getToNodeNum() {
        return toNodeNum;
    }

    public int getInnovationNumber() {
        return innovationNumber;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setFromNodeNum(int fromNode) {
        this.fromNodeNum = fromNode;
    }

    public void setToNodeNum(int toNode) {
        this.toNodeNum = toNode;
    }

    public NodeGene getFromNode() {
        return fromNode;
    }

    public NodeGene getToNode() {
        return toNode;
    }

    @Override
    public String toString() {
        if(isEnabled) {
            return String.format("%d ---> %d   |   weight == %4.2f   |  isEnabled == %b   |   innoNum: %d", this.fromNodeNum, this.toNodeNum, this.weight, this.isEnabled, this.innovationNumber);
        } else {
            return null;
        }
    }


}
