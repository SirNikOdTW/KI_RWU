package search.depthfirstsearch;

import search.Node;

public class DepthFirstSearch
{
    public <T> Node<T> depthFirstSearch(Node<T> node, Node<T> target)
    {
        if (node.isTargetReached(target))
        {
            return node;
        }

        var newNodes = node.generateSuccessors();

        while (!newNodes.isEmpty())
        {
            var resultNode = depthFirstSearch(newNodes.get(0), target);

            if (resultNode != null)
            {
                return resultNode;
            }

            newNodes.remove(0);
        }

        return null;
    }
}
