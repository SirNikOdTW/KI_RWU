package search.uninformed.iterativedeepening;

import org.junit.jupiter.api.Test;
import search.EightPuzzleNode;

import static search.SearchTestUtils.printSolution;

class IterativeDeepeningTest
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

        var actual = new IterativeDeepening().iterativeDeepening(root, expected);

        printSolution(actual);
    }

    @Test
    void shouldReturnCorrectTargetCubekNode()
    {
        int[][] state = {
                {2, 0, 4},
                {6, 7, 1},
                {8, 5, 3}
        };
        var root = new EightPuzzleNode(state);

        int[][] targetState = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        var expected = new EightPuzzleNode(targetState);

        var actual = new IterativeDeepening().iterativeDeepening(root, expected);

        printSolution(actual);
    }
}