package SADIK;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by Sadik Mussah on 4/12/16.
 */
public class _Flow
{

    private static class lab_space
    {
        public Map<Node, Integer> labels;

        public int[] nodes;

        public lab_space(int node)
        {
            labels = new HashMap<Node, Integer>();
            nodes = new int[node + 1];
        }

        public int getLabel(Node node)
        {
            return labels.get(node);
        }


        public boolean setLabel(Node node, int label)
        {
            boolean not_marked = false;
            Integer labled = labels.get(node);
            if(labled != null)
            {
                nodes[labled]--;
                if(nodes[labled] == 0) not_marked = true;
            }

            labels.put(node, label);
            nodes[label]++;
            return not_marked;
        }
    }


    public static int getMaxFlow(Graph graph)
    {
        //في حاله ماش فيها شي
        if(graph.numNodes() == 0)
        {
            return 0;
        }

        lab_space labels = space(graph);
        int f = 0;
        int node = graph.numNodes();
        List<Edge> backEdges = addBackEdges(graph);
        LinkedList<Edge> path = new LinkedList<Edge>();
        int dist_sp;
        Node i = graph.getSource();


        while(i != null && (dist_sp = labels.getLabel(graph.getSource())) < node)
        {
            Edge e = get_edges(graph, i, labels);
            if(e != null)
            {
                i = forwards(e, path);
                if(i.equals(graph.getSink()))
                {
                    int delta = m_augmented(graph, path);
                    f += delta;
                    i = graph.getSource();
                    path.clear();
                }
            }
            else i = traced(graph, labels, i, path);
        }

        removeBackEdges(graph, backEdges);

        return f;
    }

    private static lab_space space(Graph graph)
    {
        int node = graph.numNodes();
        lab_space labels = new lab_space(node);

        Set<Node> visited = new HashSet<Node>();

        for (Node i : graph.getNodes())
        {
            labels.setLabel(i, node);
        }

        labels.setLabel(graph.getSink(), 0);

        LinkedList<Node> queue = new LinkedList<Node>();
        queue.addLast(graph.getSink());

        while (!queue.isEmpty())
        {
            Node j = queue.removeFirst();
           

            for (Edge e : graph.incident(j))
            {
                Node i = e.getSource();
                if (!visited.contains(i))
                {
                    labels.setLabel(i, labels.getLabel(j) + 1);
                   
                    visited.add(i);
                    queue.addLast(i);
                }
            }
            visited.add(j);

        }

        return labels;
    }

    private static List<Edge> addBackEdges(Graph graph)
    {
        List<Edge> backEdges = new LinkedList<Edge>();
        for(Edge e : graph.getEdges())
        {
            Edge backEdge = new Edge(e.getDest(), e.getSource(), 0, 0);
            graph.addEdge(backEdge);
            backEdges.add(backEdge);
        }
        return backEdges;
    }


    private static Edge get_edges(Graph graph, Node i, lab_space d)
    {
        for(Edge e: graph.adjacent(i))
        {
            if(e.getResidualCap() > 0 && d.getLabel(i) == 1 + d.getLabel(e.getDest()))
            {
                return e;
            }
        }
        return null;
    }


    private static Node forwards(Edge e, LinkedList<Edge> path)
    {
        path.addLast(e);
        return e.getDest();
    }


    private static int m_augmented(Graph graph, LinkedList<Edge> path)
    {

       int delta =Integer.MAX_VALUE;

        for(Edge e : path)
        {
          int residualCap = (int) e.getResidualCap();
            if(residualCap < delta) delta = residualCap;
        }


        for(Edge e : path)
        {

           int flow = (int) e.getFlow();
            flow += delta;
            e.setFlow(flow);


            Edge revEdge = null;
            for(Edge incEdge : graph.incident(e.getSource()))
            {
                if(incEdge.getSource().equals(e.getDest()))
                {
                    revEdge = incEdge;
                    break;
                }
            }


           int cap = (int) revEdge.getCap();
            cap += delta;
            revEdge.setCap(cap);
            flow = (int) revEdge.getFlow();
            if(flow > 0)
            {
                flow -= delta;
                revEdge.setFlow(flow);
            }

        }
        return delta;
    }


    private static Node traced(Graph graph, lab_space labels, Node i, LinkedList<Edge> path)
    {
        int dMin = graph.numNodes() - 1;

        for(Edge e : graph.adjacent(i))
        {
            if(e.getResidualCap() > 0)
            {
                Node j = e.getDest();
                int dj = labels.getLabel(j);
                if(dj < dMin) dMin = dj;
            }
        }

        boolean flag = labels.setLabel(i, 1 + dMin);

        Node predecessor;
        if(!flag)
        {

            if(!i.equals(graph.getSource()))
            {
                Edge e = path.removeLast();
                predecessor = e.getSource();
            }
            else predecessor = graph.getSource();
        }
        else predecessor = null;


        return predecessor;
    }

    private static void removeBackEdges(Graph graph, List<Edge> backEdges)
    {
        for(Edge e : backEdges)
        {
            graph.removeEdge(e);
        }
    }



}
