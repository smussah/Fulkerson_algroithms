package SADIK;
/**
 * Created by Sadik Mussah on 4/19/16.
 */
public class Edge
{
    final Node source;
    final Node dest;
     int cap = 0;
   int  flow = 0;

    public Edge(Node source, Node dest, int cap)
    {
        this.source = source;
        this.dest = dest;
        this.cap = cap;
    }

    public Edge(Node source, Node dest, int cap, int flow)
    {
        this.source = source;
        this.dest = dest;
        this.cap = cap;
        this.flow = flow;
    }

    public Edge(Edge edge)
    {
        source = edge.source;
        dest = edge.dest;
        cap = edge.cap;
        flow = edge.flow;
    }

    public boolean equals(Object o)
    {
        if(o == null) return false;
        if(!(o instanceof Edge)) return false;
        Edge edge = (Edge)o;
        return edge.source.equals(source) && edge.dest.equals(dest) && edge.flow == flow && edge.cap == cap;
    }

    public String toString()
    {
        return  "(" + source.ID + ", " + dest.ID + ") "+"\n"+ "[" + flow + " ÷ " + cap + "] –––>";
    }

    public double getFlow()
    {
        return flow;
    }

    public void setFlow(int flow)
    {
        if(flow > cap) throw new IllegalArgumentException("cap is less than flow "
                + "_-_");
        this.flow = flow;
    }

    public Node getSource()
    {
        return source;
    }

    public Node getDest()
    {
        return dest;
    }

    public double getCap()
    {
        return cap;
    }

    public void setCap(int cap)
    {
        this.cap = cap;
    }

    public double getResidualCap()
    {
        return cap - flow;
    }

}