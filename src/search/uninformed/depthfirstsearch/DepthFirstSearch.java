package search.uninformed.depthfirstsearch;

import search.Node;

public class DepthFirstSearch
{
    public <T> Node<T> depthFirstSearch(final Node<T> node, final Node<T> target)
    {
        if (node.isTargetReached(target))
        {
            return node;
        }

        final var newNodes = node.generateSuccessors();

        while (!newNodes.isEmpty())
        {
            final var resultNode = depthFirstSearch(newNodes.get(0), target);

            if (resultNode != null)
            {
                return resultNode;
            }

            newNodes.remove(0);
        }

        return null;
    }
}
