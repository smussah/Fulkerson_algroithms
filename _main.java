package SADIK;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Sadik Mussah on 4/19/16.
 */
public class _main extends Gph_maker {

    public static void main(String[] args)
    {

        Graph graph = initiated_gph();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("");
        System.out.println("This program: runs - Maximum flow 'Ford Fulkerson algroithms");
        System.out.println("");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        int flow = _Flow.getMaxFlow(graph);
        System.out.println("Maximum flow = ~ " +flow+ " ~");
        System.out.println("");
        System.out.println("Augmented path is: " + graph.getEdges());

        Set<Integer> first_cut = new HashSet<Integer>();
       first_cut.add(1);first_cut.add(2);first_cut.add(3);
        System.out.println(" ");
        Graph g_1 = initiated_gph().getSubGraph(first_cut);
        int frst_cuts = _Flow.getMaxFlow(g_1);
        System.out.println("Augmented flow on 'First_cut is:  " + frst_cuts);
        System.out.println("Augmented path  is: " + g_1.getEdges());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");


        Set<Integer> second_cut = new HashSet<Integer>();
        second_cut.add(4);second_cut.add(5);second_cut.add(6);
        Graph g_2 = initiated_gph().getSubGraph(second_cut);
        int second_cuts = _Flow.getMaxFlow(g_2);
        System.out.println(" ");
        System.out.println("Augmented flow on second_cut is:  " + second_cuts);
        System.out.println("Augmented path  is:  " + g_2.getEdges());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("تحياتي");
    }

}
