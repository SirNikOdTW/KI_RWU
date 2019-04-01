package search.uninformed.depthfirstsearch;

import org.junit.jupiter.api.Test;
import search.EightPuzzleNode;

import static search.SearchTestUtils.printSolution;

class DepthFirstSearchTest
{
    @Test
    void shouldReturnCorrectTarget()
    {
        int[][] state = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 8}
        };
        var root = new EightPuzzleNode(state);

        int[][] targetState = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        var expected = new EightPuzzleNode(targetState);

        var actual = new DepthFirstSearch().depthFirstSearch(root, expected);

        printSolution(actual);
    }
}