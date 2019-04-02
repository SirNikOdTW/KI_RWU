package search;

import search.heuristic.Heuristic;

import java.util.List;
import java.util.Objects;

public abstract class Node<T>
{
    protected final T value;
    private final Node<T> parent;

    protected Node(final T value)
    {
        this(value, null);
    }

    protected Node(final T value, final Node<T> parent)
    {
        this.value = Objects.requireNonNull(value);
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

    public abstract boolean isTargetReached(final Node<T> target);
    public abstract List<Node<T>> generateSuccessors();
}
