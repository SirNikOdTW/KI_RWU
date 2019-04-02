package search.uninformed.breadthfirstsearch;

import org.junit.jupiter.api.Test;
import search.EightPuzzleNode;

import java.util.List;

import static search.SearchTestUtils.printSolution;

class BreadthFirstSearchTest
{
    @Test
    void shouldReturnCorrectTarget()
    {
        final int[][] state = {
                {5, 0, 3},
                {2, 1, 6},
                {4, 7, 8}
        };
        final var root = new EightPuzzleNode(state);

        final int[][] targetState = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        final var expected = new EightPuzzleNode(targetState);

        final var actual = new BreadthFirstSearch().breadthFirstSearch(List.of(root), expected);

        printSolution(actual);
    }

    @Test
    void shouldReturnCorrectTargetCubekNode()
    {
        final int[][] state = {
                {2, 0, 4},
                {6, 7, 1},
                {8, 5, 3}
        };
        final var root = new EightPuzzleNode(state);

        final int[][] targetState = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        final var expected = new EightPuzzleNode(targetState);

        final var actual = new BreadthFirstSearch().breadthFirstSearch(List.of(root), expected);

        printSolution(actual);
    }
}