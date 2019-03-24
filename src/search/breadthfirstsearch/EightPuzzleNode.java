package search.breadthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class EightPuzzleNode implements Node
{
    private int[][] state;

    public EightPuzzleNode(int[][] state) {
        if (!isValidState(state))
        {
            throw new IllegalArgumentException("Allowed numbers are only 0-8 and they must exist uniquely.");
        }

        this.state = state;
    }

    @Override
    public boolean isTargetReached(Node target) {
        return Arrays.equals(this.state, ((EightPuzzleNode) target).state);
    }

    @Override
    public List<Node> generateSuccessors() {
        ArrayList<Node> successors = new ArrayList<>();

        successors.add(new EightPuzzleNode(null));

        return successors;
    }

    private boolean isValidState(int[][] state)
    {
        int[] numbers = Arrays.stream(state).flatMapToInt(IntStream::of).toArray();
        int[] countOfNumbers = new int[9];

        for (int i : numbers)
        {
            try
            {
                countOfNumbers[i]++;
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                return false;
            }
        }

        for (int i : countOfNumbers)
        {
            if (i == 0 || i > 1)
            {
                return false;
            }
        }

        return true;
    }
}
