package search.uninformed.depthfirstsearch;

import org.junit.jupiter.api.Test;
import search.EightPuzzleNode;

import static search.SearchTestUtils.printSolution;

class DepthFirstSearchTest
{
    @Test
    void shouldReturnCorrectTarget()
    {
        final int[][] state = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 8}
        };
        final var root = new EightPuzzleNode(state);

        final int[][] targetState = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        final var expected = new EightPuzzleNode(targetState);

        final var actual = new DepthFirstSearch().depthFirstSearch(root, expected);

        printSolution(actual);
    }
}