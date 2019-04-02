package search.uninformed.iterativedeepening;

import search.Node;

public class IterativeDeepening
{
    public <T> Node<T> iterativeDeepening(final Node<T> node, final Node<T> target)
    {
        int lowBarrier = 0;

        Node<T> resultNode;

        do
        {
            resultNode = depthFirstSearch(node, target, 0, lowBarrier++);
        }
        while (resultNode == null);

        return resultNode;
    }

    private <T> Node<T> depthFirstSearch(final Node<T> node, final Node<T> target, int depth, final int barrier)
    {
        if (node.isTargetReached(target))
        {
            return node;
        }

        final var newNodes = node.generateSuccessors();

        while (!newNodes.isEmpty() && depth < barrier)
        {
            final var resultNode = depthFirstSearch(newNodes.get(0), target, ++depth, barrier);

            if (resultNode != null)
            {
                return resultNode;
            }

            newNodes.remove(0);
        }

        return null;
    }
}
