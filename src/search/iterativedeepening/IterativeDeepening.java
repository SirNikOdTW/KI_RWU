package search.iterativedeepening;

import search.Node;

public class IterativeDeepening
{
    public <T> Node<T> iterativeDeepening(Node<T> node, Node<T> target)
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

    private <T> Node<T> depthFirstSearch(Node<T> node, Node<T> target, int depth, int barrier)
    {
        if (node.isTargetReached(target))
        {
            return node;
        }

        var newNodes = node.generateSuccessors();

        while (!newNodes.isEmpty() && depth < barrier)
        {
            var resultNode = depthFirstSearch(newNodes.get(0), target, ++depth, barrier);

            if (resultNode != null)
            {
                return resultNode;
            }

            newNodes.remove(0);
        }

        return null;
    }
}
