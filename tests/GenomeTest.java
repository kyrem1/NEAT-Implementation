import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GenomeTest {

    public static void topology(Genome g) {
        g.updateKeyLists();
        NodeGene tempnode;

        for(Integer key : g.getNodes()) {
            tempnode = g.getNodes().get(key);
            tempnode.findConnections(g.getConnections());
        }

        for(Integer key : g.getNodes()) {
            tempnode = g.getNodes().get(key);
            tempnode.printConnections();
        }
//        g.printConnections();
    }

    public static void main(String[] args) {
        Genome g = new Genome();
        ArrayList<Double> ins = new ArrayList<>();
        ins.add(1.0);
        ins.add(1.0);

        g.updateKeyLists();
        Util.printArrI(g.getInput_nodes());
        System.out.println();
        Util.printArrI(g.getHidden_nodes());
        System.out.println();
        Util.printArrI(g.getOutput_nodes());
        System.out.println();
        g.printConnections();
        g.evaluate(ins);

    }
}