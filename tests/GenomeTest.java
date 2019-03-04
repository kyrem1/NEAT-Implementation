import static org.junit.jupiter.api.Assertions.*;

class GenomeTest {

    private static void updateNetworkTest() {
        Genome g = new Genome(3, 1);
        g.setupNetwork();
        g.printNetwork();
        for (int i = 0; i < 10; i++) {
            System.out.print("\n---------------------------------------\n");
            g.mutateAddNode();
            g.printNetwork();
        }
    }

    private static void feedforwardTest() {
        double[] ins = {1.0, 0.0, 1.0, 0.0};

        Genome g = new Genome (4, 2);
        g.setupNetwork();

        for(int i = 0; i < 10; i++) {
            System.out.print("\n---------------------------------------\n");
            g.mutateAddNode();
            g.printConnections();
        }

        double[] outs = g.feedForward(ins);

        for(double d : outs) {
            System.out.println(d);
        }
    }

    public static void main(String[] args) {
        feedforwardTest();
    }
}