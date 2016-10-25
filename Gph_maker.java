package SADIK;

/**
 * Created by admin on 4/20/16.
 */
public class Gph_maker {
  public static Graph initiated_gph()
    {
        Graph graph = new FulkersonFlow();

        Node node_src = new Node(0, "Source");
        Node node_1 = new Node(1, "node_1");
        Node node_2 = new Node(2, "node_2");
        Node node_3 = new Node(3, "node_3");
        Node node_4 = new Node(4, "node_4");
        Node node_5 = new Node(5, "node_5");
        Node node_6 = new Node(6, "node_6");


        Node node_sink = new Node(8, "Sink");

        graph.addNode(node_src);
        graph.setSource(node_src);

        graph.addNode(node_1);
        graph.addNode(node_2);
        graph.addNode(node_3);
        graph.addNode(node_4);
        graph.addNode(node_5);
        graph.addNode(node_6);


        graph.addNode(node_sink);
        graph.setSink(node_sink);

        graph.addEdge(new Edge(node_src, node_1, 10));
        graph.addEdge(new Edge(node_src, node_2, 5));
        graph.addEdge(new Edge(node_src, node_3, 15));
        graph.addEdge(new Edge(node_1, node_4, 9));
        graph.addEdge(new Edge(node_1, node_2, 4));
        graph.addEdge(new Edge(node_1, node_5, 15));
        graph.addEdge(new Edge(node_2, node_5, 8));
        graph.addEdge(new Edge(node_2, node_3, 4));
        graph.addEdge(new Edge(node_3, node_6, 30));
        graph.addEdge(new Edge(node_4, node_5, 15));
        graph.addEdge(new Edge(node_4, node_sink, 10));
        graph.addEdge(new Edge(node_5, node_6, 15));
        graph.addEdge(new Edge(node_5, node_sink, 10));
        graph.addEdge(new Edge(node_6, node_2, 6));
        graph.addEdge(new Edge(node_6, node_sink, 10));

        return graph;
    }
}
