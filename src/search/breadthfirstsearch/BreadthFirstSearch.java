package search.breadthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BreadthFirstSearch
{
    public Node breadthFirstSearch(List<Node> nodes, Node target)
    {
        var newNodes = new ArrayList<Node>();

        for (Node node : nodes)
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
