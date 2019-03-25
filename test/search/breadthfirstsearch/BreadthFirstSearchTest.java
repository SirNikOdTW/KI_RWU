package search.breadthfirstsearch;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class BreadthFirstSearchTest
{
    @Test
    void shouldReturnCorrectTarget()
    {
        int[][] state = {
                {5, 0, 3},
                {2, 1, 6},
                {4, 7, 8}
        };
        var root = new EightPuzzleNode(state);

        int[][] targetState = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        var expected = new EightPuzzleNode(targetState);

        var actual = new BreadthFirstSearch().breadthFirstSearch(List.of(root), expected);

        System.out.println("Target: " + Arrays.deepToString(targetState));
        System.out.println("Actual:\n" + actual);
    }

    private void printSolution(Node<int[][]> targetNode)
    {

    }
}