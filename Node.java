package SADIK;

public class Node
{
    final int ID;
    String label = "";

    public Node(int ID)
    {
        this.ID = ID;
    }

    public Node(int ID, String label)
    {
        this.ID = ID;
        this.label = label;
    }

    public Node(Node node)
    {
        ID = node.getId();
        if(node.label != null) label = new String(node.label);
    }
    public int getId()
    {
        return ID;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public boolean equals(Object obj)
    {
        if(obj == null) return false;
        if(!(obj instanceof Node)) return false;
        Node node = (Node)obj;
        return node.ID == ID;
    }

  
    public int hashCode()
    {
        return ID;
    }




}

