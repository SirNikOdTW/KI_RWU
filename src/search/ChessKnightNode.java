package search;

import java.util.ArrayList;
import java.util.List;

/**
 * For a Kata on codewars.com.
 * How many moves need a knight (Springer) to get from a given start to a given end on the chessboard,
 * e.g. a3 -> b5, the knight needs 1 move;
 * a1 -> f1, the knight needs 3 moves
 */
public class ChessKnightNode extends Node<ChessKnightNode.KnightPair> {
    public ChessKnightNode(final KnightPair state) {
        super(state);
    }

    private ChessKnightNode(final KnightPair value, final Node<KnightPair> parent, final int heuristicCosts) {
        super(value, parent, heuristicCosts);
    }

    @Override
    public boolean isTargetReached(final Node<KnightPair> target) {
        return this.valueEquals(target);
    }

    @Override
    public List<Node<KnightPair>> generateSuccessors() {
        final var successors = new ArrayList<Node<KnightPair>>();

        for (final var dir : ChessDirections.values()) {
            try {
                final char newCol = (char) (super.value.col() + dir.getColChange());
                final char newRow = (char) (super.value.row() + dir.getRowChange());

                final var newState = new KnightPair(newCol, newRow);

                final var successor = new ChessKnightNode(newState, this, super.heuristicCosts + 1);

                if (!successor.valueEquals(this) && !successor.valueEquals(super.getParent())) {
                    successors.add(successor);
                }
            } catch (final ChessKnightException ignored) {
            }
        }

        return successors;
    }

    private boolean valueEquals(final Node<KnightPair> node) {
        return node != null && super.value.equals(node.value);
    }

    @Override
    public String toString() {
        return "%c%c".formatted(super.value.col, super.value.row);
    }

    protected record KnightPair(char col, char row) {
        public KnightPair {
            if (col < 'a' || col > 'h') throw new ChessKnightException("Row out of bounds at index %c".formatted(col));
            if (row < '1' || row > '8') throw new ChessKnightException("Column out of bounds at index %c".formatted(row));
        }
    }

    protected enum ChessDirections {
        TOP_LEFT(-1, 2), TOP_RIGHT(1, 2),
        RIGHT_TOP(2, 1), RIGHT_BOTTOM(2, -1),
        DOWN_RIGHT(1 ,-2), DOWN_LEFT(-1,-2),
        LEFT_BOTTOM(-2,-1), LEFT_TOP(-2,1);

        public final int colChange;
        public final int rowChange;

        ChessDirections( final int colChange, final int rowChange) {
            this.colChange = colChange;
            this.rowChange = rowChange;
        }

        public int getColChange() {
            return colChange;
        }

        public int getRowChange() {
            return rowChange;
        }
    }

    protected static class ChessKnightException extends IndexOutOfBoundsException {
        public ChessKnightException(final String s) {
            super(s);
        }
    }
}