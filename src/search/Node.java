package search;

import java.util.List;
import java.util.Objects;

public abstract class Node<T>
{
    protected final T value;
    protected int heuristicCosts;
    private final Node<T> parent;
    private int heuristicEstimation;

    protected Node(final T value)
    {
        this(value, null, 0);
    }

    protected Node(final T value, final Node<T> parent)
    {
        this(value, parent, 0);
    }

    protected Node(final T value, final Node<T> parent, final int heuristicCosts)
    {
        this.value = Objects.requireNonNull(value);
        this.parent = parent;
        this.heuristicCosts = heuristicCosts;
    }

    public T getValue()
    {
        return this.value;
    }

    public Node<T> getParent()
    {
        return this.parent;
    }

    public int getHeuristic()
    {
        return heuristicCosts + heuristicEstimation;
    }

    public void setHeuristicEstimation(final int heuristicEstimation)
    {
        this.heuristicEstimation = heuristicEstimation;
    }

    public abstract boolean isTargetReached(final Node<T> target);
    public abstract List<Node<T>> generateSuccessors();
}
