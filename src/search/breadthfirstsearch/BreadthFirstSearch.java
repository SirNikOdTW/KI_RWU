package search.breadthfirstsearch;

import java.util.ArrayList;
import java.util.List;

public class BreadthFirstSearch
{
    public <T> Node<T> breadthFirstSearch(List<Node<T>> nodes, Node<T> target)
    {
        var newNodes = new ArrayList<Node<T>>();

        for (Node<T> node : nodes)
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
