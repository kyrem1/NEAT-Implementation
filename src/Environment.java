/**
 * Environment Interface
 * @author kyrem1
 * @version 1
 * @since 02-20-2019
 */
public interface Environment {
    /**
     * Fitness function corresponding to the environment.
     * @param g The genome to evaluate the fitness of.
     * @return (float) Fitness of the specific genome
     */
    float fitness(Genome g);

}
