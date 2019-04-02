package search.heuristic;

import search.Node;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar<T>
{
    private final Heuristic<T> heuristicFunction;

    public AStar(final Heuristic<T> heuristicFunction)
    {
        this.heuristicFunction = heuristicFunction;
    }

    public Node<T> heuristicSearch(final Node<T> start, final Node<T> target)
    {
        final var nodes = new PriorityQueue<Node<T>>(Comparator.comparingInt(node -> heuristicFunction.heuristic(node, target)));
        nodes.add(start);

        while (true)
        {
            if (nodes.isEmpty())
            {
                return null;
            }

            final var node = nodes.poll();

            if (node.isTargetReached(target))
            {
                return node;
            }

            nodes.addAll(node.generateSuccessors());
        }
    }
}
