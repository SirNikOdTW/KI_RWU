package search.breadthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class EightPuzzleNode implements Node
{
    private int[][] state;

    public EightPuzzleNode(int[][] state)
    {
        if (!isValidState(state))
        {
            throw new IllegalArgumentException("Allowed numbers are only 0-8 and they must exist uniquely.");
        }

        this.state = state;
    }

    @Override
    public boolean isTargetReached(Node target)
    {
        for (int row = 0; row < this.state.length; row++)
        {
            for (int col = 0; col < this.state[row].length; col++)
            {
                if (this.state[row][col] != ((EightPuzzleNode) target).state[row][col])
                {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public List<Node> generateSuccessors()
    {
        var successors = new ArrayList<Node>();
        var emptyPosition = Objects.requireNonNull(detectEmptyPosition());
        int x = emptyPosition.getRight();
        int y = emptyPosition.getLeft();

        for (Direction dir : Direction.values())
        {
            var newState = copyOfState();

            try
            {
                int tmp;
                switch (dir)
                {
                    case TOP:
                        tmp = newState[y-1][x];
                        newState[y-1][x] = newState[y][x];
                        newState[y][x] = tmp;
                        successors.add(new EightPuzzleNode(newState));
                        break;
                    case RIGHT:
                        tmp = newState[y][x+1];
                        newState[y][x+1] = newState[y][x];
                        newState[y][x] = tmp;
                        successors.add(new EightPuzzleNode(newState));
                        break;
                    case DOWN:
                        tmp = newState[y+1][x];
                        newState[y+1][x] = newState[y][x];
                        newState[y][x] = tmp;
                        successors.add(new EightPuzzleNode(newState));
                        break;
                    case LEFT:
                        tmp = newState[y][x-1];
                        newState[y][x-1] = newState[y][x];
                        newState[y][x] = tmp;
                        successors.add(new EightPuzzleNode(newState));
                        break;
                }
            }
            catch (ArrayIndexOutOfBoundsException ignored)
            {
            }
        }

        return successors;
    }

    public int[][] getState()
    {
        return state;
    }

    private boolean isValidState(int[][] state)
    {
        var numbers = Arrays.stream(state).flatMapToInt(IntStream::of).toArray();

        return numbersAreInAllowedRange(numbers) && numbersAreUnique(numbers);
    }

    private boolean numbersAreInAllowedRange(int[] numbers)
    {
        return Arrays.stream(numbers).min().getAsInt() == 0 && Arrays.stream(numbers).max().getAsInt() == 8;
    }

    private boolean numbersAreUnique(int[] numbers)
    {
        return Arrays.stream(numbers).count() == Arrays.stream(numbers).distinct().count();
    }

    private IntPair detectEmptyPosition()
    {
        for (int row = 0; row < this.state.length; row++)
        {
            for (int col = 0; col < this.state[row].length; col++)
            {
                if (state[row][col] == 0)
                {
                    return new IntPair(row, col);
                }
            }
        }

        return null;
    }

    private int[][] copyOfState()
    {
        var copy = new int[3][3];

        for (int y = 0; y < copy.length; y++)
        {
            System.arraycopy(this.state[y], 0, copy[y], 0, copy.length);
        }

        return copy;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for (int[] row : this.state)
        {
            builder.append(Arrays.toString(row)).append("\n");
        }

        return builder.toString();
    }

    private class IntPair
    {
        private final int left;

        private final int right;

        public IntPair(int left, int right)
        {
            this.left = left;
            this.right = right;
        }

        public int getLeft()
        {
            return left;
        }
        public int getRight()
        {
            return right;
        }

    }
    private enum Direction
    {
        TOP, RIGHT, DOWN, LEFT

    }
}
