package search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class EightPuzzleNode extends Node<int[][]>
{
    public EightPuzzleNode(final int[][] state)
    {
        super(state);
    }

    private EightPuzzleNode(final int[][] value, final Node<int[][]> parent)
    {
        super(value, parent);
    }

    @Override
    public boolean isTargetReached(final Node<int[][]> target)
    {
        return valueEquals(target);
    }

    @Override
    public List<Node<int[][]>> generateSuccessors()
    {
        final var successors = new ArrayList<Node<int[][]>>();
        final var emptyPosition = Objects.requireNonNull(detectEmptyPosition());
        final int x = emptyPosition.getX();
        final int y = emptyPosition.getY();

        for (final Direction dir : Direction.values())
        {
            final var newState = copyOfState();

            try
            {
                final var posToSwap = switch (dir) {
                    case TOP -> new IntPair(x, y-1);
                    case RIGHT -> new IntPair(x+1, y);
                    case DOWN -> new IntPair(x, y+1);
                    case LEFT -> new IntPair(x-1, y);
                };

                final var successor = this.swapStateField(newState, emptyPosition, posToSwap);

                if (!successor.valueEquals(this) && !successor.valueEquals(super.getParent()))
                {
                    successors.add(successor);
                }

            }
            catch (final ArrayIndexOutOfBoundsException ignored)
            {
            }
        }

        return successors;
    }

    @Override
    public String toString()
    {
        final StringBuilder builder = new StringBuilder();

        for (final int[] row : super.value)
        {
            builder.append(Arrays.toString(row)).append("\n");
        }

        return builder.toString();
    }

    private boolean valueEquals(final Node<int[][]> node)
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
        final var copy = new int[3][3];

        for (int y = 0; y < copy.length; y++)
        {
            System.arraycopy(super.value[y], 0, copy[y], 0, copy.length);
        }

        return copy;
    }

    private EightPuzzleNode swapStateField(final int[][] newState, final IntPair emptyPos, final IntPair posToSwap)
    {
        final int posToSwapX = posToSwap.getX();
        final int postToSwapY = posToSwap.getY();
        final int emptyX = emptyPos.getX();
        final int emptyY = emptyPos.getY();
        final int tmp;

        tmp = newState[postToSwapY][posToSwapX];
        newState[postToSwapY][posToSwapX] = newState[emptyY][emptyX];
        newState[emptyY][emptyX] = tmp;

        return new EightPuzzleNode(newState, this);
    }

    private class IntPair
    {
        private final int x;
        private final int y;

        public IntPair(final int x, final int y)
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
