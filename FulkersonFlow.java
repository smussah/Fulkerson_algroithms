package SADIK;
/**
 * Created by Sadik Mussah on 4/13/16.
 */
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FulkersonFlow implements Graph
{
    private Map<Integer, LinkedList<Edge>> adjacencies = new HashMap<Integer, LinkedList<Edge>>();
    private Map<Integer, LinkedList<Edge>> incidences = new HashMap<Integer, LinkedList<Edge>>();

    private Map<Integer, Node> nodes = new HashMap<Integer, Node>();
    private Node source;
    private Node sink;

    public void addNode(Node nodez)
    {
        if(containsNode(nodez)) throw new IllegalArgumentException(" " + nodez + "");
        nodes.put(nodez.ID, nodez);
        adjacencies.put(nodez.ID, new LinkedList<Edge>());
        incidences.put(nodez.ID, new LinkedList<Edge>());
    }
    public void addEdge(Edge edgez)
    {
        if(!containsNode(edgez.source) || !containsNode(edgez.dest))
            throw new IllegalArgumentException("مستحيل" + edgez);
        List<Edge> adjacent = adjacencies.get(edgez.source.ID);
        List<Edge> incident = incidences.get(edgez.dest.ID);
        adjacent.add(edgez);
        incident.add(edgez);
    }

    public void setSource(Node node)
    {
        source = node;
    }

    public Node getSource()
    {
        return source;
    }
    public void setSink(Node node)
    {
        sink = node;
    }
    public Node getSink()
    {
        return sink;
    }

    public int numNodes()
    {
        return nodes.size();
    }

    public int numEdges()
    {
        int numEdges = 0;

        for(List<Edge> adjList : adjacencies.values())
        {
            numEdges += adjList.size();
        }

        return numEdges;
    }


    public List<Edge> adjacent(Node nodez)
    {
        return adjacencies.get(nodez.ID);
    }

    public boolean containsNode(Node nodez)
    {
        return nodes.containsKey(nodez.ID);
    }

    public boolean containsEdge(Edge edgez)
    {
        List<Edge> adjList = adjacencies.get(edgez.source.ID);
        return adjList.contains(edgez);
    }

    public Node getNode(int ID)
    {
        return nodes.get(ID);
    }

    public Collection<Node> getNodes()
    {
        return nodes.values();
    }

    public Set<Integer> getNodesIDs()
    {
        return new HashSet<Integer>(nodes.keySet());
    }

    public List<Edge> getEdges()
    {
        List<Edge> edges = new LinkedList<Edge>();
        for(List<Edge> adjList: adjacencies.values())
        {
            edges.addAll(adjList);
        }

        return edges;
    }

    public void removeNode(Node nodez)
    {
        nodes.remove(nodez.ID);
        adjacencies.remove(nodez.ID);
        incidences.remove(nodez.ID);

        for(List<Edge> adjList : adjacencies.values())
        {
            Iterator<Edge> it = adjList.iterator();
            while(it.hasNext())
            {
                Edge edgez = it.next();
                if(edgez.dest.equals(nodez))
                {
                    it.remove();
                    break;
                }
            }
        }

        for(List<Edge> incList : incidences.values())
        {
            Iterator<Edge> it = incList.iterator();
            while(it.hasNext())
            {
                Edge edgez = it.next();
                if(edgez.source.equals(nodez))
                {
                    it.remove();
                    break;
                }
            }
        }
    }

    public void removeEdge(Edge edgez)
    {
        List<Edge> adjList = adjacencies.get(edgez.source.ID);
        List<Edge> incList = incidences.get(edgez.dest.ID);
        adjList.remove(edgez);
        incList.remove(edgez);
    }


    public void clear()
    {
        nodes.clear();
        adjacencies.clear();
        incidences.clear();
    }

    public List<Edge> incident(Node nodez)
    {
        return incidences.get(nodez.ID);
    }


    public Object clone()
    {
        FulkersonFlow graph = new FulkersonFlow();
        for(Node nodez : getNodes())
        {
            Node clonedNode = new Node(nodez);
            graph.adjacencies.put(nodez.ID, new LinkedList<Edge>());
            graph.incidences.put(nodez.ID, new LinkedList<Edge>());
            graph.nodes.put(nodez.ID, clonedNode);

            if(nodez.equals(source))
            {
                graph.setSource(clonedNode);
            }
            else if(nodez.equals(sink))
            {
                graph.setSink(clonedNode);
            }
        }


        for(Node nodez : getNodes())
        {
            LinkedList<Edge> clonedAdjList = graph.adjacencies.get(nodez.ID);
            for(Edge edgez : adjacent(nodez))
            {
                Node clonedSrc = graph.nodes.get(edgez.source.ID);
                Node clonedDest = graph.nodes.get(edgez.dest.ID);
                Edge clonedEdge = new Edge(clonedSrc, clonedDest, edgez.cap, edgez.flow);
                clonedAdjList.add(clonedEdge);
                LinkedList<Edge> clonedIncList = graph.incidences.get(edgez.dest.ID);
                clonedIncList.add(clonedEdge);
            }
        }


        return graph;
    }

    public Graph getSubGraph(Set<Integer> s)
    {
        FulkersonFlow subGraph = new FulkersonFlow();

        for(int nodez : s)
        {
            Node node = nodes.get(nodez);
            Node clonedNode = new Node(node);
            subGraph.addNode(clonedNode);
        }

        if(source != null)
        {
            Node clonedSource = new Node(source);
            subGraph.addNode(clonedSource);
            subGraph.setSource(clonedSource);
        }
        if(sink != null)
        {
            Node clonedSink = new Node(sink);
            subGraph.addNode(clonedSink);
            subGraph.setSink(clonedSink);
        }

        for(int nodez : subGraph.getNodesIDs())
        {
            LinkedList<Edge> clonedAdjList = subGraph.adjacencies.get(nodez);
            Node node = nodes.get(nodez);
            for(Edge edgez : adjacent(node))
            {
                if(subGraph.containsNode(edgez.dest))
                {
                    Node clonedSrc = subGraph.nodes.get(edgez.source.ID);
                    Node clonedDest = subGraph.nodes.get(edgez.dest.ID);
                    Edge clonedEdge = new Edge(clonedSrc, clonedDest, edgez.cap, edgez.flow);
                    clonedAdjList.add(clonedEdge);
                    LinkedList<Edge> clonedIncList = subGraph.incidences.get(edgez.dest.ID);
                    clonedIncList.add(clonedEdge);
                }
            }
        }

        return subGraph;
    }

    public String toString()
    {
        return "زواي" + adjacencies.toString() + "\nنت " + incidences.toString();
    }

}

