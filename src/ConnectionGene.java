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

    public ConnectionGene(int fromNode, int toNode, float weight, int innovationNumber) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.weight = weight;
        this.innovationNumber = innovationNumber;
    }


}
