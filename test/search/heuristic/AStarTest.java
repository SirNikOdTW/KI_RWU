package search.heuristic;

import org.junit.jupiter.api.Test;
import search.EightPuzzleNode;
import search.Node;

import static org.junit.jupiter.api.Assertions.*;
import static search.SearchTestUtils.printSolution;

class AStarTest
{
    @Test
    void shouldReturnCorrectTargetCubekNodeHeuristik1()
    {
        final int[][] state = {
                {3, 5, 0},
                {1, 2, 6},
                {4, 7, 8}
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
                if (value[row][col] != targetValue[row][col])
                {
                    counter++;
                }
            }
        }

        return counter;
    }

    private int h2(final Node<int[][]> node, final Node<int[][]> target)
    {
        return 0;
    }
}