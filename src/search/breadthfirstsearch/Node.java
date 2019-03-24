package search.breadthfirstsearch;

import java.util.List;

public interface Node
{
    boolean isTargetReached(Node target);
    List<Node> generateSuccessors();
}
