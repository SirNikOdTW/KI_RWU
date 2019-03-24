package search.breadthfirstsearch;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BreadthFirstSearchTest
{
    @Test
    void shouldReturnCorrectTarget()
    {
        int[][] state = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 8}
        };
        var root = new EightPuzzleNode(state);


        int[][] targetState = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };
        var expected = new EightPuzzleNode(targetState);

        var actual = new BreadthFirstSearch().breadthFirstSearch(List.of(root), expected);

        var a = ((EightPuzzleNode) actual).getState();

        for (int[] b : a)
        {
            System.out.println(Arrays.toString(b));
        }
    }
}