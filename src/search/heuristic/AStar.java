package search.heuristic;

import search.Node;

import java.util.Comparator;
import java.util.PriorityQueue;

public class AStar<T>
{
    private final HeuristicEstimationFunction<T> heuristicFunction;

    public AStar(final HeuristicEstimationFunction<T> heuristicFunction)
    {
        this.heuristicFunction = heuristicFunction;
    }

    public Node<T> heuristicSearch(final Node<T> start, final Node<T> target)
    {
        final var nodes = new PriorityQueue<Node<T>>(Comparator.comparingInt(Node::getHeuristic));
        start.setHeuristicEstimation(this.heuristicFunction.heuristicEstimation(start, target));
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

            final var successors = node.generateSuccessors();

            for (final var successor : successors)
            {
                successor.setHeuristicEstimation(this.heuristicFunction.heuristicEstimation(successor, target));
            }

            nodes.addAll(successors);
        }
    }
}
