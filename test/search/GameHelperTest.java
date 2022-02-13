package search;

import org.junit.jupiter.api.Test;
import search.uninformed.iterativedeepening.IterativeDeepening;

import static search.SearchTestUtils.printSolution;

public class GameHelperTest
{
    @Test
    void shouldReturnCorrectTargetWartales()
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

        final var actual = new IterativeDeepening().iterativeDeepening(root, expected);

        printSolution(actual);
    }

    @Test
    void shouldReturnCorrectTargetLabyrinthine()
    {
        final boolean[][] state = {
                {true, false, true},
                {false, true, false},
                {true, false, true}
        };

        final var root = new LabyrinthineNode(state);

        final boolean[][] targetState = {
                {true, true, true},
                {true, true, true},
                {true, true, true},
                };

        final var expected = new LabyrinthineNode(targetState);

        final var actual = new IterativeDeepening().iterativeDeepening(root, expected);

        printSolution(actual);
    }
}
