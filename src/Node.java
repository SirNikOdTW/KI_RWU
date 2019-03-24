import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Node
{
    private Object value;

    private Node parent;

    private List<Node> children;

    public Node(Object value)
    {
        this(value, new ArrayList<>());
    }

    public Node(Object value, List<Node> children)
    {
        this.value = value;
        this.children = children;

        for (Node child : children)
        {
            child.setParent(this);
        }
    }

    public void addChildren(List<Node> children)
    {
        this.children.addAll(children);
    }

    public void addChild(Node child)
    {
        this.children.add(child);
    }

    public void removeChild(Node child)
    {
        this.children.remove(child);
    }

    public Object getValue()
    {
        return value;
    }

    public void setParent(Node parent)
    {
        this.parent = parent;
    }

    public Node getParent()
    {
        return parent;
    }

    public List<Node> getChildren()
    {
        return children;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Node node = (Node) o;
        return value.equals(node.value);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(value);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for (Node child : children)
        {
            builder.append(this.value).append(" -> ").append(child.getValue()).append("\n");
        }

        for (Node child : children)
        {
            builder.append(child);
        }

        return builder.toString();
    }
}
