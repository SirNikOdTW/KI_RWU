package search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import search.uninformed.breadthfirstsearch.BreadthFirstSearch;

import java.util.List;

import static search.SearchTestUtils.countNodes;
import static search.SearchTestUtils.printSolution;

public class ChessKnightTest
{
    @Test
    public void shouldReturnOne()
    {
        final var state = new ChessKnightNode.KnightPair('a', '3');

        final var root = new ChessKnightNode(state);

        final var targetState = new ChessKnightNode.KnightPair('b', '5');
        final var expected = new ChessKnightNode(targetState);

        final var actual = new BreadthFirstSearch().breadthFirstSearch(List.of(root), expected);

        printSolution(actual);
        var nodeCount = countNodes(actual);
        var edgeCount = nodeCount - 1;

        Assertions.assertEquals(1, edgeCount);
    }

    @Test
    public void shouldReturnThree()
    {
        final var state = new ChessKnightNode.KnightPair('a', '1');

        final var root = new ChessKnightNode(state);

        final var targetState = new ChessKnightNode.KnightPair('f', '1');
        final var expected = new ChessKnightNode(targetState);

        final var actual = new BreadthFirstSearch().breadthFirstSearch(List.of(root), expected);

        printSolution(actual);
        var nodeCount = countNodes(actual);
        var edgeCount = nodeCount - 1;

        Assertions.assertEquals(3, edgeCount);
    }
}
