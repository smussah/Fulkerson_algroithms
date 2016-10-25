package SADIK;

        import java.util.Collection;
        import java.util.List;
        import java.util.Set;

public interface Graph extends Cloneable
{
    void addNode(Node n);
    void addEdge(Edge e);
    int numNodes();
    int numEdges();
    List<Edge> adjacent(Node n);
    List<Edge> incident(Node n);
    boolean containsNode(Node n);
    boolean containsEdge(Edge e);
    Node getNode(int id);
    Collection<Node> getNodes();
    Set<Integer> getNodesIDs();
    void setSource(Node node);
    Node getSource();
    void setSink(Node node);
    Node getSink();
    List<Edge> getEdges();
    void removeNode(Node n);
    void removeEdge(Edge e);
    void clear();
    Object clone();
    Graph getSubGraph(Set<Integer> s);



}
