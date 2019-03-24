package search.breadthfirstsearch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EightPuzzleNodeTest
{
    @Test
    public void shouldThrowExceptionWhileStateHasDuplicateNumbers()
    {
        int[][] state = {
                {1, 1, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        Assertions.assertThrows(IllegalArgumentException.class, () -> new EightPuzzleNode(state));
    }

    @Test
    public void shouldThrowExceptionWhileStateHasNumbersOutOfRange()
    {
        int[][] state = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        Assertions.assertThrows(IllegalArgumentException.class, () -> new EightPuzzleNode(state));
    }

    @Test
    public void shouldReturnTrueWhenTargetReached()
    {
        int[][] state = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        var node = new EightPuzzleNode(state);
        var target = new EightPuzzleNode(state);

        Assertions.assertTrue(node.isTargetReached(target));
    }

    @Test
    public void shouldReturnFalseWhenTargetNotReached()
    {
        int[][] actualState = {
                {7, 1, 6},
                {0, 4, 2},
                {3, 5, 8}
        };

        int[][] targetState = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        var node = new EightPuzzleNode(actualState);
        var target = new EightPuzzleNode(targetState);

        Assertions.assertFalse(node.isTargetReached(target));
    }

    @Test
    public void shouldReturnNonEmptyListOfSuccessors()
    {
        int[][] state = {
                {7, 1, 6},
                {0, 4, 2},
                {3, 5, 8}
        };

        var node = new EightPuzzleNode(state);
        var successors = node.generateSuccessors();

        Assertions.assertFalse(successors.isEmpty());
    }

}