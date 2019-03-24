package search.breadthfirstsearch;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void shouldReturnEmptyListOfSuccessors()
    {
    }

    @Test
    public void shouldReturnCorrectSuccessors()
    {
        int[][] state = {
                {7, 1, 6},
                {0, 4, 2},
                {3, 5, 8}
        };

        var node = new EightPuzzleNode(state);
        var successors = node.generateSuccessors();

        Assertions.assertEquals(3, successors.size());

        int[][] newState1 = {
                {0, 1, 6},
                {7, 4, 2},
                {3, 5, 8}
        };

        int[][] newState2 = {
                {7, 1, 6},
                {4, 0, 2},
                {3, 5, 8}
        };

        int[][] newState3 = {
                {7, 1, 6},
                {3, 4, 2},
                {0, 5, 8}
        };

        Assertions.assertArrayEquals(newState1, ((EightPuzzleNode) successors.get(0)).getState());
        Assertions.assertArrayEquals(newState2, ((EightPuzzleNode) successors.get(1)).getState());
        Assertions.assertArrayEquals(newState3, ((EightPuzzleNode) successors.get(2)).getState());
    }
}