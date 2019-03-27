package search;

import java.util.List;

public abstract class Node<T>
{
    protected T value;
    private Node<T> parent;

    protected Node(T value)
    {
        this(value, null);
    }

    protected Node(T value, Node<T> parent)
    {
        if (!isValidParameterValue(value))
        {
            throw new IllegalArgumentException("Illegal node value");
        }

        this.value = value;
        this.parent = parent;
    }

    public T getValue()
    {
        return this.value;
    }

    public Node<T> getParent()
    {
        return this.parent;
    }

    protected abstract boolean isValidParameterValue(T value);
    public abstract boolean isTargetReached(Node<T> target);
    public abstract List<Node<T>> generateSuccessors();
}
