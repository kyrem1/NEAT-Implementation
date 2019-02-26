import java.util.ArrayList;

/**
 * @author kyrem1
 * @version 1
 * @since 2-21-2019
 */
public class Population {
    private ArrayList<Genome> members;
    private int bestFitnessIndex;
    private float bestFitness;

    // INITIAL PARAMETERS
    static final int POP_SIZE = 100;
    public static final int INPUT_SIZE = 2;
    public static final int OUTPUT_SIZE = 1;

    // TODO ADJUSTABLE PARAMETERS
    //  - Implement Distance Algorithm
    //
    //
    //*********************************************************


    /**
     * <p>Constructor for Population of Genomes</p>
     */
    public Population() {
        this.members = new ArrayList<Genome>();
        this.bestFitness = 0.0f;
        this.bestFitnessIndex = 0;
    }

    /**
     * <p>Initializes members ArrayList with
     *      random Genomes without hidden layer.</p>
     */
    public void initializePopulation() {
        for(int i = 0; i < Population.POP_SIZE; i++) {
            this.members.add(new Genome());
        }
    }

    /**
     * Unit test for Population initializer
     * Prints 25 of the entities in the population.
     * @param args Idk what this for........
     */
    public static void main(String[] args) {
        Population testPop = new Population();
        testPop.initializePopulation();

        int show = POP_SIZE / 8;
        Genome tempdata;

        for(int i = 0; i < POP_SIZE; i++) {
            if(i % show == 0) {
                tempdata = testPop.members.get(i);
                tempdata.printConnections();
            }
        }
    }
}
