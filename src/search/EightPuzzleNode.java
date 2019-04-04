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

    private EightPuzzleNode(final int[][] value, final Node<int[][]> parent, final int heuristicCosts)
    {
        super(value, parent, heuristicCosts);
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
        final var x = emptyPosition.getX();
        final var y = emptyPosition.getY();

        for (final var dir : Direction.values())
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

                this.swapStateField(newState, emptyPosition, posToSwap);
                final var successor = new EightPuzzleNode(newState, this, super.heuristicCosts+1);

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
        final var builder = new StringBuilder();

        for (final var row : super.value)
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

        for (var row = 0; row < super.value.length; row++)
        {
            for (var col = 0; col < super.value[row].length; col++)
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
        for (var row = 0; row < super.value.length; row++)
        {
            for (var col = 0; col < super.value[row].length; col++)
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

        for (var y = 0; y < copy.length; y++)
        {
            System.arraycopy(super.value[y], 0, copy[y], 0, copy.length);
        }

        return copy;
    }

    private void swapStateField(final int[][] newState, final IntPair emptyPos, final IntPair posToSwap)
    {
        final var tmp = newState[posToSwap.getY()][posToSwap.getX()];
        newState[posToSwap.getY()][posToSwap.getX()] = newState[emptyPos.getY()][emptyPos.getX()];
        newState[emptyPos.getY()][emptyPos.getX()] = tmp;
    }

    private enum Direction
    {
        TOP, RIGHT, DOWN, LEFT
    }
}
