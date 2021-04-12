package search.heuristic;

import org.junit.jupiter.api.Test;
import search.EightPuzzleNode;
import search.IntPair;
import search.Node;

import static search.SearchTestUtils.printSolution;

class AStarTest
{
    @Test
    void shouldReturnCorrectTargetCubekNodeHeuristic1()
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

        final var actual = new AStar<>(this::h1).heuristicSearch(root, expected);

        printSolution(actual);
    }

    @Test
    void shouldReturnCorrectTargetCubekNodeHeuristic2()
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

        final var actual = new AStar<>(this::h2).heuristicSearch(root, expected);

        printSolution(actual);
    }

    private int h1(final Node<int[][]> node, final Node<int[][]> target)
    {
        final var value = node.getValue();
        final var targetValue = target.getValue();
        var counter = 0;

        for (var row = 0; row < value.length; row++)
        {
            for (var col = 0; col < value[row].length; col++)
            {
                if (value[row][col] != 0 && value[row][col] != targetValue[row][col])
                {
                    counter++;
                }
            }
        }

        return counter;
    }

    private int h2(final Node<int[][]> node, final Node<int[][]> target)
    {
        final var value = node.getValue();
        final var targetValue = target.getValue();
        var manhattanDistance = 0;

        for (int i = 1; i <= 8; i++)
        {
            final var targetPos = detectPositionOf(i, targetValue);
            final var actualPos = detectPositionOf(i, value);

            if (targetPos != null && actualPos != null)
            {
                final var xDistance = Math.abs(targetPos.x() - actualPos.x());
                final var yDistance = Math.abs(targetPos.y() - actualPos.y());

                manhattanDistance += xDistance + yDistance;
            }
        }

        return manhattanDistance;
    }

    private IntPair detectPositionOf(final int i, final int[][] value)
    {
        for (var row = 0; row < value.length; row++)
        {
            for (var col = 0; col < value[row].length; col++)
            {
                if (value[row][col] == i)
                {
                    return new IntPair(col, row);
                }
            }
        }

        return null;
    }
}