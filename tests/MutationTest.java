import java.util.Random;

class MutationTest {

    private static void splitConnectionTest() {
        //Tests splitconnection
        Genome g = new Genome(2,1);
        g.printConnections();
        System.out.print("\n\n");
        // TODO Addme
        g.printConnections();

    }

    private static void mutationAddNodeTest() {
        Genome g = new Genome(4,1);
        g.setupNetwork();
        g.printConnections();
        for(int i = 0; i < 10; i++) {
            System.out.print("\n---------------------------------------\n");
            g.mutateAddNode();
            g.printConnections();

        }
    }

    public static void main(String[] args) {
        mutationAddNodeTest();
    }

}
