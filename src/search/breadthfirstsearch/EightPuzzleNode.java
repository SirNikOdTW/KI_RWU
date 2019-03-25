package search.breadthfirstsearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class EightPuzzleNode extends Node<int[][]>
{
    public EightPuzzleNode(int[][] state)
    {
        super(state);
    }

    private EightPuzzleNode(int[][] value, Node<int[][]> parent)
    {
        super(value, parent);
    }

    @Override
    public boolean isTargetReached(Node<int[][]> target)
    {
        for (int row = 0; row < super.value.length; row++)
        {
            for (int col = 0; col < super.value[row].length; col++)
            {
                if (super.value[row][col] != target.value[row][col])
                {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public List<Node<int[][]>> generateSuccessors()
    {
        var successors = new ArrayList<Node<int[][]>>();
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
                        successors.add(new EightPuzzleNode(newState, this));
                        break;
                    case RIGHT:
                        tmp = newState[y][x+1];
                        newState[y][x+1] = newState[y][x];
                        newState[y][x] = tmp;
                        successors.add(new EightPuzzleNode(newState, this));
                        break;
                    case DOWN:
                        tmp = newState[y+1][x];
                        newState[y+1][x] = newState[y][x];
                        newState[y][x] = tmp;
                        successors.add(new EightPuzzleNode(newState, this));
                        break;
                    case LEFT:
                        tmp = newState[y][x-1];
                        newState[y][x-1] = newState[y][x];
                        newState[y][x] = tmp;
                        successors.add(new EightPuzzleNode(newState, this));
                        break;
                }
            }
            catch (ArrayIndexOutOfBoundsException ignored)
            {
            }
        }

        return successors;
    }

    @Override
    protected boolean isValidValue(int[][] state)
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
        for (int row = 0; row < super.value.length; row++)
        {
            for (int col = 0; col < super.value[row].length; col++)
            {
                if (super.value[row][col] == 0)
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
            System.arraycopy(super.value[y], 0, copy[y], 0, copy.length);
        }

        return copy;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder("Node:\n");

        for (int[] row : super.value)
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
