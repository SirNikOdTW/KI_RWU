package search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import search.EightPuzzleNode;

class EightPuzzleNodeTest
{
    @Test
    public void shouldThrowExceptionWhileStateHasDuplicateNumbers()
    {
        final int[][] state = {
                {1, 1, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        Assertions.assertThrows(IllegalArgumentException.class, () -> new EightPuzzleNode(state));
    }

    @Test
    public void shouldThrowExceptionWhileStateHasNumbersOutOfRange()
    {
        final int[][] state = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        Assertions.assertThrows(IllegalArgumentException.class, () -> new EightPuzzleNode(state));
    }

    @Test
    public void shouldReturnTrueWhenTargetReached()
    {
        final int[][] state = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        final var node = new EightPuzzleNode(state);
        final var target = new EightPuzzleNode(state);

        Assertions.assertTrue(node.isTargetReached(target));
    }

    @Test
    public void shouldReturnFalseWhenTargetNotReached()
    {
        final int[][] actualState = {
                {7, 1, 6},
                {0, 4, 2},
                {3, 5, 8}
        };

        final int[][] targetState = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        final var node = new EightPuzzleNode(actualState);
        final var target = new EightPuzzleNode(targetState);

        Assertions.assertFalse(node.isTargetReached(target));
    }

    @Test
    public void shouldReturnNonEmptyListOfSuccessors()
    {
        final int[][] state = {
                {7, 1, 6},
                {0, 4, 2},
                {3, 5, 8}
        };

        final var node = new EightPuzzleNode(state);
        final var successors = node.generateSuccessors();

        Assertions.assertFalse(successors.isEmpty());
    }

    @Test
    public void shouldReturnCorrectSuccessors()
    {
        final int[][] state = {
                {7, 1, 6},
                {0, 4, 2},
                {3, 5, 8}
        };

        final var node = new EightPuzzleNode(state);
        final var successors = node.generateSuccessors();

        Assertions.assertEquals(3, successors.size());

        final int[][] newState1 = {
                {0, 1, 6},
                {7, 4, 2},
                {3, 5, 8}
        };

        final int[][] newState2 = {
                {7, 1, 6},
                {4, 0, 2},
                {3, 5, 8}
        };

        final int[][] newState3 = {
                {7, 1, 6},
                {3, 4, 2},
                {0, 5, 8}
        };

        Assertions.assertArrayEquals(newState1, successors.get(0).getValue());
        Assertions.assertArrayEquals(newState2, successors.get(1).getValue());
        Assertions.assertArrayEquals(newState3, successors.get(2).getValue());
    }
}