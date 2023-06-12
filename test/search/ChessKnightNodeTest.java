package search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChessKnightNodeTest
{
    @Test
    public void shouldReturnTrueWhenTargetReached()
    {
        final var state = new ChessKnightNode.KnightPair('a', '1');

        final var node = new ChessKnightNode(state);
        final var target = new ChessKnightNode(state);

        Assertions.assertTrue(node.isTargetReached(target));
    }

    @Test
    public void shouldReturnFalseWhenTargetNotReached()
    {
        final var actualState = new ChessKnightNode.KnightPair('a', '1');

        final var targetState = new ChessKnightNode.KnightPair('h', '8');

        final var node = new ChessKnightNode(actualState);
        final var target = new ChessKnightNode(targetState);

        Assertions.assertFalse(node.isTargetReached(target));
    }

    @Test
    public void shouldReturnNonEmptyListOfSuccessors()
    {
        final var state = new ChessKnightNode.KnightPair('a', '1');

        final var node = new ChessKnightNode(state);
        final var successors = node.generateSuccessors();

        Assertions.assertFalse(successors.isEmpty());
    }

    @Test
    public void shouldReturnCorrectSuccessorsWithMaxPossibleSuccessors()
    {
        final var state = new ChessKnightNode.KnightPair('f', '3');

        final var node = new ChessKnightNode(state);
        final var successors = node.generateSuccessors();

        Assertions.assertEquals(8, successors.size());

        final var newState1 = new ChessKnightNode.KnightPair('e', '5');
        final var newState2 = new ChessKnightNode.KnightPair('g', '5');
        final var newState3 = new ChessKnightNode.KnightPair('h', '4');
        final var newState4 = new ChessKnightNode.KnightPair('h', '2');
        final var newState5 = new ChessKnightNode.KnightPair('g', '1');
        final var newState6 = new ChessKnightNode.KnightPair('e', '1');
        final var newState7 = new ChessKnightNode.KnightPair('d', '2');
        final var newState8 = new ChessKnightNode.KnightPair('d', '4');


        Assertions.assertEquals(newState1, successors.get(0).getValue());
        Assertions.assertEquals(newState2, successors.get(1).getValue());
        Assertions.assertEquals(newState3, successors.get(2).getValue());
        Assertions.assertEquals(newState4, successors.get(3).getValue());
        Assertions.assertEquals(newState5, successors.get(4).getValue());
        Assertions.assertEquals(newState6, successors.get(5).getValue());
        Assertions.assertEquals(newState7, successors.get(6).getValue());
        Assertions.assertEquals(newState8, successors.get(7).getValue());
    }

    @Test
    public void shouldReturnCorrectSuccessorsOnEdge()
    {
        final var state = new ChessKnightNode.KnightPair('a', '1');

        final var node = new ChessKnightNode(state);
        final var successors = node.generateSuccessors();

        Assertions.assertEquals(2, successors.size());

        Assertions.assertThrows(ChessKnightNode.ChessKnightException.class,
                () -> new ChessKnightNode.KnightPair((char) ('a' - 1), '3'));

        final var newState1 = new ChessKnightNode.KnightPair('b', '3');
        final var newState2 = new ChessKnightNode.KnightPair('c', '2');

        Assertions.assertThrows(ChessKnightNode.ChessKnightException.class,
                () -> new ChessKnightNode.KnightPair('c', '0'));

        Assertions.assertThrows(ChessKnightNode.ChessKnightException.class,
                () -> new ChessKnightNode.KnightPair('c', (char) ('0' - 1)));

        Assertions.assertThrows(ChessKnightNode.ChessKnightException.class,
                () -> new ChessKnightNode.KnightPair((char) ('a' - 1), (char) ('0' - 1)));

        Assertions.assertThrows(ChessKnightNode.ChessKnightException.class,
                () -> new ChessKnightNode.KnightPair((char) ('a' - 2), '0'));

        Assertions.assertThrows(ChessKnightNode.ChessKnightException.class,
                () -> new ChessKnightNode.KnightPair((char) ('a' - 2), '3'));

        Assertions.assertEquals(newState1, successors.get(0).getValue());
        Assertions.assertEquals(newState2, successors.get(1).getValue());
    }
}
