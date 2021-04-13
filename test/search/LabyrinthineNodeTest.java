package search;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import search.uninformed.iterativedeepening.IterativeDeepening;

import static search.SearchTestUtils.printSolution;

class LabyrinthineNodeTest
{
    @Test
    public void shouldReturnTrueWhenTargetReached()
    {
        final boolean[][] state = {
                {true, true, true},
                {true, true, true},
                {true, true, true},
        };

        final var node = new LabyrinthineNode(state);
        final var target = new LabyrinthineNode(state);

        Assertions.assertTrue(node.isTargetReached(target));
    }

    @Test
    public void shouldReturnFalseWhenTargetNotReached()
    {
        final boolean[][] actualState = {
                {true, false, true},
                {false, true, false},
                {true, false, true},
        };

        final boolean[][] targetState = {
                {true, true, true},
                {true, true, true},
                {true, true, true},
        };

        final var node = new LabyrinthineNode(actualState);
        final var target = new LabyrinthineNode(targetState);

        Assertions.assertFalse(node.isTargetReached(target));
    }

    @Test
    public void shouldReturnNonEmptyListOfSuccessors()
    {
        final boolean[][] state = {
                {true, false, true},
                {false, true, false},
                {true, false, true},
        };

        final var node = new LabyrinthineNode(state);
        final var successors = node.generateSuccessors();

        Assertions.assertFalse(successors.isEmpty());
    }

    @Test
    public void shouldReturnCorrectSuccessor()
    {
        final boolean[][] state = {
                {true, false, true},
                {true, true, true},
                {true, true, true},
        };

        final var node = new LabyrinthineNode(state);
        final var successors = node.generateSuccessors();

        Assertions.assertEquals(1, successors.size());

        final boolean[][] targetSuccessor = {
                {false, true, false},
                {true, false, true},
                {true, true, true},
        };

        System.out.println(successors.get(0));

        Assertions.assertArrayEquals(targetSuccessor, successors.get(0).getValue());
    }

    @Test
    public void shouldReturnCorrectSuccessors()
    {
        final boolean[][] state = {
                {true, false, true},
                {true, true, false},
                {false, true, true}
        };

        final var node = new LabyrinthineNode(state);
        final var successors = node.generateSuccessors();

        Assertions.assertEquals(3, successors.size());

        final boolean[][] targetSuccessor1 = {
                {false, true, false},
                {true, false, false},
                {false, true, true}
        };

        final boolean[][] targetSuccessor2 = {
                {true, false, false},
                {true, false, true},
                {false, true, false}
        };

        final boolean[][] targetSuccessor3 = {
                {true, false, true},
                {false, true, false},
                {true, false, true}
        };

        Assertions.assertArrayEquals(targetSuccessor1, successors.get(0).getValue());
        Assertions.assertArrayEquals(targetSuccessor2, successors.get(1).getValue());
        Assertions.assertArrayEquals(targetSuccessor3, successors.get(2).getValue());
    }
}