package search;

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
        return valueEquals(target);
    }

    @Override
    public List<Node<int[][]>> generateSuccessors()
    {
        var successors = new ArrayList<Node<int[][]>>();
        var emptyPosition = Objects.requireNonNull(detectEmptyPosition());
        int x = emptyPosition.getX();
        int y = emptyPosition.getY();

        for (Direction dir : Direction.values())
        {
            var newState = copyOfState();

            try
            {
                var posToSwap = switch (dir) {
                    case TOP -> new IntPair(x, y-1);
                    case RIGHT -> new IntPair(x+1, y);
                    case DOWN -> new IntPair(x, y+1);
                    case LEFT -> new IntPair(x-1, y);
                };

                var successor = this.swapStateField(newState, emptyPosition, posToSwap);

                if (!successor.valueEquals(this) && !successor.valueEquals(super.getParent()))
                {
                    successors.add(successor);
                }

            }
            catch (ArrayIndexOutOfBoundsException ignored)
            {
            }
        }

        return successors;
    }

    @Override
    protected boolean isValidParameterValue(int[][] state)
    {
        var numbers = Arrays.stream(state).flatMapToInt(IntStream::of).toArray();

        return numbersAreInAllowedRange(numbers) && numbersAreUnique(numbers);
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        for (int[] row : super.value)
        {
            builder.append(Arrays.toString(row)).append("\n");
        }

        return builder.toString();
    }

    private boolean valueEquals(Node<int[][]> node)
    {
        if (node == null)
        {
            return false;
        }

        for (int row = 0; row < super.value.length; row++)
        {
            for (int col = 0; col < super.value[row].length; col++)
            {
                if (super.value[row][col] != node.value[row][col])
                {
                    return false;
                }
            }
        }

        return true;
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
                    return new IntPair(col, row);
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

    private EightPuzzleNode swapStateField(int[][] newState, IntPair emptyPos, IntPair posToSwap)
    {
        int posToSwapX = posToSwap.getX();
        int postToSwapY = posToSwap.getY();
        int emptyX = emptyPos.getX();
        int emptyY = emptyPos.getY();
        int tmp;

        tmp = newState[postToSwapY][posToSwapX];
        newState[postToSwapY][posToSwapX] = newState[emptyY][emptyX];
        newState[emptyY][emptyX] = tmp;

        return new EightPuzzleNode(newState, this);
    }

    private class IntPair
    {
        private final int x;
        private final int y;

        public IntPair(int x, int y)
        {
            this.x = x;
            this.y = y;
        }

        public int getX()
        {
            return x;
        }
        public int getY()
        {
            return y;
        }

    }
    private enum Direction
    {
        TOP, RIGHT, DOWN, LEFT

    }
}
