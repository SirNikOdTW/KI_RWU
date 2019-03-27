package search;

public class SearchTestUtils
{
    public static <T> void printSolution(Node<T> targetNode)
    {
        var node = targetNode;

        System.out.println("Read from down to top!");
        System.out.println("END");

        while (node != null)
        {
            System.out.println(node);
            node = node.getParent();
        }

        System.out.println("START");
    }
}
