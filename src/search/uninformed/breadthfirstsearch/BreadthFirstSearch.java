package search.uninformed.breadthfirstsearch;

import search.Node;

import java.util.ArrayList;
import java.util.List;

public class BreadthFirstSearch
{
    public <T> Node<T> breadthFirstSearch(final List<Node<T>> nodes, final Node<T> target)
    {
        final var newNodes = new ArrayList<Node<T>>();

        for (final Node<T> node : nodes)
        {
            if (node.isTargetReached(target))
            {
                return node;
            }

            newNodes.addAll(node.generateSuccessors());
        }

        if (!newNodes.isEmpty())
        {
            return breadthFirstSearch(newNodes, target);
        }

        return null;
    }
}
