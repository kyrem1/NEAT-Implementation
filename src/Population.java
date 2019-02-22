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
    private final int POP_SIZE;

    /**
     * <p>Constructor</p>
     * @param populationSize Initializes size constant for population.
     */
    public Population(int populationSize) {
        this.POP_SIZE = populationSize;
        this.members = new ArrayList<Genome>();
        this.bestFitness = 0.0f;
        this.bestFitnessIndex = 0;
    }

    /**
     * <p>Initializes members ArrayList with
     *      random Genomes without hidden layer.</p>
     */
    public void initializePopulation() {
        for(int i = 0; i < this.POP_SIZE; i++) {
            this.members.add(new Genome());
        }
    }


    /**
     * Unit test for Population initializer
     * Prints 25 of the entities in the population.
     * @param aargs
     */
    public static void main(String[] aargs) {
        Population testPop = new Population(100);
        testPop.initializePopulation();

        int show = testPop.POP_SIZE / 8;
        Genome tempdata;

        for(int i = 0; i < testPop.POP_SIZE; i++) {
            if(i % show == 0) {
                tempdata = testPop.members.get(i);
                tempdata.printConnections();
            }
        }
    }


}
