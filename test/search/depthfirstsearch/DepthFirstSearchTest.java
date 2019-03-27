package search.depthfirstsearch;

import org.junit.jupiter.api.Test;
import search.EightPuzzleNode;
import search.breadthfirstsearch.BreadthFirstSearch;

import java.util.List;

import static search.SearchTestUtils.printSolution;

class DepthFirstSearchTest
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

        var actual = new DepthFirstSearch().depthFirstSearch(root, expected);

        printSolution(actual);
    }

//    @Test
//    void shouldReturnCorrectTargetChubekNode()
//    {
//        int[][] state = {
//                {2, 0, 4},
//                {6, 7, 1},
//                {8, 5, 3}
//        };
//        var root = new EightPuzzleNode(state);
//
//        int[][] targetState = {
//                {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 0}
//        };
//        var expected = new EightPuzzleNode(targetState);
//
//        var actual = new DepthFirstSearch().depthFirstSearch(root, expected);
//
//        printSolution(actual);
//    }
}