import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BreadthFirstSearch
{
    public Node breadthFirstSearch(List<Node> nodes, Node target)
    {
        var newNodes = new ArrayList<Node>();

        for (Node node : nodes)
        {
            if (target.equals(node))
            {
                return node;
            }

            newNodes.addAll(node.getChildren());
        }

        if (newNodes.isEmpty())
        {
            return breadthFirstSearch(newNodes, target);
        }

        return null;
    }
}
