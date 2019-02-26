/**
 * ConnectionGene Object
 *
 * @author kyrem1
 * @version 1
 * @since 02-20-2019
 */

public class ConnectionGene {


    private int fromNode;
    private int toNode;
    private float weight;
    private boolean isEnabled;
    private int innovationNumber;

    /**
     *
     * @param fromNode  Node number for start of connection
     * @param toNode  Node number for endpoint of connection
     * @param weight  Initial value for Weight of connection
     * @param innovationNumber  Innovation Number of the connection
     * @param isEnabled  Specifies if connection is initially enabled
     */
    public ConnectionGene(int fromNode, int toNode, float weight, int innovationNumber, boolean isEnabled) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.weight = weight;
        this.innovationNumber = innovationNumber;
        this.isEnabled = isEnabled;
    }





    public float getWeight() {
        return weight;
    }

    public int getFromNode() {
        return fromNode;
    }

    public int getToNode() {
        return toNode;
    }

    public int getInnovationNumber() {
        return innovationNumber;
    }

    public void setFromNode(int fromNode) {
        this.fromNode = fromNode;
    }

    public String toString() {
        return String.format("%d ---> %d   |   weight == %4.2f   |  isEnabled == %b   |   innoNum: %d", this.fromNode, this.toNode, this.weight, this.isEnabled, this.innovationNumber);
    }
}
