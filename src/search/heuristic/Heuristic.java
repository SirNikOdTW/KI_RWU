package search.heuristic;

import search.Node;

public interface Heuristic<T>
{
    int heuristic(Node<T> node, Node<T> target);
}
