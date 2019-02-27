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

    // TODO Interface Parameters to Environment
    // Initial Parameters
    static final int POP_SIZE = 100;
    public static final int INPUT_SIZE = 2;
    public static final int OUTPUT_SIZE = 1;


    // SPECIATION PARAMETERS
    public static final double COMPATIBILITY_THRESHOLD = 3.0;
    public static final double C1_EXCESS_SIGNIFICANCE = 1.0;
    public static final double C2_DISJOINT_SIGNIFICANCE = 1.0;
    public static final double C3_WEIGHT_SIGNIFICANCE = 0.4;

    // MUTATION RATES
    public static final double MUTATION_RATE = 0.1;
    public static final double CONNECTION_WEIGHT_MUTATION_RATE = 0.8;
    public static final double UNIFORM_PERTURBATION_RATE = 0.10;
    public static final double DISABLED_IS_INHERITED_RATE = 0.75;
    public static final double OFFSPRING_MUTATION_WITHOUT_CROSSOVER_RATE = 0.25;
    public static final double INTERSPECIES_MATING_RATE = 0.001;
    public static final double NEW_LINK_MUTATION_RATE = 0.04; // TODO MAKE SMALL POP AND LARGE POP RATE
    


    // TODO
    //  - Implement Distance Algorithm
    //  - Implement Interfacing
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
