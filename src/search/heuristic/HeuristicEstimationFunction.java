package search.heuristic;

import search.Node;

public interface HeuristicEstimationFunction<T>
{
    int heuristicEstimation(Node<T> node, Node<T> target);
}
