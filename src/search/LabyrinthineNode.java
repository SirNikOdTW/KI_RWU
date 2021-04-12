package search;

import java.util.*;

public class LabyrinthineNode extends Node<boolean[][]>
{
    public LabyrinthineNode(final boolean[][] state)
    {
        super(state);
    }

    private LabyrinthineNode(final boolean[][] value, final Node<boolean[][]> parent, final int heuristicCosts)
    {
        super(value, parent, heuristicCosts);
    }

    @Override
    public boolean isTargetReached(final Node<boolean[][]> target)
    {
        return valueEquals(target);
    }

    @Override
    public List<Node<boolean[][]>> generateSuccessors()
    {
        final var successors = new ArrayList<Node<boolean[][]>>();
        final var offPositions = detectOffPositions();

        for (final var pos : offPositions)
        {
            final var newState = copyOfState();

            toggleStateAtPos(newState, new IntPair(pos.x(), pos.y()));
            toggleStateAtPos(newState, new IntPair(pos.x(), pos.y() + 1));
            toggleStateAtPos(newState, new IntPair(pos.x(), pos.y() - 1));
            toggleStateAtPos(newState, new IntPair(pos.x() + 1, pos.y()));
            toggleStateAtPos(newState, new IntPair(pos.x() - 1, pos.y()));

            final var successor = new LabyrinthineNode(newState, this, super.heuristicCosts + 1);

            if (!successor.valueEquals(this) && !successor.valueEquals(super.getParent()))
            {
                successors.add(successor);
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

    private boolean valueEquals(final Node<boolean[][]> node)
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

    private List<IntPair> detectOffPositions()
    {
        final var positions = new ArrayList<IntPair>();

        for (var row = 0; row < super.value.length; row++)
        {
            for (var col = 0; col < super.value[row].length; col++)
            {
                if (!super.value[row][col])
                {
                    positions.add(new IntPair(col, row));
                }
            }
        }

        return positions;
    }

    private boolean[][] copyOfState()
    {
        final var copy = new boolean[super.value.length][super.value[0].length];

        for (var y = 0; y < copy.length; y++)
        {
            System.arraycopy(super.value[y], 0, copy[y], 0, copy.length);
        }

        return copy;
    }

    private void toggleStateAtPos(boolean[][] newState, IntPair pos)
    {
        try
        {
            newState[pos.y()][pos.x()] = !newState[pos.y()][pos.x()];
        }
        catch (final ArrayIndexOutOfBoundsException ignored)
        {
        }
    }
}