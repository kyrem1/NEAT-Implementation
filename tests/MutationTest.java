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

    private static void mutationAddConnectionTest() {
        Genome g = new Genome(2,1);

        double[] ins = {1.0, 0.0};

        g.setupNetwork();
        g.printConnections();
        for(int i = 0; i < 5; i++) {
            System.out.print("\n---------------------------------------\n");
            g.mutateAddNode();
            g.mutateAddConnection();
            g.printConnections();
        }

        double[] outs = g.feedForward(ins);
        for(double d : outs) {
            System.out.println(d);
        }
    }

    public static void main(String[] args) {
//        splitConnectionTest();
//        mutationAddNodeTest();
        mutationAddConnectionTest();
    }

}
